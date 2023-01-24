package com.saison.reportgenerator.controller;


import com.saison.reportgenerator.service.Generator;
import com.saison.reportgenerator.service.impl.HTMLReportGenerator;
import com.saison.reportgenerator.service.impl.PDFReportGenerator;
import com.saison.reportgenerator.service.impl.PDFReportGeneratorRaptor;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneratorController {

    Generator generator;

    @PostMapping("/getReport")
    public @ResponseBody String getReport(@RequestBody Map<String,Object> json) throws IOException, TemplateException {
        generator = new HTMLReportGenerator();
        return (String)generator.getReport(json);
    }

    @PostMapping("/getPDF")
    public @ResponseBody String getPDF(@RequestBody Map<String,Object> json) throws IOException, TemplateException {
        generator = new PDFReportGenerator();
        return (String) generator.getReport(json);
    }

    @PostMapping("/getPDFByRaptor")
    public @ResponseBody String getPDFByRaptor(@RequestBody Map<String,Object> json) throws TemplateException, IOException {
        generator = new PDFReportGeneratorRaptor();
        return (String) generator.getReport(json);
    }

}
