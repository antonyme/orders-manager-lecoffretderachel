package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Customer;

public interface CustomerService {
	void persistCustomer(Customer Customer);
	List listCustomers();
	Customer findCustomerById(Integer i);
	void updateCustomer(Customer Customer);
	void deleteCustomer(Customer Customer);
}