package com.saison.reportgenerator.service.impl;

import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.saison.reportgenerator.service.Generator;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CsvToJsonReportGenerator implements Generator {

    public ArrayList<String> unflattenJson(JSONArray jsonArray)
    {
        ArrayList<String> unflattenedJson = new ArrayList<>();
        for(int i = 0; i<jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            for(String key: obj.keySet().stream().toList())
            {
                if(obj.get(key) == null || obj.get(key)=="")
                {
                    obj.remove(key);
                }
            }
            unflattenedJson.add(JsonUnflattener.unflatten(obj.toString()));
        }
        return unflattenedJson;
    }

    @Override
    public Object getReport(Object csv) {
        String csvString = (String) csv;
        JSONArray json = CDL.toJSONArray(csvString);
        ArrayList<String> jsonArrList = unflattenJson(json);
        return jsonArrList.toString();
    }

}
