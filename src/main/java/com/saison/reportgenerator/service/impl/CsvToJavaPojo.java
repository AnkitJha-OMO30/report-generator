package com.saison.reportgenerator.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.saison.reportgenerator.model.samples.DBField1;
import com.saison.reportgenerator.model.samples.RequestPojo;
import com.saison.reportgenerator.service.Generator;
import com.saison.reportgenerator.util.PojoMapper;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CsvToJavaPojo  implements Generator {

    ObjectMapper objectMapper = new ObjectMapper();

    public List<LinkedHashMap<String,Object>> getInitialJsonFromCsv(String csvStr)
    {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<LinkedHashMap<String,Object>> mappingIterator;
        try {
            mappingIterator = mapper.reader()
                    .forType(LinkedHashMap.class)
                    .with(schema)
                    .readValues(csvStr);
            List<LinkedHashMap<String,Object>> arrayList = mappingIterator.readAll();
            return arrayList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> unflattenJson(JsonNode jsonArray)
    {
        ArrayList<String> unflattenedJson = new ArrayList<>();
        for(int i = 0; i<jsonArray.size(); i++)
        {
            ObjectNode obj = (ObjectNode) jsonArray.get(i);
            obj.fieldNames().forEachRemaining(key -> {
                if(obj.get(key) == null || obj.get(key).toString().equals(""))
                {
                    obj.remove(key);
                }
            });

            unflattenedJson.add(JsonUnflattener.unflatten(obj.toString()));
        }
        return unflattenedJson;
    }

    @Override
    public Object getReport(Object csv) throws IOException {
        String csvStr = (String) csv;
        List<LinkedHashMap<String,Object>> arrayList = getInitialJsonFromCsv(csvStr);
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayList);

        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        ArrayList<String> unflattenJson = unflattenJson(jsonNode);
        System.out.println("Unflattened Flattened Json Below: \n" + unflattenJson.toString());
        RequestPojo pojo = objectMapper.readValue(unflattenJson.get(0).toString(), RequestPojo.class);

        PojoMapper mapper1 = Mappers.getMapper(PojoMapper.class);
        DBField1 field1 = mapper1.toDBField1Object(pojo);
        System.out.println("New DBField Pojo" + field1.toString());

        return unflattenJson+"\n Pojo String " + field1.toString();
    }

}
