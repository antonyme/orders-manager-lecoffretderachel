package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.InventoryDAO;
import com.lecoffretderachel.ordersmanager.model.Inventory;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	InventoryDAO inventoryDAO;
	
	@Transactional
	public void persistInventory(Inventory inventory) {
		inventoryDAO.persist(inventory);
	}

	@Transactional
	public List listInventorys() {
		return inventoryDAO.listAll();
	}

	@Transactional
	public void updateInventory(Inventory inventory) {
		inventoryDAO.update(inventory);
	}
	
	@Transactional
	public Inventory findInventoryById(Integer id) {
		return inventoryDAO.findById(id);
	}

	@Transactional
	public void deleteInventory(Inventory inventory) {
		inventoryDAO.delete(inventory);
	}
}
