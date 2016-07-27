package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Product;

public interface InventoryDAO {
	void persist(Inventory inventory);
	Inventory findById(Integer id);
	List listAll();
	void update(Inventory inventory);
	void delete(Inventory inventory);
	
	Inventory findByProductAndSize(Product product, String size);
}
