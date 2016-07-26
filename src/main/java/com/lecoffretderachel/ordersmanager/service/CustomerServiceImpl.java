package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.CustomerDAO;
import com.lecoffretderachel.ordersmanager.model.Customer;

public class CustomerServiceImpl implements CustomerService{

	CustomerDAO customerDAO;
	
	public CustomerServiceImpl(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
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

	@Transactional
	public Customer findByEmail(String email) throws IllegalArgumentException {
		List resultsList = customerDAO.findByEmail(email);
		if(resultsList.size() > 1) {
			throw new IllegalArgumentException("More than one client have the same email : " + email);
		}
		else if(resultsList.size() == 1){
			return (Customer) resultsList.get(0);
		}
		else {
			return null;
		}
	}

	@Transactional
	public List findByName(String firstName, String lastName) {
		return customerDAO.findByName(firstName, lastName);
	}
}
