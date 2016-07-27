package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Customer;
import com.lecoffretderachel.ordersmanager.model.OrderProduct;
import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.CustomerService;
import com.lecoffretderachel.ordersmanager.service.InventoryService;
import com.lecoffretderachel.ordersmanager.service.OrderService;
import com.lecoffretderachel.ordersmanager.service.ProductService;

public class OrderImportModel {
	ProductService productService;
	CustomerService customerService;
	OrderService orderService;
	InventoryService inventoryService;
	List<OrderCSV> orderCSVList;
	List<OrderModelBuilder> subOrderBuilderList = new ArrayList<>();
	List<OrderModelBuilder> directOrderBuilderList = new ArrayList<>();
	
	public OrderImportModel(ProductService productService, CustomerService customerService, OrderService orderService, InventoryService inventoryService) {
		this.productService = productService;
		this.customerService = customerService;
		this.orderService = orderService;
		this.inventoryService = inventoryService;
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
	
	public void matchOrdersClient() throws IllegalArgumentException {
		for(OrderModelBuilder order : directOrderBuilderList) {
			matchOrderClient(order);
		}
		for(OrderModelBuilder order : subOrderBuilderList) {
			matchOrderClient(order);
		}
	}
	
	private void matchOrderClient(OrderModelBuilder order) throws IllegalArgumentException {
		Customer result = customerService.findByEmail(order.getNewOrder().getBillingEmail());
		if(result != null) {
			order.getNewOrder().setOrderOwner(result);
		}
		else {
			List results = customerService.findByName(order.getNewOrder().getShippingFirstName(),
					order.getNewOrder().getShippingLastName());
			if(results.size() > 1) {
				order.getNewOrder().setOrderOwner((Customer) results.get(0));
			}
			else {
				Customer newCustomer = new Customer(order.getNewOrder().getBillingEmail(),
						order.getNewOrder().getShippingFirstName(),
						order.getNewOrder().getShippingLastName());
				customerService.persist(newCustomer);
				order.getNewOrder().setOrderOwner(newCustomer);
			}
		}
	}
	
	public void persistDirectOrders() {
		for(OrderModelBuilder order : directOrderBuilderList) {
			
			orderService.persist(order.getNewOrder());
		}
	}
}
