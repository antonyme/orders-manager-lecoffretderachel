package com.lecoffretderachel.ordersmanager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag implements java.io.Serializable, Comparable {
	private Integer id;
	private String name;
	private Set<Product> include = new HashSet<Product>();

	public Tag() {}
	
	public Tag(String name) {
		this.name = name;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "tags")
	public Set<Product> getPreferences() {
		return include;
	}
	public void setPreferences(Set<Product> preferences) {
		this.include = preferences;
	}
	
	public static Class<?> getClass(int i) {
		switch(i) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		default:
			return null;
		}
	}
	
	public Object get(int i) {
		switch(i) {
		case 0:
			return id;
		case 1:
			return name;
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
			name = (String) value;
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Object o) {
		return name.compareTo(((Tag) o).getName());
	}
}
