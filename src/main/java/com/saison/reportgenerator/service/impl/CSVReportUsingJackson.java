package com.saison.reportgenerator.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CSVReportUsingJackson implements Generator {

    ObjectMapper mapper = new ObjectMapper();


    public void savetoCSV(ArrayList<String> flatCSV, File csvFile)
    {
        LinkedHashMap<String,Integer> headers = new LinkedHashMap<>();
        for(String json : flatCSV ) {
            try {
                JsonNode node = mapper.readTree(json);
                node.fieldNames().forEachRemaining(s -> headers.putIfAbsent(s,1));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        CsvSchema.Builder builder = CsvSchema.builder();
        headers.keySet().stream().forEach(builder::addColumn);

        CsvSchema schema = builder.build().withHeader();
        try {
            JsonNode node = mapper.readTree(flatCSV.toString());
            CsvMapper mapper = new CsvMapper();
            mapper.writerFor(JsonNode.class)
                    .with(schema)
                    .writeValue(csvFile,node);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }



    }

    public String flattenObject(String json)
    {
        LinkedHashMap<String,Object> flatJsonMap =
                (LinkedHashMap) JsonFlattener.flattenAsMap(json);
        return flatJsonMap.toString();
    }

    public ArrayList<String> flattenJSON(String json)
    {

        try {
            JsonNode node = mapper.readTree(json);
            //System.out.println(node.toString());
            ArrayList<String> flatJson = new ArrayList<>();
            if(node.isArray()) {
                for (int i = 0; i < node.size(); i++) {
                    flatJson.add(flattenObject(node.get(i).toString()));
                }
            }
            else
            {
                flatJson.add(flattenObject(node.toString()));
            }
            return flatJson;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getReport(Object jsonExternal) throws IOException, TemplateException {
        String jsonStr = (String) jsonExternal;
        File csvFile = new File("/Users/ankitjha/Desktop/Reports/"+"CSVTransaction.csv");
        ArrayList<String> flatJSON = flattenJSON(jsonStr);
        savetoCSV(flatJSON,csvFile);
        return csvFile.getPath();
    }
}
