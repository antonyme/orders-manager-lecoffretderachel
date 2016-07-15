package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.CustomerDAO;
import com.lecoffretderachel.ordersmanager.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDAO customerDAO;
	
	@Transactional
	public void persist(Customer customer) {
		customerDAO.persist(customer);
	}

	@Transactional
	public List list() {
		return customerDAO.listAll();
	}

	@Transactional
	public void update(Customer customer) {
		customerDAO.update(customer);
	}
	
	@Transactional
	public Customer findById(Integer id) {
		return customerDAO.findById(id);
	}

	@Transactional
	public void delete(Customer customer) {
		customerDAO.delete(customer);
	}
}
