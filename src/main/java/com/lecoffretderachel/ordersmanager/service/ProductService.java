package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Product;

public interface ProductService {
	void persist(Product product);
	List list();
	Product findFirst();
	Product findById(Integer i);
	void update(Product product);
	void delete(Product product);
	
	Product matchByName(String name) throws IllegalArgumentException;
}