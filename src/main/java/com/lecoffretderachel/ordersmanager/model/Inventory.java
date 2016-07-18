package com.lecoffretderachel.ordersmanager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory implements java.io.Serializable {
	private Integer id;
	private Product product;
	private String productSize;
	private Integer quantity;
	
	public Inventory() {
		productSize = "";
		quantity = 0;
	}
	
	public Inventory(Product product, String productSize) {
		this.product = product;
		this.productSize = productSize;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "product_size")
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	
	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public static Class<?> getClass(int i) {
		switch(i) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		default:
			return null;
		}
	}
	
	public static String[] getHeaders() {
		return new String[] {"id", "product", "size", "quantity"};
	}
	
	public Object get(int i) {
		switch(i) {
		case 0:
			return id;
		case 1:
			return product.getName();
		case 2:
			return productSize;
		case 3:
			return quantity;
		default:
			return null;
		}
	}
	
	public void set(int i, Object value) {
		switch (i) {
		case 1:
			product = (Product) value;
			break;
		case 2:
			productSize = (String) value;
			break;
		case 3:
			quantity = (Integer) value;
			break;
		default:
			break;
		}
	}
}
