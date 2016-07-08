package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.ProductDAO;
import com.lecoffretderachel.ordersmanager.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;
	
	@Transactional
	public void persistProduct(Product product) {
		productDAO.persistProduct(product);
	}

	@Transactional
	public List listProducts() {
		return productDAO.listProducts();
	}

	@Transactional
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}
	
	@Transactional
	public Product findProductById(Integer id) {
		return productDAO.findProductById(id);
	}

	@Transactional
	public void deleteProduct(Product product) {
		productDAO.deleteProduct(product);
	}
}
