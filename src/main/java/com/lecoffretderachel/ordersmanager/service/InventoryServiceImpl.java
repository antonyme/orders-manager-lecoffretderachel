package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.InventoryDAO;
import com.lecoffretderachel.ordersmanager.model.Inventory;

@Service("InventoryService")
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	InventoryDAO InventoryDAO;
	
	@Transactional
	public void persistInventory(Inventory Inventory) {
		InventoryDAO.persist(Inventory);
	}

	@Transactional
	public List listInventorys() {
		return InventoryDAO.listAll();
	}

	@Transactional
	public void updateInventory(Inventory Inventory) {
		InventoryDAO.update(Inventory);
	}
	
	@Transactional
	public Inventory findInventoryById(Integer id) {
		return InventoryDAO.findById(id);
	}

	@Transactional
	public void deleteInventory(Inventory Inventory) {
		InventoryDAO.delete(Inventory);
	}
}
