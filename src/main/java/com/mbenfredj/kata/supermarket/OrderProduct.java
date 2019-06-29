package com.mbenfredj.kata.supermarket;

public class OrderProduct {

	private Product product;
	private Integer units; // units or ounces for weight selling product
	private ISpecialPricingRule specialPricingRule;

	public OrderProduct(OrderProductBuilder builder) {
		this.product = builder.product;
		this.units = builder.units;
		this.specialPricingRule = builder.specialPricingRule;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getUnits() {
		return units;
	}

	public Boolean hasSpacialPricingRule() {
		if (this.specialPricingRule != null) {
			return true;
		}
		return false;
	}

	// Builder class
	public static class OrderProductBuilder {
		private Product product;
		private Integer units;
		private ISpecialPricingRule specialPricingRule;

		public static OrderProductBuilder create() {
			return new OrderProductBuilder();
		}

		public OrderProduct build() {
			return new OrderProduct(this);
		}

		public OrderProductBuilder withProduct(Product product) {
			this.product = product;
			return this;
		}

		public OrderProductBuilder withUnits(Integer units) {
			this.units = units;
			return this;
		}

		public OrderProductBuilder withSpecialPricingRule(ISpecialPricingRule specialPricingRule) {
			this.specialPricingRule = specialPricingRule;
			return this;
		}

	}

	public Integer getTotalPrice() {
		Integer totalPrice = 0;
		if (this.hasSpacialPricingRule()) {
			return this.specialPricingRule.getTotalPrice(this);
		}
		switch (this.getProduct().getSaleType()) {
		case WEIGHT:
			totalPrice = (int) ((((double) this.product.getPrice()) / 16) * this.units);
			break;
		case UNIT:
			totalPrice = this.units * this.product.getPrice();
			break;
		}

		return totalPrice;
	}

}
