package com.saison.reportgenerator.service.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import com.saison.reportgenerator.util.ReportGeneratorConfiguration;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class PDFReportGenerator implements Generator {

    Configuration cfg;

    public HttpClientErrorException validateJSON(Map<String,Object> json)
    {
        if(!json.containsKey("userId")) {
            return new HttpClientErrorException(HttpStatus.BAD_REQUEST,"JSON is missing userId Field");
        }
        if(!json.containsKey("transactions")) {
            return new HttpClientErrorException(HttpStatus.BAD_REQUEST,"JSON is missing transactions fields");
        }
        return null;
    }

    @Override
    public Object getReport(Object jsonExternal) throws IOException, TemplateException {
        Map<String,Object> json = (Map<String, Object>) jsonExternal;
        cfg = getConfiguration();

        HttpClientErrorException exception = validateJSON(json);
        if(exception!=null)
        {
            return exception;
        }

        Path customPath = Path.of("src/main/resources/image/Rupee.jpg");
        json.put("imgSrc",customPath.toUri());

        String template = "1.ftp";
        if(json.containsKey("template"))
            template = (String)json.get("template");

        String fileContent = getHtmlPage(cfg, template, json);

        File directory = new File("/Users/ankitjha/Desktop/Reports");

        String fileName = pdfFileName(json);
        File pdfFile = File.createTempFile(fileName,".pdf",directory);

        OutputStream oStream = new FileOutputStream(pdfFile);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode()
                .withHtmlContent(fileContent,null)
                .toStream(oStream)
                .useSVGDrawer(new BatikSVGDrawer())
                .run();
       /* DefaultObjectDrawerFactory factory = new DefaultObjectDrawerFactory();
        factory.registerDrawer("jfreechart/pie", new JFreeChartPieDiagramObjectDrawer());
        factory.registerDrawer("jfreechart/bar", new JFreeChartBarDiagramObjectDrawer());
        builder.useObjectDrawerFactory(factory)
                .run();*/
        oStream.close();
        return pdfFile.getPath();
    }

    public static Configuration getConfiguration() throws IOException {
        return ReportGeneratorConfiguration.getTemplateConfiguration();
    }

    public static String pdfFileName(Map<String,Object> json )
    {
        String fileName = "TransactionReport";
        if(!json.containsKey("pdfName")) {
            return fileName;
        }
        if(json.get("pdfName").getClass() == String.class) {
            fileName = (String)json.get("pdfName");
        }
        if(fileName.length()<3) {
            fileName = "TransactionReport";
        }
        return fileName;
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
