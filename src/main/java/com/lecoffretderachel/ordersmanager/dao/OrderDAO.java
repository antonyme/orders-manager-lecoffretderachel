package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Order;

public interface OrderDAO {
	void persist(Order order);
	Order findById(Integer id);
	List listAll();
	void update(Order order);
	void delete(Order order);
	
	List findByName(String name);
}
