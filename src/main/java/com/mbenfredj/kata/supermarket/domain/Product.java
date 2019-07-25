package com.mbenfredj.kata.supermarket.domain;

import com.mbenfredj.kata.supermarket.business.rules.ISpecialPricingRule;

public class Product {

	private String name;
	private int price; // unit price
	private ProductSaleType saleType;
	private ISpecialPricingRule specialPricingRule;


	private Product(ProductBuilder builder) {
		this.name = builder.name;
		this.price = builder.price;
		this.saleType = builder.saleType;
		this.specialPricingRule = builder.specialPricingRule;

	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public ProductSaleType getSaleType() {
		return saleType;
	}
	
	public ISpecialPricingRule getSpecialPricingRule() {
		return specialPricingRule;
	}
	
	public Boolean hasSpacialPricingRule() {
		if (this.specialPricingRule != null) {
			return true;
		}
		return false;
	}

	// Builder class
	public static class ProductBuilder {
		private String name;
		private Integer price;
		private ProductSaleType saleType;
		private ISpecialPricingRule specialPricingRule;


		public static ProductBuilder create() {
			return new ProductBuilder();
		}

		public Product build() {
			return new Product(this);
		}

		public ProductBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public ProductBuilder withPrice(Integer price) {
			this.price = price;
			return this;
		}

		public ProductBuilder withSaleType(ProductSaleType saleType) {
			this.saleType = saleType;
			return this;
		}
		
		public ProductBuilder withSpecialPricingRule(ISpecialPricingRule specialPricingRule) {
			this.specialPricingRule = specialPricingRule;
			return this;
		}

	}


}
