package com.lecoffretderachel.ordersmanager.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements java.io.Serializable {
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String note;
	private List<Tag> tagsIncluded = new ArrayList<Tag>();
	private List<Tag> tagsExcluded = new ArrayList<Tag>();
	private List<Product> productsIncluded = new ArrayList<Product>();
	private List<Product> productsExcluded = new ArrayList<Product>();
	private List<Order> orders = new ArrayList<Order>();
	
	public Customer() {
	}
	
	public Customer(String email, String firstName, String lastName, String note) {
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return null;
	}

	@Column(name = "name")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "name")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "customer_tag_include",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public List<Tag> getTagsIncluded() {
		return tagsIncluded;
	}

	public void setTagsIncluded(List<Tag> tagsIncluded) {
		this.tagsIncluded = tagsIncluded;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "customer_tag_excluded",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public List<Tag> getTagsExcluded() {
		return tagsExcluded;
	}

	public void setTagsExcluded(List<Tag> tagsExcluded) {
		this.tagsExcluded = tagsExcluded;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "customer_product_include",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public List<Product> getProductsIncluded() {
		return productsIncluded;
	}

	public void setProductsIncluded(List<Product> productsIncluded) {
		this.productsIncluded = productsIncluded;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "customer_product_exclude",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public List<Product> getProductsExcluded() {
		return productsExcluded;
	}

	public void setProductsExcluded(List<Product> productsExcluded) {
		this.productsExcluded = productsExcluded;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderOwner")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
		return new String[] {"id", "name", "tags"};
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
}