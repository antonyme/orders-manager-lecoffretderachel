package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lecoffretderachel.ordersmanager.logFile.LogWriter;
import com.lecoffretderachel.ordersmanager.logFile.Util;
import com.lecoffretderachel.ordersmanager.model.Customer;
import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.model.OrderProduct;
import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.CustomerService;
import com.lecoffretderachel.ordersmanager.service.InventoryService;
import com.lecoffretderachel.ordersmanager.service.OrderService;
import com.lecoffretderachel.ordersmanager.service.ProductService;
import com.lecoffretderachel.ordersmanager.subscription.ProductChooser;

public class OrderImportModel {
	ProductService productService;
	CustomerService customerService;
	OrderService orderService;
	InventoryService inventoryService;
	List<OrderCSV> orderCSVList;
	List<OrderModelBuilder> subOrderBuilderList = new ArrayList<>();
	List<OrderModelBuilder> directOrderBuilderList = new ArrayList<>();
	List<Order> subOrderList = new ArrayList<>();
	List<String[]> refusedProduct = new ArrayList<>();
	List<String[]> addedDirectProduct = new ArrayList<>();
	
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
	
	public List<Order> getSubOrderList() {
		List<Order> res = new ArrayList<>();
		subOrderBuilderList.forEach((order) -> res.add(order.getNewOrder()));
		return res;
	}
	
	public int getSubOrderMaxItemCount() {
		List<Integer> sizes = new ArrayList<>();
		subOrderBuilderList.forEach((order) -> sizes.add(order.getNewOrder().getOrderProductInclude().size()));
		return Collections.max(sizes);
	}
	
	public List<OrderProduct> getPossibleOrderProduct() {
		List<OrderProduct> res = new ArrayList<>();
		List<Inventory> inventories = inventoryService.list();
		for(Inventory inventory : inventories) {
			if(inventory.getQuantity() > 0) {
				OrderProduct toAdd = new OrderProduct();
				toAdd.setProduct(inventory.getProduct());
				toAdd.setProductSize(inventory.getProductSize());
				toAdd.setQuantity(1);
				res.add(toAdd);
			}
		}
		return res;
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
			List<OrderProduct> toRemoveFromOrder = new ArrayList<>();
			for(OrderProduct productInclude : order.getNewOrder().getOrderProductInclude()) {
				if(!inventoryService.removeFromInventory(productInclude)) {
					refusedProduct.add(
							Util.orderProductToString(order.getNewOrder(), productInclude));
				}
				else {
					addedDirectProduct.add(
							Util.orderProductToString(order.getNewOrder(), productInclude));
				}
			}
			order.getNewOrder().getOrderProductInclude().removeAll(toRemoveFromOrder);
			orderService.persist(order.getNewOrder());
		}
	}
	
	public void suggestSubOrderProducts() {
		subOrderBuilderList.forEach((order) -> subOrderList.add(order.getNewOrder()));
		ProductChooser chooser = new ProductChooser(subOrderList, inventoryService.list());
		subOrderList = chooser.getResult();
	}
	
	public void writeLogs() {
		LogWriter.write("refused products", refusedProduct);
		LogWriter.write("added direct", addedDirectProduct);
	}
}
