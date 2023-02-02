package com.saison.reportgenerator.controller;


import com.saison.reportgenerator.model.ReportLog;
import com.saison.reportgenerator.service.Generator;
import com.saison.reportgenerator.service.ReportLogService;
import com.saison.reportgenerator.service.SaveReportLog;
import com.saison.reportgenerator.service.impl.CSVReportGenerator;
import com.saison.reportgenerator.service.impl.HTMLReportGenerator;
import com.saison.reportgenerator.service.impl.PDFReportGenerator;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneratorController {

    @Autowired
    ReportLogService reportLogService;

    Generator generator;

    @PostMapping("/getReport")
    public @ResponseBody String getReport(@RequestBody Map<String,Object> json) throws IOException, TemplateException {
        generator = new HTMLReportGenerator();
        return (String)generator.getReport(json);
    }

    @PostMapping("/getPDF")
    public @ResponseBody String getPDF(@RequestBody Map<String,Object> json) throws IOException, TemplateException {
        generator = new PDFReportGenerator();
        String url = (String) generator.getReport(json);
        ReportLog logsSaved = SaveReportLog.saveLogs(reportLogService, url, json);
        return logsSaved.getReportUrl();
    }



    @PostMapping("/getCSV")
    public String getCSV(@RequestBody Map<String,Object> json) throws TemplateException, IOException {
        generator = new CSVReportGenerator();
        return (String) generator.getReport(json);
    }

    /*@PostMapping("/getUserCharts")
    public String getCharts(@RequestBody Map<String,Object> json) {
        return "Not Operational Yet";
    }*/

    /*@PostMapping("/getPDFByRaptor")
    public @ResponseBody String getPDFByRaptor(@RequestBody Map<String,Object> json) throws TemplateException, IOException {
        generator = new PDFReportGeneratorRaptor();
        return (String) generator.getReport(json);
    }*/

}
