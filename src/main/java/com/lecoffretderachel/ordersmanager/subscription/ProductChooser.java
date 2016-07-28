package com.lecoffretderachel.ordersmanager.subscription;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.service.InventoryService;

public class ProductChooser {
	InventoryService inventoryService;
	List<Order> toFill;
	List<Inventory> currentInventory;
	
	public ProductChooser(List<Order> toFill, List<Inventory> currentInventory) {
		this.toFill = toFill;
		this.currentInventory = currentInventory;
	}
	
	public List<Order> getResult() {
		for(Order order : toFill) {
			
		}
		return toFill;
	}
}
