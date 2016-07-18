package com.lecoffretderachel.ordersmanager.model;

import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements java.io.Serializable, Comparable {
	private Integer id;
	private String name;
	private List<Tag> tags = new ArrayList<Tag>();
	
	public Product() {
		this.name = "newProduct";
	}
	
	public Product(String name) {
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "product_tag_include",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public static Class<?> getClass(int i) {
		switch(i) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
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
		case 1:
			return name;
		case 2:
			Collections.sort(tags);
			return tags;
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
		case 2:
			tags = (List<Tag>) value;
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
		return name.compareTo(((Product) o).getName());
	}
}
