package com.lecoffretderachel.ordersmanager.injectionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.lecoffretderachel.ordersmanager.main.MainController;
import com.lecoffretderachel.ordersmanager.main.MainView;

@Configuration
@Import({EditorConfig.class, ImportConfig.class})
public class AppConfig {
	@Autowired
	private EditorConfig editorConfig;
	@Autowired
	private ImportConfig importConfig;
	@Autowired
	private DataBaseConfig dataBaseConfig;
	
	@Bean
	public MainController getMainController() {
		return new MainController(getMainView(),
				editorConfig.getCustomerController(),
				editorConfig.getInventoryController(),
				editorConfig.getOrderController(),
				editorConfig.getProductController(),
				editorConfig.getTagController(),
				importConfig.getOrderImportController(),
				dataBaseConfig.getDb2sql());
	}
	
	@Bean
	public MainView getMainView() {
		return new MainView();
	}
}
