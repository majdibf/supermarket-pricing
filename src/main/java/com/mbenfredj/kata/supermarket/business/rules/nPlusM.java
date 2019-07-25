package com.mbenfredj.kata.supermarket.business.rules;

import com.mbenfredj.kata.supermarket.business.AmountConverter;
import com.mbenfredj.kata.supermarket.domain.OrderItem;

public class nPlusM implements ISpecialPricingRule {
	private Integer freeUnit; // N free unit from
	private Integer purchasedUnit; // M purchased unit

	public nPlusM(Integer freeUnit, Integer purchasedUnit) {
		super();
		this.freeUnit = freeUnit;
		this.purchasedUnit = purchasedUnit;
	}

	@Override
	public Integer calculatePrice(OrderItem orderItem) {
		Integer nbFreeUnit = orderItem.getUnits() / purchasedUnit * freeUnit;
		return orderItem.getProduct().getPrice() * (orderItem.getUnits() - nbFreeUnit);
	}

	@Override
	public String getTotalPriceDetails(OrderItem orderItem) {
		return new StringBuilder().append(orderItem.getProduct().getName())
				 .append(" : ")
				 .append(orderItem.getUnits())
				 .append(" X ")
				 .append(AmountConverter.convertToDollar(orderItem.getProduct().getPrice()))
				 .append("\t")
				 .append(" = ")
				 .append(AmountConverter.convertToDollar(calculatePrice(orderItem)))
				 .append("$ ")
				 .append(" (Promotion) Free units: "+ orderItem.getUnits() / purchasedUnit * freeUnit)
				 .toString();
	}

}
