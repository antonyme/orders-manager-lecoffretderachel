package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.List;

import com.lecoffretderachel.ordersmanager.service.OrderService;

public class OrderImportModel {
	OrderService orderService;
	List<OrderCSV> orderCSVList;
	List<OrderModelBuilder> subOrderBuilderList = new ArrayList<>();
	List<OrderModelBuilder> directOrderBuilderList = new ArrayList<>();
	
	public OrderImportModel(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public List<OrderCSV> getOrderCSVList() {
		return orderCSVList;
	}
	public void setOrderCSVList(List<OrderCSV> orderCSVList) {
		this.orderCSVList = orderCSVList;
	}
	
	public List<OrderModelBuilder> getOrderBuilderList(Boolean subscription) {
		if(subscription) {
			return subOrderBuilderList;
		}
		else {
			return directOrderBuilderList;
		}
	}
}
