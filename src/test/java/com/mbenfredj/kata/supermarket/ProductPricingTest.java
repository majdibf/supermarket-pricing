package com.mbenfredj.kata.supermarket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.mbenfredj.kata.supermarket.OrderProduct.OrderProductBuilder;

public class ProductPricingTest {

	// Prices
	private Integer priceOfOneCanOfBeans = 65;
	private Integer priceOfTwoCanOfBeans = 130;

	// Products
	private Product canOfBeans = Product.ProductBuilder.create().withName("can of beans")
			.withPrice(priceOfOneCanOfBeans).build();

	// OrderProducts
	private OrderProduct orderOneCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(1).build();
	private OrderProduct orderTwoCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(2).build();

	@Test
	public void must_return_65_for_one_can_of_beans() {
		assertThat(orderOneCanOfBeans.getUnits()).isEqualTo(1);
		assertThat(orderOneCanOfBeans.getTotalPrice()).isEqualTo(priceOfOneCanOfBeans);
	}

	@Test
	public void must_return_130_for_two_can_of_beans() {
		assertThat(orderTwoCanOfBeans.getUnits()).isEqualTo(2);
		assertThat(orderTwoCanOfBeans.getTotalPrice()).isEqualTo(priceOfTwoCanOfBeans);
	}

}
