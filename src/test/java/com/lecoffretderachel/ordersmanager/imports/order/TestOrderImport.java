package com.lecoffretderachel.ordersmanager.imports.order;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lecoffretderachel.ordersmanager.injectionconfig.AppConfig;

public class TestOrderImport {
	private static AnnotationConfigApplicationContext context;
	
	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		context.getBean(OrderImportController.class).show();
	}

	public boolean isRunning() {
		return false;
	}

	public void start() {
	}

	public void stop() {
		context.close();
	}
}
