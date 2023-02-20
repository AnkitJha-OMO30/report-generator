package com.saison.reportgenerator.model.samples;

import java.util.LinkedHashMap;

public class Field3Class {

    private String field31;
    private String field32;

    public String getField31() {
        return field31;
    }

    public void setField31(String field31) {
        this.field31 = field31;
    }

    public String getField32() {
        return field32;
    }

    public void setField32(String field32) {
        this.field32 = field32;
    }

    @Override
    public String toString()
    {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("field31" , field31.toString());
        map.put("field32" , field32.toString());
        return map.toString();
    }

}
