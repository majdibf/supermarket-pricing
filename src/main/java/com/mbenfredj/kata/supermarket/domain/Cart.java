package com.mbenfredj.kata.supermarket.domain;

import java.util.List;

import com.mbenfredj.kata.supermarket.business.OrderProductService;

public class Cart {
	private List<OrderItem> orders;
	OrderProductService orderProductService = new OrderProductService();

	public Cart(List<OrderItem> orders) {
		this.orders = orders;
	}

	public String print() {
		StringBuilder details = new StringBuilder();
		orders.forEach(order -> details.append(orderProductService.getTotalPriceDetails(order)).append("\n"));
		details.append("TOTAL : ").append(total());

		return details.toString();
	}

	private int total() {
		return orders.stream().map(order->orderProductService.calculateOrderItemPrice(order)).reduce(0, Integer::sum);
	}
}
