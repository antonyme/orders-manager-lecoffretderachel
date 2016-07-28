package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.OrderDAO;
import com.lecoffretderachel.ordersmanager.model.Customer;
import com.lecoffretderachel.ordersmanager.model.Order;

public class OrderServiceImpl implements OrderService{

	OrderDAO orderDAO;
	
	public OrderServiceImpl(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@Transactional
	public void persist(Order order) {
		orderDAO.persist(order);
	}

	@Transactional
	public List list() {
		return orderDAO.listAll();
	}

	@Transactional
	public void update(Order order) {
		orderDAO.update(order);
	}
	
	@Transactional
	public Order findById(Integer id) {
		return orderDAO.findById(id);
	}

	@Transactional
	public void delete(Order order) {
		orderDAO.delete(order);
	}
	
	@Transactional
	public List findByCustomer(Customer customer) {
		return orderDAO.findByCustomer(customer);
	}
}
