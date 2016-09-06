package com.lecoffretderachel.ordersmanager.logFile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lecoffretderachel.ordersmanager.injectionconfig.AppConfig;

public class TestDb2sql {
	private static AnnotationConfigApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		Db2sql db2sql = context.getBean(Db2sql.class);
		db2sql.dumpDatabase("testBackUp");
	}
}
