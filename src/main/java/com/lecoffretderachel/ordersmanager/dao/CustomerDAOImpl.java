package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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

	@Override
	public List findByEmail(String email) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
				.add(Restrictions.eq("email", email))
				.list();
	}

	@Override
	public List findByName(String firstName, String lastName) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
				.add(Restrictions.eq("first_name", firstName))
				.add(Restrictions.eq("last_name", lastName))
				.list();
	}
}
