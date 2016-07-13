package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lecoffretderachel.ordersmanager.model.Product;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Product product) {
		sessionFactory.getCurrentSession().persist(product);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
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
}
