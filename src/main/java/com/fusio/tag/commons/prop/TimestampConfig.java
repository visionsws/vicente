package com.fusio.tag.commons.prop;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class TimestampConfig {
	public static final ConcurrentHashMap<String, String> props = new ConcurrentHashMap<String, String>();
	private static final String filename = "/timestamp.properties";
	
	public static String get(String key) {
		return props.get(key);
	}
	
	static {
		initConstVars(filename);
	}

	// 读取properties文件
	public static Properties readProperties(String propFile) {
		Properties properties = new Properties();
		try {
			InputStream in = TimestampConfig.class.getResourceAsStream(propFile);
			properties.load(in);
		} catch (Exception e) {
			properties = null;
		}
		return properties;
	}

	public static void initConstVars() {
		initConstVars(filename);
	}

	// 初始化和刷新
	public static void initConstVars(String path) {
		try {
			props.clear();
			Properties prop = readProperties(path);
			Enumeration<String> enumNames = (Enumeration<String>) prop.propertyNames();// #注释的会被忽略
			while (enumNames.hasMoreElements()) {
				String key = enumNames.nextElement();
				String value = prop.getProperty(key);
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				props.put(key, value);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("initConstVars不支持该编码");
		}
	}

	public static void printConst() {
		Iterator it = props.keySet().iterator();
		for (; it.hasNext();) {
			String key = (String) it.next();
			String value = props.get(key);
			System.out.println(key + "<-->" + value);
		}
	}
}
