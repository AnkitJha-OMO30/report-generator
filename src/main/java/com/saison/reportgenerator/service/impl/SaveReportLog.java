package com.saison.reportgenerator.service.impl;

import com.saison.reportgenerator.model.ReportLog;
import com.saison.reportgenerator.service.ReportLogService;

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
        newReportLog.setFileType("PDF");
        return service.save(newReportLog);
    }

}
