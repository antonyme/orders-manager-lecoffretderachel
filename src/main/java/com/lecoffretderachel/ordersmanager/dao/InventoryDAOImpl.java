package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.lecoffretderachel.ordersmanager.model.Inventory;

public class InventoryDAOImpl implements InventoryDAO {

	private SessionFactory sessionFactory;
	
	public InventoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void persist(Inventory inventory) {
		sessionFactory.getCurrentSession().persist(inventory);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Inventory").list();
	}

	public Inventory findById(Integer id) {
		return (Inventory) sessionFactory.getCurrentSession().get(Inventory.class, id);
	}

	public void update(Inventory inventory) {
		sessionFactory.getCurrentSession().update(inventory);
	}
	
	public void delete(Inventory Inventory) {
		sessionFactory.getCurrentSession().delete(Inventory);
	}
}
