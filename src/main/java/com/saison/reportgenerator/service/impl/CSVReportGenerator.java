package com.saison.reportgenerator.service.impl;

import com.saison.reportgenerator.service.Generator;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CSVReportGenerator implements Generator {

    protected String customCSVHeaderForTransaction(JSONArray jsonArray)
    {
        JSONObject jo = jsonArray.optJSONObject(0);
        if (jo != null) {
            JSONArray names = jo.names();
            System.out.println();
            if (names != null) {
                return "Account Affected,Transaction,Amount(in Rs)\n" + CDL.toString(names, jsonArray);
            }
        }
        return null;
    }

    @Override
    public Object getReport(Map<String, Object> json) throws IOException {

        String urlsToReturn = "";
        JSONObject jsonObject = new JSONObject(json);

        JSONArray customList = jsonObject.getJSONArray("transactions");
        File csvFile = new File("/Users/ankitjha/Desktop/Reports/"+"CSVTransaction.csv");
        String csvWrite = customCSVHeaderForTransaction(customList);
        System.out.println(csvWrite);
        FileUtils.writeStringToFile(csvFile, csvWrite);
        urlsToReturn+="URL For Transaction: " + csvFile.getPath() + "\n";

        if(!json.containsKey("loans")) {
            return urlsToReturn;
        }

        customList = jsonObject.getJSONArray("loans");
        csvFile = new File("/Users/ankitjha/Desktop/Reports/"+"CSVLoans.csv");
        FileUtils.writeStringToFile(csvFile, CDL.toString(customList));
        urlsToReturn+="URL For Transaction: " + csvFile.getPath() + "\n";

        return urlsToReturn;
    }
}
