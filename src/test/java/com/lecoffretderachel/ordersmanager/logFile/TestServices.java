package com.lecoffretderachel.ordersmanager.logFile;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lecoffretderachel.ordersmanager.injectionconfig.AppConfig;
import com.lecoffretderachel.ordersmanager.service.UtilService;

public class TestServices {
	private static AnnotationConfigApplicationContext context;
	
	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		UtilService utilService = context.getBean(UtilService.class);
		List<String> tables = utilService.getTables();
		List<String> columns;
		String create;
//		List<Object[]> data;
		for(String table : tables) {
			columns = utilService.getColumns(table);
			create = utilService.showCreateTable(table);
//			data = utilService.getData(table);
			System.out.println("Table name : " + table);
			System.out.println();
			System.out.println("Columns : " + columns);
			System.out.println("Create : " + create);
/*			System.out.println("Data :");
			for(Object[] line : data) {
				for(Object elem : line) {
					if (elem != null) {
						System.out.print(elem.toString() + " | ");
					}
					else {
						System.out.print("null | ");
					}
				}
				System.out.println();
			}*/
		}
	}
}
