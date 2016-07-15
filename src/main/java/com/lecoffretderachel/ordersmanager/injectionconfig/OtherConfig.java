package com.lecoffretderachel.ordersmanager.injectionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.lecoffretderachel.ordersmanager.editors.DualListEditor;
import com.lecoffretderachel.ordersmanager.editors.product.ProductController;
import com.lecoffretderachel.ordersmanager.editors.product.ProductTableModel;
import com.lecoffretderachel.ordersmanager.editors.product.ProductView;
import com.lecoffretderachel.ordersmanager.service.ListService;

@Configuration
@Import(DataBaseConfig.class)
public class OtherConfig {
	
	@Autowired
	private DataBaseConfig dataBaseConfig;
	
	@Bean
	public ProductController getProductController() {
		return new ProductController(getProductView(), getProductTableModel());
	}
	
	@Bean
	public ProductTableModel getProductTableModel() {
		return new ProductTableModel(dataBaseConfig.getProductService(), dataBaseConfig.getTagService());
	}
	
	@Bean
	public ProductView getProductView() {
		return new ProductView(getDualListEditor());
	}
	
	@Bean
	public DualListEditor getDualListEditor() {
		return new DualListEditor((ListService) dataBaseConfig.getTagService());
	}
}
