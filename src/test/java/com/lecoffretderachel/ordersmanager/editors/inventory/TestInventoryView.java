package com.lecoffretderachel.ordersmanager.editors.inventory;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInventoryView implements Lifecycle {
	private static ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.registerShutdownHook();
		context.getBean(InventoryController.class).show();
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
