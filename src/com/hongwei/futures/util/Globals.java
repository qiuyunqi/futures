package com.hongwei.futures.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Globals {

	public static final String configPath = "Globals.properties";
	public static final String basePath = Globals.class.getResource("/").toString().substring(6);

	private static Properties config;// 组员

	private static Log log = LogFactory.getLog(Globals.class);
	// 加载配置文件
	static {
		try {
			config = new Properties();
			InputStream in = Globals.class.getResourceAsStream("/"
					+ configPath);
			config.load(in);
			in.close();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	/**
	 * 获取配日志文件的值
	 */
	public static String getValue(String key) {
		return config.getProperty(key);
	}

	/**
	 * 更改配置文件的值
	 */
	public static void setValue(String key, Integer value) {
		config.put(key, value.toString());
	}

	/**
	 * 恢复复默认值
	 */
	public static void reset(int i) {
		OutputStream out;
		try {
			out = new BufferedOutputStream(new FileOutputStream(basePath
					+ configPath));
			try {
				config.store(out, "save");
				out.close();
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			finally{
				out.close();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
