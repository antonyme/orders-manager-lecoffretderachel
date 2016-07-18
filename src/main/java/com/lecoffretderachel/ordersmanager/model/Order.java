package com.lecoffretderachel.ordersmanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "order_table")
public class Order {
	private Integer id;
	private Boolean subsciption;
	private Date orderDate;
	private String customerNote;
	private String bilingEmail;
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingCompany;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingState;
	private String shippingPostCode;
	private String shippingCountry;
	private Customer orderOwner;
	private List<Product> productsIncluded = new ArrayList<Product>();
	
	public Order() {
	}
	
	public Order(String email, String firstName, String lastName, String note) {
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "subscription")
	public Boolean getSubsciption() {
		return subsciption;
	}

	public void setSubsciption(Boolean subsciption) {
		this.subsciption = subsciption;
	}

	@Column(name = "order_date")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "customer_note")
	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	@Column(name = "billing_email")
	public String getBilingEmail() {
		return bilingEmail;
	}

	public void setBilingEmail(String bilingEmail) {
		this.bilingEmail = bilingEmail;
	}

	@Column(name = "shipping_first_name")
	public String getShippingFirstName() {
		return shippingFirstName;
	}

	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}

	@Column(name = "shipping_last_name")
	public String getShippingLastName() {
		return shippingLastName;
	}

	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
	}

	@Column(name = "shipping_company")
	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	@Column(name = "shipping_address_1")
	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	@Column(name = "shipping_address_2")
	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	@Column(name = "shipping_city")
	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	@Column(name = "shipping_state")
	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	@Column(name = "shipping_postcode")
	public String getShippingPostCode() {
		return shippingPostCode;
	}

	public void setShippingPostCode(String shippingPostCode) {
		this.shippingPostCode = shippingPostCode;
	}

	@Column(name = "shipping_country")
	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "customer_id")
	public Customer getOrderOwner() {
		return orderOwner;
	}

	public void setOrderOwner(Customer orderOwner) {
		this.orderOwner = orderOwner;
	}

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
		name = "order_product",
		joinColumns = @JoinColumn(name = "order_id"),
		inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	public List<Product> getProductsIncluded() {
		return productsIncluded;
	}

	public void setProductsIncluded(List<Product> productsIncluded) {
		this.productsIncluded = productsIncluded;
	}
	
	public static Class<?> getClass(int i) {
		switch(i) {
		case 0:
			return Integer.class;
		default:
			return null;
		}
	}
	
	public static String[] getHeaders() {
		return new String[] {"id"};
	}
	
	public Object get(int i) {
		switch(i) {
		case 0:
			return id;
		default:
			return null;
		}
	}
	
	public void set(int i, Object value) {
		switch (i) {
		case 0:
			id = (Integer) value;
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		return null;
	}
}
