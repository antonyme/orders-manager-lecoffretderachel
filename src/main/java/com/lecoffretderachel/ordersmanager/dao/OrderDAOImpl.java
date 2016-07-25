package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.lecoffretderachel.ordersmanager.model.Order;

public class OrderDAOImpl implements OrderDAO {

	private SessionFactory sessionFactory;
	
	public OrderDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void persist(Order order) {
		sessionFactory.getCurrentSession().persist(order);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Order").list();
	}

	public Order findById(Integer id) {
		return (Order) sessionFactory.getCurrentSession().get(Order.class, id);
	}

	public void update(Order order) {
		sessionFactory.getCurrentSession().update(order);
	}
	
	public void delete(Order order) {
		sessionFactory.getCurrentSession().delete(order);
	}
	
	public List findByName(String name) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Order.class)
				.add(Restrictions.like("name", name))
				.list();
	}
}
