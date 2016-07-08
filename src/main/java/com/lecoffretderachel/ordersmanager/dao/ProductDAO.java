package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Product;

public interface ProductDAO {
	void persistProduct(Product product);
	Product findProductById(Integer id);
	List listProducts();
	void updateProduct(Product product);
	void deleteProduct(Product product);
}
