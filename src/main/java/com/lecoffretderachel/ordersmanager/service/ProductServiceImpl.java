package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.ProductDAO;
import com.lecoffretderachel.ordersmanager.model.Product;

public class ProductServiceImpl implements ProductService, ListService {

	ProductDAO productDAO;
	
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Transactional
	public void persist(Product product) {
		productDAO.persist(product);
	}

	@Transactional
	public List list() {
		return productDAO.listAll();
	}
	
	@Transactional
	public Product findFirst() {
		return productDAO.findFirst();
	}

	@Transactional
	public void update(Product product) {
		productDAO.update(product);
	}
	
	@Transactional
	public Product findById(Integer id) {
		return productDAO.findById(id);
	}

	@Transactional
	public void delete(Product product) {
		productDAO.delete(product);
	}
}
