package com.mbenfredj.kata.supermarket;

public class OrderProduct {

	private Product product;
	private Integer units; // units or ounces for weight selling product

	public OrderProduct(OrderProductBuilder builder) {
		this.product = builder.product;
		this.units = builder.units;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getUnits() {
		return units;
	}

	// Builder class
	public static class OrderProductBuilder {
		private Product product;
		private Integer units;

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

	}

	public Integer getTotalPrice() {
		return this.units * this.product.getPrice();
	}

}
