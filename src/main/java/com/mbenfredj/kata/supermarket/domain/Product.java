package com.mbenfredj.kata.supermarket;

public class Product {

	private String name;
	private int price; // unit price
	private ProductSaleType saleType;

	private Product(ProductBuilder builder) {
		this.name = builder.name;
		this.price = builder.price;
		this.saleType = builder.saleType;
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

	// Builder class
	public static class ProductBuilder {
		private String name;
		private Integer price;
		private ProductSaleType saleType;

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

	}

}
