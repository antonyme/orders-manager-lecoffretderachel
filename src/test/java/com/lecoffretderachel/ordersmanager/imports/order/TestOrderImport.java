package com.lecoffretderachel.ordersmanager.imports.order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lecoffretderachel.ordersmanager.editors.customer.CustomerController;
import com.lecoffretderachel.ordersmanager.injectionconfig.AppConfig;
import com.lecoffretderachel.ordersmanager.injectionconfig.ImportConfig;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

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
