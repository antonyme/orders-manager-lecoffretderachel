package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;

public interface InventoryService {
	void persistInventory(Inventory Inventory);
	List listInventorys();
	Inventory findInventoryById(Integer i);
	void updateInventory(Inventory Inventory);
	void deleteInventory(Inventory Inventory);
}