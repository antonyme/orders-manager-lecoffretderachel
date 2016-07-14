package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;

public interface InventoryDAO {
	void persist(Inventory inventory);
	Inventory findById(Integer id);
	List listAll();
	void update(Inventory inventory);
	void delete(Inventory inventory);
}
