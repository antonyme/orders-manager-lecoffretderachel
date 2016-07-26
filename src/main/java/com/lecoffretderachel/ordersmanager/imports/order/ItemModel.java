package com.lecoffretderachel.ordersmanager.imports.order;

public class ItemModel {
	private String name;
	private Integer quantity;
	private Double price;
	private String size;
	private Boolean isSubscription;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Boolean getIsSubscription() {
		return isSubscription;
	}
	public void setIsSubscription(Boolean isSubscription) {
		this.isSubscription = isSubscription;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
