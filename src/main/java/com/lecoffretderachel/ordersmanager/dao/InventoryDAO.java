package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;

public interface InventoryDAO {
	void persist(Inventory Inventory);
	Inventory findById(Integer id);
	List listAll();
	void update(Inventory Inventory);
	void delete(Inventory Inventory);
}
