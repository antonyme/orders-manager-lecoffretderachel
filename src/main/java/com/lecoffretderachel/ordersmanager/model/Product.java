package com.lecoffretderachel.ordersmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "fabric")
	private String fabric;
	
	@Column(name = "style")
	private String style;
	
	public Product() {}
	
	public Product(String name, String color, String fabric, String style) {
		this.name = name;
		this.color = color;
		this.fabric = fabric;
		this.style = style;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFabric() {
		return fabric;
	}
	public void setFabric(String fabric) {
		this.fabric = fabric;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	public static Class<?> getClass(int i) {
		return i == 0 ? Integer.class : String.class;
	}
	
	public Object get(int i) {
		switch(i) {
		case 0:
			return id;
		case 1:
			return name;
		case 2:
			return color;
		case 3:
			return fabric;
		case 4:
			return style;
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
			color = (String) value;
			break;
		case 3:
			fabric = (String) value;
			break;
		case 4:
			style = (String) value;
			break;
		default:
			break;
		}
	}
}
