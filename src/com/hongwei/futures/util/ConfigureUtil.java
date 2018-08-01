package com.hongwei.futures.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.codehaus.xfire.client.Client;

public class ConfigureUtil {
	
	/**
	 * 获取配置文件
	 * @param fileName		配置文件的名称  配置文件必须是在 config下面
	 * @return
	 * @throws IOException
	 */
	public static Properties getValue(String fileName) throws IOException {
		InputStream in = Client.class.getClassLoader().getResourceAsStream(fileName);
		Properties properties = new Properties();
		properties.load(in);
		return properties;
	}
	
}
