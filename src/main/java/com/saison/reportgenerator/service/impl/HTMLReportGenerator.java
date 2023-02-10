package com.saison.reportgenerator.service.impl;

import com.saison.reportgenerator.ReportGeneratorConfiguration;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class HTMLReportGenerator implements Generator {

    Configuration cfg;

    @Override
    public Object getReport(Object jsonExternal) throws IOException, TemplateException {
        Map<String, Object> json = (Map<String, Object>)jsonExternal;
        cfg = getConfiguration();

        Path customPath = Path.of("src/main/resources/image/Rupee.jpg");
        json.put("imgSrc",customPath.toUri());

        String template = "1.ftp";
        if(json.containsKey("template"))
            template = (String)json.get("template");

        Template freemarkerTemplate = cfg.getTemplate(template);
        Writer htmlOut = new StringWriter();
        freemarkerTemplate.process(json,htmlOut);
        String htmlContent = htmlOut.toString();
        htmlOut.close();
        return htmlContent;

    }

    public static Configuration getConfiguration() throws IOException {
        return ReportGeneratorConfiguration.getTemplateConfiguration();
    }
}
