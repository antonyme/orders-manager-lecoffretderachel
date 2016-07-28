package com.lecoffretderachel.ordersmanager.logFile;

import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.model.OrderProduct;

public class Util {

	public static String[] orderProductToString(Order order, OrderProduct orderProduct) {
		return new String[] {
				order.getWoocommerceId().toString(),
				order.getOrderDate().toString(),
				order.getBillingEmail(),
				order.getShippingFirstName(),
				order.getShippingLastName(),
				order.getShippingCompany(),
				order.getShippingAddress1(),
				order.getShippingAddress2(),
				order.getShippingCity(),
				order.getShippingPostCode(),
				order.getShippingState(),
				order.getShippingCountry(),
				orderProduct.getProduct().getName(),
				orderProduct.getProductSize(),
				orderProduct.getQuantity().toString()
			};
	}

}
