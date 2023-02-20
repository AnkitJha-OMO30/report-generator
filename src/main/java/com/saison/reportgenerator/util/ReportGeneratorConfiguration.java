package com.saison.reportgenerator.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

public class ReportGeneratorConfiguration {

    private static Configuration cfg = null;

    private ReportGeneratorConfiguration() throws IOException{
        cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
    }

    public static Configuration getTemplateConfiguration() throws IOException {
        if(cfg == null)
        {
            new ReportGeneratorConfiguration();
        }
        return cfg;
    }
}
