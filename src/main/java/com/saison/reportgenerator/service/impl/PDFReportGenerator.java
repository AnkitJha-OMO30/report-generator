package com.saison.reportgenerator.service.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.saison.reportgenerator.ReportGeneratorConfiguration;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PDFReportGenerator implements Generator {

    Configuration cfg;

    @Override
    public Object getReport(Map<String, Object> json) throws IOException, TemplateException {
        cfg = ReportGeneratorConfiguration.getTemplateConfiguration();
        Path customPath = Path.of("/Users/ankitjha/Desktop/Repos/report-generator/src/main/resources/image/Rupee.jpg");
        json.put("imgSrc",customPath.toUri());
        //System.out.println("Fetching the template");
        //Template freemarkerTemplate = cfg.getTemplate("2.ftp");

        String template = "1.ftp";
        if(json.containsKey("template"))
            template = (String)json.get("template");

        Template freemarkerTemplate = cfg.getTemplate(template);
        File tempFile = File.createTempFile("tempHTML",".html");
        Writer out = new FileWriter(tempFile);
        freemarkerTemplate.process(json,out);
        String fileContent;
        fileContent = Files.readString(Path.of(tempFile.getPath()));
        Files.delete(Path.of(tempFile.getPath()));
        out.close();
        File directory = new File("/Users/ankitjha/Desktop/Reports");
        File pdfFile = File.createTempFile("Report",".pdf",directory);
        OutputStream oStream = new FileOutputStream(pdfFile);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode()
                .withHtmlContent(fileContent,null)
                .toStream(oStream)
                .run();
        oStream.close();
        return pdfFile.getPath();



    }
}
