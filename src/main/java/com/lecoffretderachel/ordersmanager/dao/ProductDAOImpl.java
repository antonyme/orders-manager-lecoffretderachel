package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.lecoffretderachel.ordersmanager.model.Product;

public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;

	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void persist(Product product) {
		sessionFactory.getCurrentSession().persist(product);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}
	
	public Product findFirst() {
		return (Product) sessionFactory.getCurrentSession()
				.createCriteria(Product.class)
				.setFirstResult(0)
				.setMaxResults(1)
				.list()
				.get(0);
	}

	public Product findById(Integer id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	public void update(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}
	
	public void delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
	}
	
	public List findByName(String name) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Product.class)
				.add(Restrictions.eq("name", name))
				.list();
	}
}
