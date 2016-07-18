package com.lecoffretderachel.ordersmanager.editors.order;

import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lecoffretderachel.ordersmanager.injectionconfig.AppConfig;

public class TestOrderView implements Lifecycle {
	private static AnnotationConfigApplicationContext context;
	
	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		context.getBean(OrderController.class).show();
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
