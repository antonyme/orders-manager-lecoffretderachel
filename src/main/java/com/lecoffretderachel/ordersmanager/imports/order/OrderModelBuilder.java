package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Order;

public class OrderModelBuilder {
	private Order newOrder = new Order();
	private List<ItemModel> productInclude = new ArrayList<>();
	
	public OrderModelBuilder(OrderCSV orderCSV, boolean subscription) {
		newOrder.setSubscription(subscription);
		for(ItemModel item : orderCSV.getItemModelList()) {
			if(item.getIsSubscription() == subscription) {
				productInclude.add(item);
			}
		}
		buildModelFromCSV(orderCSV);
	}
	
	private void buildModelFromCSV(OrderCSV orderCSV) {
		newOrder.setWoocommerceId(orderCSV.getOrder_id());
		newOrder.setOrderDate(orderCSV.getDate());
		newOrder.setCustomerNote(orderCSV.getCustomer_note());
		newOrder.setBillingEmail(orderCSV.getBilling_email());
		newOrder.setShippingFirstName(orderCSV.getShipping_first_name());
		newOrder.setShippingLastName(orderCSV.getShipping_last_name());
		newOrder.setShippingCompany(orderCSV.getShipping_company());
		newOrder.setShippingAddress1(orderCSV.getShipping_address_1());
		newOrder.setShippingAddress2(orderCSV.getShipping_address_2());
		newOrder.setShippingCity(orderCSV.getShipping_city());
		newOrder.setShippingState(orderCSV.getShipping_state());
		newOrder.setShippingPostCode(orderCSV.getShipping_postcode());
		newOrder.setShippingCountry(orderCSV.getShipping_country());
	}

	public Order getNewOrder() {
		return newOrder;
	}
	
	public List<ItemModel> getProductInclude() {
		return productInclude;
	}

	public void setProductInclude(List<ItemModel> productInclude) {
		this.productInclude = productInclude;
	}
}
