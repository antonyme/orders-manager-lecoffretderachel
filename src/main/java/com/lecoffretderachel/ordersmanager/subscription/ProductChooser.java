package com.lecoffretderachel.ordersmanager.subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lecoffretderachel.ordersmanager.imports.order.OrderModelBuilder;
import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.model.OrderProduct;
import com.lecoffretderachel.ordersmanager.service.InventoryService;

public class ProductChooser {
	InventoryService inventoryService;
	List<OrderModelBuilder> toFill;
	List<Inventory> currentInventory;
	Integer totalInventory;
	List<Inventory> pool = new ArrayList<>();
	Integer poolSize;
	Random rand = new Random();
	
	public ProductChooser(List<OrderModelBuilder> toFill, List<Inventory> currentInventory) {
		this.toFill = toFill;
		this.currentInventory = currentInventory;
		totalInventory = 0;
		currentInventory.forEach((entry) -> totalInventory += entry.getQuantity());
	}
	
	public List<Order> getResult() {
		for(OrderModelBuilder order : toFill) {
			for(int i = 0; i < 2; i++) {
				createPool(order);
				if(poolSize == 0) {
					order.getNewOrder().getOrderProductInclude().add(null);
				}
				else {
					int choosen = getRandomly();
					order.getNewOrder().getOrderProductInclude()
					.add(getOrderProdFromInventory(order, currentInventory.get(choosen)));
					currentInventory.get(choosen)
					.setQuantity(currentInventory.get(choosen).getQuantity() - 1);
				}
			}
		}
		List<Order> res = new ArrayList<>();
		toFill.forEach((order) -> res.add(order.getNewOrder()));
		return res;
	}
	
	private void createPool(OrderModelBuilder currentOrder) {
		pool.clear();
		poolSize = 0;
		for(Inventory inventory : currentInventory) {
			if(inventory.getProductSize().equals(currentOrder.getProductInclude().get(0).getSize())
					&& !inventory.getProduct().getName().equals("Recu en cadeau")
					&& !inventory.getProduct().getName().equals("offrir en cadeau")) {
				pool.add(inventory);
				poolSize += inventory.getQuantity();
			}
		}
		//currentOrder.getNewOrder().getOrderOwner().getProductsExcluded()
		
	}
	
	private int getRandomly() {
		int position = rand.nextInt(poolSize) + 1;
		int i;
		for(i = 0; position > 0 && i < pool.size(); i++) {
			position -= pool.get(i).getQuantity();
		}
		return currentInventory.indexOf(pool.get(i - 1));
	}
	
	private OrderProduct getOrderProdFromInventory(OrderModelBuilder order, Inventory inventory) {
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder(order.getNewOrder());
		orderProduct.setProduct(inventory.getProduct());
		orderProduct.setProductSize(inventory.getProductSize());
		orderProduct.setQuantity(1);
		return orderProduct;
	}
}
