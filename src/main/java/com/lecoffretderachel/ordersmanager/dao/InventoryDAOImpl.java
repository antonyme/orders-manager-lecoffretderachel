package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lecoffretderachel.ordersmanager.model.Inventory;

@Repository("InventoryDAO")
public class InventoryDAOImpl implements InventoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Inventory Inventory) {
		sessionFactory.getCurrentSession().persist(Inventory);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Inventory").list();
	}

	public Inventory findById(Integer id) {
		return (Inventory) sessionFactory.getCurrentSession().get(Inventory.class, id);
	}

	public void update(Inventory Inventory) {
		sessionFactory.getCurrentSession().update(Inventory);
	}
	
	public void delete(Inventory Inventory) {
		sessionFactory.getCurrentSession().delete(Inventory);
	}
}
