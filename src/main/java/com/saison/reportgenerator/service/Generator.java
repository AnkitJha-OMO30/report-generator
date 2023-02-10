package com.saison.reportgenerator.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface Generator {

    Object getReport(Object json) throws IOException, TemplateException;

}
