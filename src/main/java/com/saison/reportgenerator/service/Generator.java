package com.saison.reportgenerator.service;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface Generator {

    Object getReport(Map<String,Object> json) throws IOException, TemplateException;

}
