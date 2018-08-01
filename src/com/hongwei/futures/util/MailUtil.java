package com.hongwei.futures.util;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 *类说明
 *@author yancheng
 *@version 1.0 创建时间: May 8, 2010 11:30:12 AM
 **/
public class MailUtil {
	/**
	 * mail连接
	 * @param userName
	 * @return
	 */
	public static String getMailHttpHref(String userName){
		String httpHref = null;
		try {
			String type =  userName.substring(userName.lastIndexOf("@")+1,userName.lastIndexOf("."))
			        .toLowerCase();
			ResourceBundle rb = ResourceBundle.getBundle("mail");
			Enumeration<String> enumeration =  rb.getKeys();
			while(enumeration.hasMoreElements()){
				String typeName = enumeration.nextElement();
			
				if(typeName.toLowerCase().equals(type)){
					httpHref = rb.getObject(typeName).toString();
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return httpHref;
	}
}
