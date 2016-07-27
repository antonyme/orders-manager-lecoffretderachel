package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Product;

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

	public Inventory findByProductAndSize(Product product, String size) {
		return (Inventory) sessionFactory.getCurrentSession()
				.createCriteria(Inventory.class)
				.add(Restrictions.eq("product", product))
				.add(Restrictions.eq("productSize", size))
				.list().get(0);
	}
}
