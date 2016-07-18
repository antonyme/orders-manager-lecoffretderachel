package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.lecoffretderachel.ordersmanager.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private SessionFactory sessionFactory;
	
	public CustomerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void persist(Customer customer) {
		sessionFactory.getCurrentSession().persist(customer);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Customer").list();
	}

	public Customer findById(Integer id) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
	}

	public void update(Customer customer) {
		sessionFactory.getCurrentSession().update(customer);
	}
	
	public void delete(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
	}
}
