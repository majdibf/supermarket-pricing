package com.mbenfredj.kata.supermarket;

public class nForP implements ISpecialPricingRule {

	private Integer units; // N units
	private Integer price; // For P price

	public nForP(Integer units, Integer price) {
		super();
		this.units = units;
		this.price = price;
	}

	@Override
	public Integer getTotalPrice(OrderProduct orderProduct) {
		return (this.price * (orderProduct.getUnits() / this.units))
				+ (orderProduct.getProduct().getPrice() * (orderProduct.getUnits() % this.units));
	}

}
