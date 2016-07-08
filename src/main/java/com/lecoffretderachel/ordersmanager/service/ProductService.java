package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Product;

public interface ProductService {
	void persistProduct(Product product);
	List listProducts();
	Product findProductById(Integer i);
	void updateProduct(Product product);
	void deleteProduct(Product product);
}