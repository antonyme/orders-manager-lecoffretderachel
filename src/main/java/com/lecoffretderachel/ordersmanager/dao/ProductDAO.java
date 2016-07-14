package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Product;

public interface ProductDAO {
	void persist(Product product);
	Product findById(Integer id);
	List listAll();
	Product findFirst();
	void update(Product product);
	void delete(Product product);
}
