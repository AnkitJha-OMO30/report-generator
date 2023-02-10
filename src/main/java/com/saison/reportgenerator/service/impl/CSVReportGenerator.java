package com.saison.reportgenerator.service.impl;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.saison.reportgenerator.service.Generator;
import org.apache.commons.io.FileUtils;
import org.jfree.data.json.impl.JSONValue;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CSVReportGenerator implements Generator {

    public String getStringForCsv(JSONArray arr)
    {
        JSONObject jo = arr.getJSONObject(0);
        if(jo == null)
        {
            return null;
        }
        Map<String, Integer> headerCount = new HashMap<>();
        for(int i = 0; i<arr.length();i++)
        {
            arr.getJSONObject(i).keySet().stream().forEach(s->headerCount.putIfAbsent(s,1));
        }
        //System.out.println(headerCount.keySet());
        JSONArray names = new JSONArray(headerCount.keySet());
        if (names == null) {
            return null;
        }
        return CDL.rowToString(names) + CDL.toString(names, arr);
    }

    public JSONArray flattenJsonArr(Object jsonExternal)
    {
        JSONArray arr = new JSONArray((ArrayList)jsonExternal);
        JSONArray flattenedJsonArr = new JSONArray();
        for(int i = 0; i<arr.length();i++)
        {
            JSONObject obj = arr.getJSONObject(i);
            String flattenJSON = JsonFlattener.flatten(obj.toString());
            //System.out.println(flattenJSON);
            flattenedJsonArr.put(new JSONObject(flattenJSON));
        }

        return flattenedJsonArr;
    }

    @Override
    public Object getReport(Object jsonExternal) throws IOException {

        String urlsToReturn = "";
        if(jsonExternal.getClass()== ArrayList.class)
        {
            JSONArray flattenedJsonArr = flattenJsonArr(jsonExternal);
            File csvFile = new File("/Users/ankitjha/Desktop/Reports/"+"CSVTransaction.csv");

            FileUtils.writeStringToFile(csvFile,getStringForCsv(flattenedJsonArr));
            return csvFile.getPath();
        }



        String jsonString = JSONValue.toJSONString((LinkedHashMap)jsonExternal);
        String jsonFlatter = JsonFlattener.flatten(jsonString);
        JSONObject object = new JSONObject(jsonFlatter);
        File csvFile = new File("/Users/ankitjha/Desktop/Reports/"+"CSVTransaction.csv");
        JSONArray tempArr = new JSONArray();
        tempArr.put(object);
        FileUtils.writeStringToFile(csvFile,getStringForCsv(tempArr));

        urlsToReturn=csvFile.getPath();

        return urlsToReturn;
    }
}
