package com.mbenfredj.kata.supermarket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.mbenfredj.kata.supermarket.OrderProduct.OrderProductBuilder;

public class ProductPricingTest {

	
	// Prices
	private Integer priceOfOneCanOfBeans = 65;
	private Integer priceOfTwoCanOfBeans = 130;

	private Integer priceOfOneCoke = 45;
	private Integer priceOfTwoCoke = 90;
	private Integer priceOfThreeCoke = 100;

	// Products
	private Product canOfBeans = Product.ProductBuilder.create().withName("can of beans")
			.withPrice(priceOfOneCanOfBeans).build();
	private Product coke = Product.ProductBuilder.create().withName("coke").withPrice(priceOfOneCoke).build();

	//SpecialPricingRules
	private ISpecialPricingRule nForP = new nForP(3,100);

	// OrderProducts
	//can of beans orders
	private OrderProduct orderOneCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(1).build();
	private OrderProduct orderTwoCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(2).build();
	//coke orders
	private OrderProduct orderOneCoke = OrderProductBuilder.create().withProduct(coke).withSpecialPricingRule(nForP).withUnits(1).build();
	private OrderProduct orderThreeCoke = OrderProductBuilder.create().withProduct(coke).withSpecialPricingRule(nForP).withUnits(3).build();

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

	@Test
	public void must_return_45_when_pricing_one_coke_available_in_special_sale() {
		assertThat(orderOneCoke.getProduct().getName()).isEqualTo("coke");
		assertThat(orderOneCoke.getUnits()).isEqualTo(1);
		assertThat(orderOneCoke.hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderOneCoke.getTotalPrice()).isEqualTo(priceOfOneCoke);
	}
	
	@Test
	public void must_return_100_when_pricing_three_coke_available_in_special_sale() {
		assertThat(orderThreeCoke.getProduct().getName()).isEqualTo("coke");
		assertThat(orderThreeCoke.getUnits()).isEqualTo(3);
		assertThat(orderThreeCoke.hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderThreeCoke.getTotalPrice()).isEqualTo(priceOfThreeCoke);
	}
	

}
