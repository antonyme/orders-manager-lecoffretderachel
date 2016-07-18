package com.lecoffretderachel.ordersmanager.injectionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.lecoffretderachel.ordersmanager.editors.DualListEditor;
import com.lecoffretderachel.ordersmanager.editors.customer.CustomerController;
import com.lecoffretderachel.ordersmanager.editors.customer.CustomerTableModel;
import com.lecoffretderachel.ordersmanager.editors.customer.CustomerView;
import com.lecoffretderachel.ordersmanager.editors.inventory.InventoryController;
import com.lecoffretderachel.ordersmanager.editors.inventory.InventoryTableModel;
import com.lecoffretderachel.ordersmanager.editors.inventory.InventoryView;
import com.lecoffretderachel.ordersmanager.editors.order.OrderController;
import com.lecoffretderachel.ordersmanager.editors.order.OrderTableModel;
import com.lecoffretderachel.ordersmanager.editors.order.OrderView;
import com.lecoffretderachel.ordersmanager.editors.product.ProductController;
import com.lecoffretderachel.ordersmanager.editors.product.ProductTableModel;
import com.lecoffretderachel.ordersmanager.editors.product.ProductView;
import com.lecoffretderachel.ordersmanager.editors.tag.TagController;
import com.lecoffretderachel.ordersmanager.editors.tag.TagTableModel;
import com.lecoffretderachel.ordersmanager.editors.tag.TagView;
import com.lecoffretderachel.ordersmanager.service.ListService;

@Configuration
@Import(DataBaseConfig.class)
public class EditorConfig {
	
	@Autowired
	private DataBaseConfig dataBaseConfig;
	
	@Bean
	public CustomerController getCustomerController() {
		return new CustomerController(getCustomerView(), getCustomerTableModel());
	}
	
	@Bean
	public CustomerTableModel getCustomerTableModel() {
		return new CustomerTableModel(dataBaseConfig.getCustomerService(), dataBaseConfig.getTagService());
	}
	
	@Bean
	public CustomerView getCustomerView() {
		return new CustomerView(getDualListEditorForTags(), getDualListEditorForProducts());
	}
	
	@Bean
	public InventoryController getInventoryController() {
		return new InventoryController(getInventoryView(), getInventoryTableModel());
	}
	
	@Bean
	public InventoryTableModel getInventoryTableModel() {
		return new InventoryTableModel(dataBaseConfig.getInventoryService(), dataBaseConfig.getProductService());
	}
	
	@Bean
	public InventoryView getInventoryView() {
		return new InventoryView();
	}
	
	@Bean
	public OrderController getOrderController() {
		return new OrderController(getOrderView(), getOrderTableModel());
	}
	
	@Bean
	public OrderTableModel getOrderTableModel() {
		return new OrderTableModel(dataBaseConfig.getOrderService());
	}
	
	@Bean
	public OrderView getOrderView() {
		return new OrderView();
	}
	
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
		return new ProductView(getDualListEditorForTags());
	}
	
	@Bean
	public TagController getTagController() {
		return new TagController(getTagView(), getTagTableModel());
	}
	
	@Bean
	public TagTableModel getTagTableModel() {
		return new TagTableModel(dataBaseConfig.getTagService());
	}
	
	@Bean
	public TagView getTagView() {
		return new TagView();
	}
	
	@Bean
	public DualListEditor getDualListEditorForTags() {
		return new DualListEditor((ListService) dataBaseConfig.getTagService());
	}
	
	@Bean
	public DualListEditor getDualListEditorForProducts() {
		return new DualListEditor((ListService) dataBaseConfig.getProductService());
	}
}
