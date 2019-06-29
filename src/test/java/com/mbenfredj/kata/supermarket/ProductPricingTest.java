package com.mbenfredj.kata.supermarket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mbenfredj.kata.supermarket.OrderProduct.OrderProductBuilder;

import pricing.rules.ISpecialPricingRule;
import pricing.rules.nForP;
import pricing.rules.nPlusM;

public class ProductPricingTest {

	// Prices
	private Integer priceOfOneCanOfBeans = 65;
	private Integer priceOfTwoCanOfBeans = 130;

	private Integer priceOfOneCoke = 45;
	private Integer priceOfTwoCoke = 90;
	private Integer priceOfThreeCoke = 100;

	private Integer priceOfOneFanta = 30;
	private Integer priceOfThreeFanta = 90;
	private Integer priceOfFourFanta = 90;

	private Integer priceOfOnePoundApple = 199;
	private Integer priceOfFourOuncesApple = 49;

	// Products
	private Product canOfBeans = Product.ProductBuilder.create().withName("can of beans")
			.withPrice(priceOfOneCanOfBeans).withSaleType(ProductSaleType.UNIT).build();
	private Product coke = Product.ProductBuilder.create().withName("coke").withPrice(priceOfOneCoke)
			.withSaleType(ProductSaleType.UNIT).build();
	private Product fanta = Product.ProductBuilder.create().withName("fanta").withPrice(priceOfOneFanta)
			.withSaleType(ProductSaleType.UNIT).build();

	private Product apple = Product.ProductBuilder.create().withName("apple").withPrice(priceOfOnePoundApple)
			.withSaleType(ProductSaleType.WEIGHT).build();

	// SpecialPricingRules
	private ISpecialPricingRule nForP = new nForP(3, 100);
	private ISpecialPricingRule nPlusM = new nPlusM(1, 3);

	// OrderProducts
	// can of beans orders
	private OrderProduct orderOneCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(1).build();
	private OrderProduct orderTwoCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(2).build();
	// coke orders
	private OrderProduct orderOneCoke = OrderProductBuilder.create().withProduct(coke).withSpecialPricingRule(nForP)
			.withUnits(1).build();
	private OrderProduct orderThreeCoke = OrderProductBuilder.create().withProduct(coke).withSpecialPricingRule(nForP)
			.withUnits(3).build();
	// fanta orders
	private OrderProduct orderOneFanta = OrderProductBuilder.create().withProduct(fanta).withSpecialPricingRule(nPlusM)
			.withUnits(1).build();
	private OrderProduct orderFourFanta = OrderProductBuilder.create().withProduct(fanta).withSpecialPricingRule(nPlusM)
			.withUnits(4).build();

	// apple
	private OrderProduct orderOnePoundApple = OrderProductBuilder.create().withProduct(apple).withUnits(16).build();
	private OrderProduct orderFourOuncesApple = OrderProductBuilder.create().withProduct(apple).withUnits(4).build();

	@Test
	public void must_return_65_for_one_can_of_beans() {
		assertThat(orderOneCanOfBeans.getUnits()).isEqualTo(1);
		assertThat(orderOneCanOfBeans.getTotalPrice()).isEqualTo(priceOfOneCanOfBeans);

	}

	@Test
	public void must_return_130_for_two_can_of_beans() {
		assertThat(orderTwoCanOfBeans.getUnits()).isEqualTo(2);
		assertThat(orderTwoCanOfBeans.getTotalPrice()).isEqualTo(priceOfTwoCanOfBeans);
		assertThat(orderTwoCanOfBeans.getTotalPriceDetails()).isEqualTo("can of beans : 2 X 0.65  = 1.3$");

	}

	@Test
	public void must_return_45_when_pricing_one_coke_available_in_special_sale() {
		assertThat(orderOneCoke.getProduct().getName()).isEqualTo("coke");
		assertThat(orderOneCoke.getUnits()).isEqualTo(1);
		assertThat(orderOneCoke.hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderOneCoke.getTotalPrice()).isEqualTo(priceOfOneCoke);
		assertThat(orderOneCoke.getTotalPriceDetails()).isEqualTo("coke :  3 X 0.45 = 1.35 Promotion price: 45$");

	}

	@Test
	public void must_return_100_when_pricing_three_coke_available_in_special_sale() {
		assertThat(orderThreeCoke.getProduct().getName()).isEqualTo("coke");
		assertThat(orderThreeCoke.getUnits()).isEqualTo(3);
		assertThat(orderThreeCoke.hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderThreeCoke.getTotalPrice()).isEqualTo(priceOfThreeCoke);
		assertThat(orderThreeCoke.getTotalPriceDetails()).isEqualTo("coke :  3 X 0.45 = 1.35 Promotion price: 100$");
	}

