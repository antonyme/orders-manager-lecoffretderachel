package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.List;

import com.lecoffretderachel.ordersmanager.model.OrderProduct;
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
	
	public void matchDirectOrdersProducts() throws IllegalArgumentException {
		for(OrderModelBuilder order : directOrderBuilderList) {
			matchOrderProducts(order);
		}
	}
	
	private void matchOrderProducts(OrderModelBuilder order) throws IllegalArgumentException {
		List<OrderProduct> productsInclude = new ArrayList<>();
		for(ItemModel item : order.getProductInclude()) {
			Product prod = productService.matchByName(item.getName());
			OrderProduct productInclude = new OrderProduct();
			productInclude.setProduct(prod);
			productInclude.setOrder(order.getNewOrder());
			productInclude.setProductSize(item.getSize());
			productInclude.setQuantity(item.getQuantity());
			productsInclude.add(productInclude);
		}
		order.getNewOrder().setOrderProductInclude(productsInclude);
	}
}
