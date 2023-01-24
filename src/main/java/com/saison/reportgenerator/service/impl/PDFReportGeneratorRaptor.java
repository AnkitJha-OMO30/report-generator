package com.saison.reportgenerator.service.impl;

import com.docraptor.ApiClient;
import com.docraptor.ApiException;
import com.docraptor.Doc;
import com.docraptor.DocApi;
import com.saison.reportgenerator.ReportGeneratorConfiguration;
import com.saison.reportgenerator.service.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Map;

public class PDFReportGeneratorRaptor implements Generator {


    @Override
    public Object getReport(Map<String, Object> json) throws IOException, TemplateException {
        Configuration cfg = getConfiguration();

//        Path customPath = Path.of("/Users/ankitjha/Desktop/Repos/report-generator/src/main/resources/image/Rupee.jpg");
//        json.put("imgSrc",customPath.toUri());

        String currentHTMLCode = getHtmlCode(cfg,json);

        DocApi docraptor = new DocApi();
        ApiClient client = docraptor.getApiClient();
        client.setUsername("0NuBIYv9isTnmVuz820l");
        Doc doc = new Doc();
        doc.setTest(true);
        doc.setDocumentContent(currentHTMLCode);
        doc.setDocumentType(Doc.DocumentTypeEnum.PDF);
        doc.setName("TransactionReport.pdf");
        doc.setJavascript(true);
        byte[] document;
        try {
             document = docraptor.createDoc(doc);
//            File directory = new File("/Users/ankitjha/Desktop/Reports");
//            File pdfFile = File.createTempFile("TransactionReport",".pdf",directory);
        } catch (ApiException e) {
            document = null;
            e.printStackTrace();
            System.out.println("Exception in Generating PDF Doc");
        }
        File directory = new File("/Users/ankitjha/Desktop/Reports");
        File pdfFile = File.createTempFile("Template3",".pdf",directory);
        if(document != null)
            Files.write(pdfFile.toPath(), document);
        return directory.toString();
    }

    protected Configuration getConfiguration() throws IOException {
        return ReportGeneratorConfiguration.getTemplateConfiguration();
    }

    protected String getHtmlCode(Configuration cfg, Map<String,Object> json) throws IOException, TemplateException {
        Writer htmlOut = new StringWriter();
        Template freeMarkerTemplate = cfg.getTemplate("Template3.ftp");
        freeMarkerTemplate.process(json,htmlOut);
        return htmlOut.toString();
    }
}
