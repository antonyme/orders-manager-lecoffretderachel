package com.lecoffretderachel.ordersmanager.editors.product;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lecoffretderachel.ordersmanager.injectionconfig.DataBaseConfig;
import com.lecoffretderachel.ordersmanager.injectionconfig.OtherConfig;

public class TestProductView implements Lifecycle {
	private static AnnotationConfigApplicationContext context;
	
	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(DataBaseConfig.class, OtherConfig.class);
		context.registerShutdownHook();
		context.getBean(ProductController.class).show();
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
