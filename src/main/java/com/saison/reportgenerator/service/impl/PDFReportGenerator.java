package com.saison.reportgenerator.service.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.saison.reportgenerator.ReportGeneratorConfiguration;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class PDFReportGenerator implements Generator {

    Configuration cfg;

    @Override
    public Object getReport(Map<String, Object> json) throws IOException, TemplateException {
        cfg = getConfiguration();

        Path customPath = Path.of("src/main/resources/image/Rupee.jpg");
        json.put("imgSrc",customPath.toUri());

        String template = "1.ftp";
        if(json.containsKey("template"))
            template = (String)json.get("template");

        String fileContent = getHtmlPage(cfg, template, json);

        File directory = new File("/Users/ankitjha/Desktop/Reports");
        File pdfFile = File.createTempFile("TransactionReport",".pdf",directory);
        OutputStream oStream = new FileOutputStream(pdfFile);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode()
                .withHtmlContent(fileContent,null)
                .toStream(oStream)
                .run();
        oStream.close();
        return pdfFile.getPath();
    }

    public static Configuration getConfiguration() throws IOException {
        return ReportGeneratorConfiguration.getTemplateConfiguration();
    }

    public static String getHtmlPage(Configuration cfg, String templatePath, Map<String,Object> json) throws IOException,
            TemplateException {
        Template freemarkerTemplate = cfg.getTemplate(templatePath);
        Writer htmlOut = new StringWriter();
        freemarkerTemplate.process(json,htmlOut);
        String fileContent = htmlOut.toString();
        htmlOut.close();
        return fileContent;
    }
}