	@Test
	public void must_return_30_when_pricing_one_fanta_available_in_special_sale() {
		assertThat(orderOneFanta.getProduct().getName()).isEqualTo("fanta");
		assertThat(orderOneFanta.getUnits()).isEqualTo(1);
		assertThat(orderOneFanta.hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderOneFanta.getTotalPrice()).isEqualTo(priceOfOneFanta);
		assertThat(orderOneFanta.getTotalPriceDetails())
				.isEqualTo("fanta : 1 X 0.3	 = 0.3$  (Promotion) Free units: 0");
	}

	@Test
	public void must_return_30_when_pricing_four_fanta_available_in_special_sale() {
		assertThat(orderFourFanta.getProduct().getName()).isEqualTo("fanta");
		assertThat(orderFourFanta.getUnits()).isEqualTo(4);
		assertThat(orderFourFanta.hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderFourFanta.getTotalPrice()).isEqualTo(priceOfThreeFanta);
		assertThat(orderFourFanta.getTotalPrice()).isEqualTo(priceOfFourFanta);
		assertThat(orderFourFanta.getTotalPriceDetails())
				.isEqualTo("fanta : 4 X 0.3	 = 0.9$  (Promotion) Free units: 1");
	}

	@Test
	public void must_return_199_when_pricing_one_pound_of_apple_without_special_sale() {
		assertThat(orderOnePoundApple.getProduct().getName()).isEqualTo("apple");
		assertThat(orderOnePoundApple.getUnits()).isEqualTo(16);
		assertThat(orderOnePoundApple.hasSpacialPricingRule()).isEqualTo(false);
		assertThat(orderOnePoundApple.getTotalPrice()).isEqualTo(priceOfOnePoundApple);
		assertThat(orderOnePoundApple.getTotalPriceDetails()).isEqualTo("apple : 1.0 X 1.99	 = 1.99$");

	}

	@Test
	public void must_return_49_when_pricing_four_ounces_of_apple_without_special_sale() {
		assertThat(orderFourOuncesApple.getProduct().getName()).isEqualTo("apple");
		assertThat(orderFourOuncesApple.getUnits()).isEqualTo(4);
		assertThat(orderFourOuncesApple.hasSpacialPricingRule()).isEqualTo(false);
		assertThat(orderFourOuncesApple.getTotalPrice()).isEqualTo(priceOfFourOuncesApple);
		assertThat(orderFourOuncesApple.getTotalPriceDetails()).isEqualTo("apple : 0.25 X 1.99	 = 0.49$");

	}

	@Test
	public void must_return_65_details_for_one_can_of_beans() {
		assertThat(orderOneCanOfBeans.getUnits()).isEqualTo(1);
		assertThat(orderOneCanOfBeans.getTotalPrice()).isEqualTo(priceOfOneCanOfBeans);
		assertThat(orderOneCanOfBeans.getTotalPrice()).isEqualTo(priceOfOneCanOfBeans);
		assertThat(orderOneCanOfBeans.getTotalPriceDetails()).isEqualTo("can of beans : 1 X 0.65  = 0.65$");
	}

	// Chart Tests

	private static final String FIRST_CHART_PRINT = "can of beans : 1 X 0.65  = 0.65$\n"
			+ "coke :  3 X 0.45 = 1.35 Promotion price: 100$\n" + "TOTAL : 165";

	private static final String SECOND_CHART_PRINT = "fanta : 4 X 0.3	 = 0.9$  (Promotion) Free units: 1\n"
			+ "can of beans : 2 X 0.65  = 1.3$\n" + "apple : 0.25 X 1.99	 = 0.49$\n" + "TOTAL : 269";

	@Test
	public void test_validate_first_chart() {
		Chart chart = firstChart();
		assertThat(chart.print()).isEqualTo(FIRST_CHART_PRINT);
	}

	@Test
	public void test_validate_second_chart() {
		Chart chart = secondChart();
		assertThat(chart.print()).isEqualTo(SECOND_CHART_PRINT);

	}

	private Chart firstChart() {
		List<OrderProduct> orders = new ArrayList<>();
		orders.add(orderOneCanOfBeans);
		orders.add(orderThreeCoke);
		Chart firstChart = new Chart(orders);
		return firstChart;
	}

	private Chart secondChart() {
		List<OrderProduct> orders = new ArrayList<>();
		orders.add(orderFourFanta);
		orders.add(orderTwoCanOfBeans);
		orders.add(orderFourOuncesApple);
		Chart secondChart = new Chart(orders);
		return secondChart;
	}

}
