package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Customer;

public interface CustomerService {
	void persist(Customer Customer);
	List list();
	Customer findById(Integer i);
	void update(Customer Customer);
	void delete(Customer Customer);
}