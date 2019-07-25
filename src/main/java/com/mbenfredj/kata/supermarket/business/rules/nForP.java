package com.mbenfredj.kata.supermarket.business.rules;

import com.mbenfredj.kata.supermarket.business.AmountConverter;
import com.mbenfredj.kata.supermarket.domain.OrderItem;

public class nForP implements ISpecialPricingRule {

	private Integer units; // N units
	private Integer price; // For P price

	public nForP(Integer units, Integer price) {
		super();
		this.units = units;
		this.price = price;
	}

	@Override
	public Integer calculatePrice(OrderItem orderItem) {
		return (this.price * (orderItem.getUnits() / this.units))
				+ (orderItem.getProduct().getPrice() * (orderItem.getUnits() % this.units));
	}

	@Override
	public String getTotalPriceDetails(OrderItem orderItem) {
		return new StringBuilder().append(orderItem.getProduct().getName())
				 .append(" :  ")
				 .append(this.units)
				 .append(" X ")
				 .append(AmountConverter.convertToDollar(orderItem.getProduct().getPrice()))
				 .append(" = ")
				 .append(AmountConverter.convertToDollar(this.units * orderItem.getProduct().getPrice()))
				 .append(" Promotion price: "+calculatePrice(orderItem))
				 .append("$")
				 .toString();
	}

}
