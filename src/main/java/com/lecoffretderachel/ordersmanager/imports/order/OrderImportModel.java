package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.ProductService;

public class OrderImportModel {
	ProductService productService;
	List<OrderCSV> orderCSVList;
	List<OrderModelBuilder> subOrderBuilderList = new ArrayList<>();
	List<OrderModelBuilder> directOrderBuilderList = new ArrayList<>();
	
	public OrderImportModel(ProductService productService) {
		this.productService = productService;
	}
	
	public List<OrderCSV> getOrderCSVList() {
		return orderCSVList;
	}
	public void setOrderCSVList(List<OrderCSV> orderCSVList) {
		this.orderCSVList = orderCSVList;
	}
	
	public List<OrderModelBuilder> getOrderBuilderList(Boolean subscription) {
		if(subscription) {
			return subOrderBuilderList;
		}
		else {
			return directOrderBuilderList;
		}
	}
	
	public void matchOrderProducts(OrderModelBuilder order) throws IllegalArgumentException {
		List<Product> orderProducts = new ArrayList<>();
		order.getProductInclude().forEach((product) ->
					orderProducts.add(productService.matchByName(product.getName())));
	}
}
