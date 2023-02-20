package com.saison.reportgenerator.model.samples;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Field2 {

    private String field21;
    private String field22;
    private ArrayList<Field23Class> field23;

    public String getField21() {
        return field21;
    }

    public void setField21(String field21) {
        this.field21 = field21;
    }

    public String getField22() {
        return field22;
    }

    public void setField22(String field22) {
        this.field22 = field22;
    }

    public ArrayList<Field23Class> getField23() {
        return field23;
    }

    public void setField23(ArrayList<Field23Class> field23) {
        this.field23 = field23;
    }

    @Override
    public String toString()
    {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("field21",field21);
        map.put("field22",field22);
        map.put("field23",field23.toString());
        return map.toString();
    }
}
