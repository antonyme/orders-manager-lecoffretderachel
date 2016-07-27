package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.OrderProduct;
import com.lecoffretderachel.ordersmanager.model.Product;

public interface InventoryService {
	void persist(Inventory inventory);
	List list();
	Inventory findById(Integer i);
	void update(Inventory inventory);
	void delete(Inventory inventory);

	Inventory findByProductAndSize(Product product, String size);
	Boolean removeFromInventory(OrderProduct toRemove);
}