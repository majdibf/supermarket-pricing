package com.mbenfredj.kata.supermarket.business.rules;

import com.mbenfredj.kata.supermarket.domain.OrderItem;

public interface ISpecialPricingRule {

	Integer calculatePrice(OrderItem orderProduct);

	String getTotalPriceDetails(OrderItem orderProduct);	

}
