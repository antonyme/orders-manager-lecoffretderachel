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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "order_table")
public class Order {
	private Integer id;
	private Boolean subscription;
	private Date orderDate;
	private String customerNote;
	private String billingEmail;
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
	private List<OrderProduct> orderProductInclude = new ArrayList<OrderProduct>();
	
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
	public Boolean getSubscription() {
		return subscription;
	}

	public void setSubscription(Boolean subscription) {
		this.subscription = subscription;
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
	public String getBillingEmail() {
		return billingEmail;
	}

	public void setBillingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
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

	@OneToMany(mappedBy = "order")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<OrderProduct> getOrderProductInclude() {
		return orderProductInclude;
	}

	public void setOrderProductInclude(List<OrderProduct> orderProductInclude) {
		this.orderProductInclude = orderProductInclude;
	}
	
	@Transient
	public List<Product> getProductInclude() {
		List<Product> res = new ArrayList<>();
		orderProductInclude.forEach((link) -> res.add(link.getProduct()));
		return res;
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
			return String.class;
		case 4:
			return Boolean.class;
		case 5:
			return String.class;
		default:
			return null;
		}
	}
	
	public static String[] getHeaders() {
		return new String[] {"id", "orderDate", "shippingFirstName", "shippingLastName", "subscription", "productInclude"};
	}
	
	public Object get(int i) {
		switch(i) {
		case 0:
			return id;
		case 1:
			return orderDate.toString();
		case 2:
			return shippingFirstName;
		case 3:
			return shippingLastName;
		case 4:
			return subscription;
		case 5:
			return getProductInclude();
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
}
