package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.OrderDAO;
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
	public Order matchByName(String name) throws IllegalArgumentException {
		List resultsList = orderDAO.findByName(name);
		switch(resultsList.size()) {
		case 0:
			throw new IllegalArgumentException("No existing product found for : " + name);
		case 1:
			return (Order) resultsList.get(0);
		default:
			throw new IllegalArgumentException("Multiple products (" + resultsList.size() + ") found for : " + name);
		}
	}
}
