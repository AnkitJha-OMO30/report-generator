package com.saison.reportgenerator.model.samples;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DBField1 {

    private String dbFieldStr;
    private String dbField22;

    private ArrayList<Field3Class> dbFieldArray;

    public String getDbFieldStr() {
        return dbFieldStr;
    }

    public void setDbFieldStr(String dbFieldStr) {
        this.dbFieldStr = dbFieldStr;
    }

    public String getDbField22() {
        return dbField22;
    }

    public void setDbField22(String dbField22) {
        this.dbField22 = dbField22;
    }

    public ArrayList<Field3Class> getDbFieldArray() {
        return dbFieldArray;
    }

    public void setDbFieldArray(ArrayList<Field3Class> dbFieldArray) {
        this.dbFieldArray = dbFieldArray;
    }

    @Override
    public String toString()
    {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("dbFieldStr" , dbFieldStr.toString());
        map.put("dbField22" , dbField22.toString());
        map.put("dbFieldArray" , dbFieldArray.toString());
        return map.toString();
    }

}
