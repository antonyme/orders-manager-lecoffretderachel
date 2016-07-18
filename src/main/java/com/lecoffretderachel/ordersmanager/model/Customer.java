package com.lecoffretderachel.ordersmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
		this.email = "newEmail";
		this.firstName = "newFirstName";
		this.lastName = "newLastName";
		this.note = "newNote";
	}
	
	public Customer(String email, String firstName, String lastName, String note) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.note = note;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
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

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
		name = "customer_tag_exclude",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public List<Tag> getTagsExcluded() {
		return tagsExcluded;
	}

	public void setTagsExcluded(List<Tag> tagsExcluded) {
		this.tagsExcluded = tagsExcluded;
	}

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
		name = "customer_product_include",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	public List<Product> getProductsIncluded() {
		return productsIncluded;
	}

	public void setProductsIncluded(List<Product> productsIncluded) {
		this.productsIncluded = productsIncluded;
	}

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
		name = "customer_product_exclude",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	public List<Product> getProductsExcluded() {
		return productsExcluded;
	}

	public void setProductsExcluded(List<Product> productsExcluded) {
		this.productsExcluded = productsExcluded;
	}

	@OneToMany(mappedBy = "orderOwner")
	@LazyCollection(LazyCollectionOption.FALSE)
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
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return String.class;
		default:
			return null;
		}
	}
	
	public static String[] getHeaders() {
		return new String[] {"id", "email", "firstName", "lastName", "note", "tagsIncluded", "tagsExcluded", "productsIncluded", "productsExcluded"};
	}
	
	public Object get(int i) {
		switch(i) {
		case 0:
			return id;
		case 1:
			return email;
		case 2:
			return firstName;
		case 3:
			return lastName;
		case 4:
			return note;
		case 5:
			Collections.sort(tagsIncluded);
			return tagsIncluded;
		case 6:
			Collections.sort(tagsExcluded);
			return tagsExcluded;
		case 7:
			Collections.sort(productsIncluded);
			return productsIncluded;
		case 8:
			Collections.sort(productsExcluded);
			return productsExcluded;
		default:
			return null;
		}
	}
	
	public void set(int i, Object value) {
		switch (i) {
		case 0:
			id = (Integer) value;
			break;
		case 1:
			email = (String) value;
			break;
		case 2:
			firstName = (String) value;
			break;
		case 3:
			lastName = (String) value;
			break;
		case 4:
			note = (String) value;
			break;
		case 5:
			tagsIncluded = (List<Tag>) value;
			break;
		case 6:
			tagsExcluded = (List<Tag>) value;
			break;
		case 7:
			productsIncluded = (List<Product>) value;
			break;
		case 8:
			productsExcluded = (List<Product>) value;
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		return email;
	}
}