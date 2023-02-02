package com.saison.reportgenerator.service;

import com.saison.reportgenerator.model.ReportLog;

import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

public class SaveReportLog {

    private static ReportLog getNewObject() {
        ReportLog temp = new ReportLog();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
        temp.setDateStamp(cal.getTime());
        return temp;
    }
    public static ReportLog saveLogs(ReportLogService service, String urlStored, Map<String,Object> json) {
        ReportLog newReportLog = getNewObject();
        newReportLog.setUserId((String) json.get("userId"));
        newReportLog.setReportUrl(urlStored);
        String template = "1.ftp";
        if(json.containsKey("template")) {
            template = (String) json.get("template");
        }
        newReportLog.setTemplate(template);
        return service.save(newReportLog);
    }

}
