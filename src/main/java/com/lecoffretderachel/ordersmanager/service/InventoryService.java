package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;

public interface InventoryService {
	void persist(Inventory inventory);
	List list();
	Inventory findById(Integer i);
	void update(Inventory inventory);
	void delete(Inventory inventory);
}