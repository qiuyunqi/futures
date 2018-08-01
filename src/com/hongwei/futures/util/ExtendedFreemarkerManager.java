package com.hongwei.futures.util;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 *类说明
 *@author yancheng
 *@version 1.0 创建时间: May 22, 2010 11:18:44 AM
 **/
public class ExtendedFreemarkerManager extends FreemarkerManager  {
	@Override
	protected Configuration createConfiguration(ServletContext servletContext)
		throws TemplateException {
		Configuration configuration = super.createConfiguration(servletContext);
		//获得当前时间
		configuration.setSharedVariable("dateTime", new DatetimeFreemarkerModel() );
		//是否大于后者时间
		configuration.setSharedVariable("isGltDate", new Compare2DateFreemarkerModel());
		return configuration;
	}
}
