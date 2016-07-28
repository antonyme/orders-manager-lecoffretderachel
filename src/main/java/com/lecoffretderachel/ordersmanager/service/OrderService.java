package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Customer;
import com.lecoffretderachel.ordersmanager.model.Order;

public interface OrderService {
	void persist(Order order);
	List list();
	Order findById(Integer i);
	void update(Order order);
	void delete(Order order);

	List findByCustomer(Customer customer);
}