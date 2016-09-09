package com.lecoffretderachel.ordersmanager.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Util {
	public static String setToString(Set<?> set) {
		StringBuilder res = new StringBuilder();
		for(Object item : set) {
			res.append(item.toString() + ",");
		}
		if(res.length() > 1) res.deleteCharAt(res.length() - 1);
		return res.toString();
	}
	
	public static Set<String> StringToSet(String str) {
		return new HashSet<>(Arrays.asList(str.split(",")));
	}
	
	public static String addEscapeChar(String str) {
		return str.replaceAll("'", "\\\\'")
				.replaceAll("\"", "\\\\\"");
	}
	
	public static Properties loadProperties() {
		Properties properties = new Properties();
		String fileName = "/config.properties";
		try(InputStream inputStream = Util.class.getResourceAsStream(fileName)) {
			if(inputStream != null) {
				properties.load(inputStream);
			}
			else {
				throw new FileNotFoundException("propertie file '" + fileName + "' not found");
			}
		}
		catch (Exception e) {
			System.out.println("Exception : " + e);
		}
		return properties;
	}
}