package com.saison.reportgenerator.service.impl;

import com.saison.reportgenerator.ReportGeneratorConfiguration;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class HTMLReportGenerator implements Generator {

    Configuration cfg;

    @Override
    public Object getReport(Map<String,Object> json) throws IOException, TemplateException {
        if( cfg == null ) {
            cfg = ReportGeneratorConfiguration.getTemplateConfiguration();
        }
        Path customPath = Path.of("/Users/ankitjha/Desktop/Repos/report-generator/src/main/resources/image/Rupee.jpg");
        json.put("imgSrc",customPath.toUri());
        //System.out.println("Fetching the template");
        //Template freemarkerTemplate = cfg.getTemplate("ReportTemplate.ftp");
        Template freemarkerTemplate = cfg.getTemplate("DecoratedReportTemplate.ftp");
        File tempFile = File.createTempFile("tempHTML",".html");
        Writer out = new FileWriter(tempFile);
        freemarkerTemplate.process(json,out);
        /*Writer out = new OutputStreamWriter(System.out);
        freemarkerTemplate.process(json, out);*/
        String fileContent = "No Content Currently";
        fileContent = Files.readString(Path.of(tempFile.getPath()));
        Files.delete(Path.of(tempFile.getPath()));
        out.close();
        return fileContent;
    }
}
