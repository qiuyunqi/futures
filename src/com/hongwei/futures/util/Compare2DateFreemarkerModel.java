package com.hongwei.futures.util;


import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 *类说明
 *@author yancheng
 *@version 1.0 创建时间: May 22, 2010 2:10:38 PM
 **/
public class Compare2DateFreemarkerModel implements TemplateMethodModel {
	 @Override
	public Object exec(List list) throws TemplateModelException {
	        if (list == null || list.size() != 2) {
	            throw new TemplateModelException("请输入两个日期");
	        }
	        Object timeOne = list.get(0);
	        Object timeTwo = list.get(1);
	        Date dateOne;
	        Date dateTwo;
	        if (timeOne instanceof String) {
	            dateOne = DateUtil.getDateFromString(timeOne.toString());
	        } else {
	            dateOne = (Date) timeOne;
	        }
	        if (timeTwo instanceof String) {
	        	dateTwo = DateUtil.getDateFromString(timeTwo.toString());
	        } else {
	        	dateTwo = (Date) timeOne;
	        }
	        if(dateOne.getTime()-dateTwo.getTime()>=0){
	        	return true;
	        }else{
	        	return false;
	        } 
	 }
}

