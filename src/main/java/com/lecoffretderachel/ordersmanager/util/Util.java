package com.lecoffretderachel.ordersmanager.util;

import java.util.Arrays;
import java.util.HashSet;
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
}