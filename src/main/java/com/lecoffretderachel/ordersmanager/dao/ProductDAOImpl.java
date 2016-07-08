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

	public void persistProduct(Product product) {
		sessionFactory.getCurrentSession().persist(product);
	}

	public List listProducts() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
		//(List) sessionFactory.getCurrentSession().createCriteria(getClass()).list();
	}

	public Product findProductById(Integer id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	public void updateProduct(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}
	
	public void deleteProduct(Product product) {
		sessionFactory.getCurrentSession().delete(product);
	}
}
