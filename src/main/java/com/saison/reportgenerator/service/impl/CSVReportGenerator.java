package com.saison.reportgenerator.service.impl;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.saison.reportgenerator.service.Generator;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class CSVReportGenerator implements Generator {


    public JSONObject getMapModJsonObj(LinkedHashMap<String,Object> json)
    {
        JSONObject obj = new JSONObject();
        try{
            Field chgJsonObjField = obj.getClass().getDeclaredField("map");
            chgJsonObjField.setAccessible(true);
            chgJsonObjField.set(obj,json);
            chgJsonObjField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public String getStringForCsv(JSONArray arr)
    {
        JSONObject jo = arr.getJSONObject(0);
        if(jo == null)
        {
            return null;
        }
        LinkedHashMap<String,Integer> headerCount = new LinkedHashMap<>();
        for(int i = 0; i<arr.length();i++)
        {
            //System.out.println("["+i+"]: "+arr.getJSONObject(i).keySet());
            arr.getJSONObject(i).keySet().stream().forEach(s->headerCount.putIfAbsent(s,1));
        }
        //System.out.println(headerCount.keySet());
        JSONArray names = new JSONArray(headerCount.keySet());
        if (names.length() == 0) {
            return null;
        }
        return CDL.rowToString(names) + CDL.toString(names, arr);
    }

    public LinkedHashMap flattenObject(String json)
    {
        //System.out.println(json);
        LinkedHashMap<String,Object> flatJsonMap =
                (LinkedHashMap) JsonFlattener.flattenAsMap(json);
        //System.out.println(flatJsonMap.toString()+"\nJson Flat as Map");
        return flatJsonMap;
    }

    public JSONArray flattenJson(String json)
    {
        JSONParser parser = new JSONParser(json);
        Object parsedJson;
        try {
            parsedJson = parser.parse();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(parsedJson.getClass()+"\n"+parsedJson);
        if( parsedJson.getClass() == ArrayList.class ) {
            JSONArray arr = new JSONArray(json);
            JSONArray flattenedJSON = new JSONArray();
            for(int i = 0; i<arr.length();i++)
            {
                LinkedHashMap<String,Object> flatJson =
                        (LinkedHashMap) flattenObject(arr.get(i).toString());
                //System.out.println(flatJson);
                flattenedJSON.put(getMapModJsonObj(flatJson));
            }
            return flattenedJSON;
        }
        LinkedHashMap<String,Object> flatJsonStr = flattenObject((String)json);
        JSONArray arr = new JSONArray();
        JSONObject obj = getMapModJsonObj(flatJsonStr);
        arr.put(obj);
        System.out.println(arr.get(0).toString()+"\nJSON In Array");
        return arr;
    }

    @Override
    public Object getReport(Object jsonExternal) throws IOException {

        String jsonStr = (String) jsonExternal;
        //System.out.println(jsonStr+"\nAbove is JSON");
        File csvFile = new File("/Users/ankitjha/Desktop/Reports/"+"CSVTransaction.csv");
        JSONArray tempArr = flattenJson(jsonStr);
        FileUtils.writeStringToFile(csvFile,getStringForCsv(tempArr));
        return csvFile.getPath();
    }

}
