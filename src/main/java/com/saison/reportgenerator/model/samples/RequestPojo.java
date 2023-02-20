package com.saison.reportgenerator.model.samples;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RequestPojo {

    private String field1;
    private Field2 field2;
    private ArrayList<Field3Class> field3;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public Field2 getField2() {
        return field2;
    }

    public void setField2(Field2 field2) {
        this.field2 = field2;
    }

    public ArrayList<Field3Class> getField3() {
        return field3;
    }

    public void setField3(ArrayList<Field3Class> field3) {
        this.field3 = field3;
    }

    @Override
    public String toString()
    {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("field1" , field1);
        map.put("field2" , field2.toString());
        map.put("field3" , field3.toString());
        return map.toString();
    }


}
