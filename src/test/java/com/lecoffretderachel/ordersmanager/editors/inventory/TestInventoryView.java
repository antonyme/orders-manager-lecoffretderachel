package com.lecoffretderachel.ordersmanager.editors.inventory;

import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.lecoffretderachel.ordersmanager.injectionconfig.DataBaseConfig;
import com.lecoffretderachel.ordersmanager.injectionconfig.OtherConfig;

public class TestInventoryView implements Lifecycle {
	private static AnnotationConfigApplicationContext context;
	
	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(DataBaseConfig.class, OtherConfig.class);
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
