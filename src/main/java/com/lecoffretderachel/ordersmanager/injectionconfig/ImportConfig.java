package com.lecoffretderachel.ordersmanager.injectionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.lecoffretderachel.ordersmanager.imports.order.OrderImportController;
import com.lecoffretderachel.ordersmanager.imports.order.OrderImportModel;
import com.lecoffretderachel.ordersmanager.imports.order.OrderImportView;

@Configuration
@Import(DataBaseConfig.class)
public class ImportConfig {
	@Autowired
	private DataBaseConfig dataBaseConfig;
	
	@Bean
	public OrderImportController getOrderImportController() {
		return new OrderImportController(getOrderImportView(), getOrderImportModel());
	}
	
	@Bean
	public OrderImportView getOrderImportView() {
		return new OrderImportView();
	}
	
	@Bean
	public OrderImportModel getOrderImportModel() {
		return new OrderImportModel(dataBaseConfig.getProductService(), dataBaseConfig.getCustomerService());
	}
}
