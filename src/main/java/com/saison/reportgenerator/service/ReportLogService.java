package com.saison.reportgenerator.service;

import com.saison.reportgenerator.model.ReportLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ReportLogService extends JpaRepository<ReportLog, Long> {

    Iterable<ReportLog> findByUserId(String userId);
    Iterable<ReportLog> findAllByDateStampBetween(Date startDate, Date endDate);



}
