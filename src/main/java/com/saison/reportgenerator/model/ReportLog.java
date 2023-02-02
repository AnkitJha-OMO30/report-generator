package com.saison.reportgenerator.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ReportLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "report_log_sequence")
    @SequenceGenerator(name = "report_log_sequence", sequenceName = "report_log_sequence", allocationSize = 1)
    private Long recordId;
    private String userId;
    private Date dateStamp;
    private String template;
    private String reportUrl;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }
}
