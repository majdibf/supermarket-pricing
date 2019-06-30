package com.mbenfredj.kata.supermarket;

import java.util.List;

public class Chart {
	private List<OrderProduct> orders;

	public Chart(List<OrderProduct> orders) {
		this.orders = orders;
	}

	public String print() {
		StringBuilder details = new StringBuilder();
		orders.forEach(order -> details.append(order.getTotalPriceDetails()).append("\n"));
		details.append("TOTAL : ").append(total());

		return details.toString();
	}

	private int total() {
		return orders.stream().map(OrderProduct::getTotalPrice).reduce(0, Integer::sum);
	}
}
