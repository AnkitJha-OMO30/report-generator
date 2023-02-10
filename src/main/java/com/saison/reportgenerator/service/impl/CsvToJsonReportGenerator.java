package com.saison.reportgenerator.service.impl;

import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.saison.reportgenerator.service.Generator;
import org.json.CDL;
import org.json.JSONArray;

import java.util.ArrayList;

public class CsvToJsonReportGenerator implements Generator {


    @Override
    public Object getReport(Object csv) {
        String csvString = (String) csv;
        JSONArray json = CDL.toJSONArray(csvString);
        ArrayList<String> jsonArrList = new ArrayList<>();
        for(int i = 0; i<json.length();i++)
        {
            jsonArrList.add(JsonUnflattener.unflatten(json.get(i).toString()));
        }
        return jsonArrList.toString();
    }

}
