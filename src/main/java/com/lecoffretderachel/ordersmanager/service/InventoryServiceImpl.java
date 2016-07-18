package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.InventoryDAO;
import com.lecoffretderachel.ordersmanager.model.Inventory;

public class InventoryServiceImpl implements InventoryService{

	InventoryDAO inventoryDAO;
	
	public InventoryServiceImpl(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
	
	@Transactional
	public void persist(Inventory inventory) {
		inventoryDAO.persist(inventory);
	}

	@Transactional
	public List list() {
		return inventoryDAO.listAll();
	}

	@Transactional
	public void update(Inventory inventory) {
		inventoryDAO.update(inventory);
	}
	
	@Transactional
	public Inventory findById(Integer id) {
		return inventoryDAO.findById(id);
	}

	@Transactional
	public void delete(Inventory inventory) {
		inventoryDAO.delete(inventory);
	}
}
