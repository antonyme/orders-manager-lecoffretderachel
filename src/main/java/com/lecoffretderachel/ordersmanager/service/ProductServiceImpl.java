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
		productDAO.persist(product);
	}

	@Transactional
	public List listProducts() {
		return productDAO.listAll();
	}

	@Transactional
	public void updateProduct(Product product) {
		productDAO.update(product);
	}
	
	@Transactional
	public Product findProductById(Integer id) {
		return productDAO.findById(id);
	}

	@Transactional
	public void deleteProduct(Product product) {
		productDAO.delete(product);
	}
}
