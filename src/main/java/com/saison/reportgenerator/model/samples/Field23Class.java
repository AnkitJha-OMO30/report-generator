package com.saison.reportgenerator.model.samples;

import java.util.LinkedHashMap;

public class Field23Class {
    private String field231;

    public String getField231() {
        return field231;
    }

    public void setField231(String field231) {
        this.field231 = field231;
    }

    @Override
    public String toString()
    {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("field231" , field231);
        return map.toString();
    }
}
