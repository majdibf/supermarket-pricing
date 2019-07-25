package com.mbenfredj.kata.supermarket.business;

import com.mbenfredj.kata.supermarket.domain.OrderItem;

public class OrderProductService {

	public Integer calculateOrderItemPrice(OrderItem orderItem) {
		Integer totalPrice = 0;
		if (orderItem.getProduct().hasSpacialPricingRule()) {
			return orderItem.getProduct().getSpecialPricingRule().calculatePrice(orderItem);
		}
		switch (orderItem.getProduct().getSaleType()) {
		case WEIGHT:
			totalPrice = (int) ((((double) orderItem.getProduct().getPrice()) / 16) * orderItem.getUnits());
			break;
		case UNIT:
			totalPrice = orderItem.getUnits() * orderItem.getProduct().getPrice();
			break;
		}

		return totalPrice;
	}

	public String getTotalPriceDetails(OrderItem orderItem) {
		String details = null;
		if (orderItem.getProduct().hasSpacialPricingRule()) {
			return orderItem.getProduct().getSpecialPricingRule().getTotalPriceDetails(orderItem);
		}
		switch (orderItem.getProduct().getSaleType()) {
		case WEIGHT:
			details = new StringBuilder().append(orderItem.getProduct().getName()).append(" : ")
					.append(orderItem.getUnits() / (double) 16).append(" X ")
					.append(AmountConverter.convertToDollar(orderItem.getProduct().getPrice())).append("\t")
					.append(" = ").append(AmountConverter.convertToDollar(calculateOrderItemPrice(orderItem)))
					.append("$").toString();
			break;
		case UNIT:
			details = new StringBuilder().append(orderItem.getProduct().getName()).append(" : ")
					.append(orderItem.getUnits()).append(" X ")
					.append(AmountConverter.convertToDollar(orderItem.getProduct().getPrice())).append(" ")
					.append(" = ").append(AmountConverter.convertToDollar(calculateOrderItemPrice(orderItem)))
					.append("$").toString();
			break;
		}
		return details;
	}

}
