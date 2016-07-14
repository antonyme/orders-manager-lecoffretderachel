package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Customer;

public interface CustomerDAO {
	void persist(Customer customer);
	Customer findById(Integer id);
	List listAll();
	void update(Customer customer);
	void delete(Customer customer);
}
