package com.mbenfredj.kata.supermarket;

public class Product {

	private String name;
	private int price; // unit price

	private Product(ProductBuilder builder) {
		this.name = builder.name;
		this.price = builder.price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	// Builder class
	public static class ProductBuilder {
		private String name;
		private Integer price;

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

	}

}
