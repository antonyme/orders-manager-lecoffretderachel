package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.Date;
import java.util.List;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class OrderCSV {
	@CsvBindByName
	private Integer order_id;
	@CsvBindByName
	@CsvDate(value = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	@CsvBindByName
	private String billing_first_name;
	@CsvBindByName
	private String billing_last_name;
	@CsvBindByName
	private String billing_email;
	@CsvBindByName
	private String billing_phone;
	@CsvBindByName
	private String billing_address_1;
	@CsvBindByName
	private String billing_address_2;
	@CsvBindByName
	private String billing_postcode;
	@CsvBindByName
	private String billing_city;
	@CsvBindByName
	private String billing_state;
	@CsvBindByName
	private String billing_country;
	@CsvBindByName
	private String billing_company;
	@CsvBindByName
	private String shipping_first_name;
	@CsvBindByName
	private String shipping_last_name;
	@CsvBindByName
	private String shipping_address_1;
	@CsvBindByName
	private String shipping_address_2;
	@CsvBindByName
	private String shipping_postcode;
	@CsvBindByName
	private String shipping_city;
	@CsvBindByName
	private String shipping_state;
	@CsvBindByName
	private String shipping_country;
	@CsvBindByName
	private String shipping_company;
	@CsvBindByName
	private String customer_note;
	
	private List<String> order_items;
	
	private Boolean isSubscription;
	private List<ItemModel> itemModelList; 
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBilling_first_name() {
		return billing_first_name;
	}
	public void setBilling_first_name(String billing_first_name) {
		this.billing_first_name = billing_first_name;
	}
	public String getBilling_last_name() {
		return billing_last_name;
	}
	public void setBilling_last_name(String billing_last_name) {
		this.billing_last_name = billing_last_name;
	}
	public String getBilling_email() {
		return billing_email;
	}
	public void setBilling_email(String billing_email) {
		this.billing_email = billing_email;
	}
	public String getBilling_phone() {
		return billing_phone;
	}
	public void setBilling_phone(String billing_phone) {
		this.billing_phone = billing_phone;
	}
	public String getBilling_address_1() {
		return billing_address_1;
	}
	public void setBilling_address_1(String billing_address_1) {
		this.billing_address_1 = billing_address_1;
	}
	public String getBilling_address_2() {
		return billing_address_2;
	}
	public void setBilling_address_2(String billing_address_2) {
		this.billing_address_2 = billing_address_2;
	}
	public String getBilling_postcode() {
		return billing_postcode;
	}
	public void setBilling_postcode(String billing_postcode) {
		this.billing_postcode = billing_postcode;
	}
	public String getBilling_city() {
		return billing_city;
	}
	public void setBilling_city(String billing_city) {
		this.billing_city = billing_city;
	}
	public String getBilling_state() {
		return billing_state;
	}
	public void setBilling_state(String billing_state) {
		this.billing_state = billing_state;
	}
	public String getBilling_country() {
		return billing_country;
	}
	public void setBilling_country(String billing_country) {
		this.billing_country = billing_country;
	}
	public String getBilling_company() {
		return billing_company;
	}
	public void setBilling_company(String billing_company) {
		this.billing_company = billing_company;
	}
	public String getShipping_first_name() {
		return shipping_first_name;
	}
	public void setShipping_first_name(String shipping_first_name) {
		this.shipping_first_name = shipping_first_name;
	}
	public String getShipping_last_name() {
		return shipping_last_name;
	}
	public void setShipping_last_name(String shipping_last_name) {
		this.shipping_last_name = shipping_last_name;
	}
	public String getShipping_address_1() {
		return shipping_address_1;
	}
	public void setShipping_address_1(String shipping_address_1) {
		this.shipping_address_1 = shipping_address_1;
	}
	public String getShipping_address_2() {
		return shipping_address_2;
	}
	public void setShipping_address_2(String shipping_address_2) {
		this.shipping_address_2 = shipping_address_2;
	}
	public String getShipping_postcode() {
		return shipping_postcode;
	}
	public void setShipping_postcode(String shipping_postcode) {
		this.shipping_postcode = shipping_postcode;
	}
	public String getShipping_city() {
		return shipping_city;
	}
	public void setShipping_city(String shipping_city) {
		this.shipping_city = shipping_city;
	}
	public String getShipping_state() {
		return shipping_state;
	}
	public void setShipping_state(String shipping_state) {
		this.shipping_state = shipping_state;
	}
	public String getShipping_country() {
		return shipping_country;
	}
	public void setShipping_country(String shipping_country) {
		this.shipping_country = shipping_country;
	}
	public String getShipping_company() {
		return shipping_company;
	}
	public void setShipping_company(String shipping_company) {
		this.shipping_company = shipping_company;
	}
	public String getCustomer_note() {
		return customer_note;
	}
	public void setCustomer_note(String customer_note) {
		this.customer_note = customer_note;
	}
	public List<String> getOrder_items() {
		return order_items;
	}
	public void setOrder_items(List<String> order_items) {
		this.order_items = order_items;
	}
	public Boolean getIsSubscription() {
		return isSubscription;
	}
	public void setIsSubscription(Boolean isSubscription) {
		this.isSubscription = isSubscription;
	}
	public List<ItemModel> getItemModelList() {
		return itemModelList;
	}
	public void setItemModelList(List<ItemModel> itemModelList) {
		this.itemModelList = itemModelList;
	}
}
