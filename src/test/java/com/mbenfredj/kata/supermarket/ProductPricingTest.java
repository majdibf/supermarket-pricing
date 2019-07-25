package com.mbenfredj.kata.supermarket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mbenfredj.kata.supermarket.business.OrderProductService;
import com.mbenfredj.kata.supermarket.business.rules.ISpecialPricingRule;
import com.mbenfredj.kata.supermarket.business.rules.nForP;
import com.mbenfredj.kata.supermarket.business.rules.nPlusM;
import com.mbenfredj.kata.supermarket.domain.Cart;
import com.mbenfredj.kata.supermarket.domain.OrderItem;
import com.mbenfredj.kata.supermarket.domain.OrderItem.OrderProductBuilder;
import com.mbenfredj.kata.supermarket.domain.Product;
import com.mbenfredj.kata.supermarket.domain.ProductSaleType;

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
	
	// SpecialPricingRules
	private ISpecialPricingRule nForP = new nForP(3, 100);
	private ISpecialPricingRule nPlusM = new nPlusM(1, 3);

	// Products
	private Product canOfBeans = Product.ProductBuilder.create().withName("can of beans")
			.withPrice(priceOfOneCanOfBeans).withSaleType(ProductSaleType.UNIT).build();
	private Product coke = Product.ProductBuilder.create().withName("coke").withPrice(priceOfOneCoke)
			.withSaleType(ProductSaleType.UNIT).withSpecialPricingRule(nForP).build();
	private Product fanta = Product.ProductBuilder.create().withName("fanta").withPrice(priceOfOneFanta).withSpecialPricingRule(nPlusM)
			.withSaleType(ProductSaleType.UNIT).build();

	private Product apple = Product.ProductBuilder.create().withName("apple").withPrice(priceOfOnePoundApple)
			.withSaleType(ProductSaleType.WEIGHT).build();



	// OrderProducts
	// can of beans orders
	private OrderItem orderOneCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(1).build();
	private OrderItem orderTwoCanOfBeans = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(2).build();
	// coke orders
	private OrderItem orderOneCoke = OrderProductBuilder.create().withProduct(coke)
			.withUnits(1).build();
	private OrderItem orderThreeCoke = OrderProductBuilder.create().withProduct(coke)
			.withUnits(3).build();
	// fanta orders
	private OrderItem orderOneFanta = OrderProductBuilder.create().withProduct(fanta)
			.withUnits(1).build();
	private OrderItem orderFourFanta = OrderProductBuilder.create().withProduct(fanta)
			.withUnits(4).build();

	// apple
	private OrderItem orderOnePoundApple = OrderProductBuilder.create().withProduct(apple).withUnits(16).build();
	private OrderItem orderFourOuncesApple = OrderProductBuilder.create().withProduct(apple).withUnits(4).build();

	//Services
	OrderProductService orderProductService = new OrderProductService();
	@Test
	public void must_return_65_for_one_can_of_beans() {
		assertThat(orderOneCanOfBeans.getUnits()).isEqualTo(1);
		assertThat(orderProductService.calculateOrderItemPrice(orderOneCanOfBeans)).isEqualTo(priceOfOneCanOfBeans);

	}

	@Test
	public void must_return_130_for_two_can_of_beans() {
		assertThat(orderTwoCanOfBeans.getUnits()).isEqualTo(2);
		assertThat(orderProductService.calculateOrderItemPrice(orderTwoCanOfBeans)).isEqualTo(priceOfTwoCanOfBeans);
		assertThat(orderProductService.getTotalPriceDetails(orderTwoCanOfBeans)).isEqualTo("can of beans : 2 X 0.65  = 1.3$");

	}

	@Test
	public void must_return_45_when_pricing_one_coke_available_in_special_sale() {
		assertThat(orderOneCoke.getProduct().getName()).isEqualTo("coke");
		assertThat(orderOneCoke.getUnits()).isEqualTo(1);
		assertThat(orderOneCoke.getProduct().hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderProductService.calculateOrderItemPrice(orderOneCoke)).isEqualTo(priceOfOneCoke);
		assertThat(orderProductService.getTotalPriceDetails(orderOneCoke)).isEqualTo("coke :  3 X 0.45 = 1.35 Promotion price: 45$");

	}

	@Test
	public void must_return_100_when_pricing_three_coke_available_in_special_sale() {
		assertThat(orderThreeCoke.getProduct().getName()).isEqualTo("coke");
		assertThat(orderThreeCoke.getUnits()).isEqualTo(3);
		assertThat(orderThreeCoke.getProduct().hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderProductService.calculateOrderItemPrice(orderThreeCoke)).isEqualTo(priceOfThreeCoke);
		assertThat(orderProductService.getTotalPriceDetails(orderThreeCoke)).isEqualTo("coke :  3 X 0.45 = 1.35 Promotion price: 100$");
	}

	@Test
	public void must_return_30_when_pricing_one_fanta_available_in_special_sale() {
		assertThat(orderOneFanta.getProduct().getName()).isEqualTo("fanta");
		assertThat(orderOneFanta.getUnits()).isEqualTo(1);
		assertThat(orderOneFanta.getProduct().hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderProductService.calculateOrderItemPrice(orderOneFanta)).isEqualTo(priceOfOneFanta);
		assertThat(orderProductService.getTotalPriceDetails(orderOneFanta))
				.isEqualTo("fanta : 1 X 0.3	 = 0.3$  (Promotion) Free units: 0");
	}

	@Test
	public void must_return_30_when_pricing_four_fanta_available_in_special_sale() {
		assertThat(orderFourFanta.getProduct().getName()).isEqualTo("fanta");
		assertThat(orderFourFanta.getUnits()).isEqualTo(4);
		assertThat(orderFourFanta.getProduct().hasSpacialPricingRule()).isEqualTo(true);
		assertThat(orderProductService.calculateOrderItemPrice(orderFourFanta)).isEqualTo(priceOfThreeFanta);
		assertThat(orderProductService.calculateOrderItemPrice(orderFourFanta)).isEqualTo(priceOfFourFanta);
		assertThat(orderProductService.getTotalPriceDetails(orderFourFanta))
				.isEqualTo("fanta : 4 X 0.3	 = 0.9$  (Promotion) Free units: 1");
	}

	@Test
	public void must_return_199_when_pricing_one_pound_of_apple_without_special_sale() {
		assertThat(orderOnePoundApple.getProduct().getName()).isEqualTo("apple");
		assertThat(orderOnePoundApple.getUnits()).isEqualTo(16);
		assertThat(orderOnePoundApple.getProduct().hasSpacialPricingRule()).isEqualTo(false);
		assertThat(orderProductService.calculateOrderItemPrice(orderOnePoundApple)).isEqualTo(priceOfOnePoundApple);
		assertThat(orderProductService.getTotalPriceDetails(orderOnePoundApple)).isEqualTo("apple : 1.0 X 1.99	 = 1.99$");

	}

	@Test
	public void must_return_49_when_pricing_four_ounces_of_apple_without_special_sale() {
		assertThat(orderFourOuncesApple.getProduct().getName()).isEqualTo("apple");
		assertThat(orderFourOuncesApple.getUnits()).isEqualTo(4);
		assertThat(orderFourOuncesApple.getProduct().hasSpacialPricingRule()).isEqualTo(false);
		assertThat(orderProductService.calculateOrderItemPrice(orderFourOuncesApple)).isEqualTo(priceOfFourOuncesApple);
		assertThat(orderProductService.getTotalPriceDetails(orderFourOuncesApple)).isEqualTo("apple : 0.25 X 1.99	 = 0.49$");

	}

	@Test
	public void must_return_65_details_for_one_can_of_beans() {
		assertThat(orderOneCanOfBeans.getUnits()).isEqualTo(1);
		assertThat(orderProductService.calculateOrderItemPrice(orderOneCanOfBeans)).isEqualTo(priceOfOneCanOfBeans);
		assertThat(orderProductService.calculateOrderItemPrice(orderOneCanOfBeans)).isEqualTo(priceOfOneCanOfBeans);
		assertThat(orderProductService.getTotalPriceDetails(orderOneCanOfBeans)).isEqualTo("can of beans : 1 X 0.65  = 0.65$");
	}

	// Cart Tests

	private static final String FIRST_CART_PRINT = "can of beans : 1 X 0.65  = 0.65$\n"
			+ "coke :  3 X 0.45 = 1.35 Promotion price: 100$\n" + "TOTAL : 165";

	private static final String SECOND_CART_PRINT = "fanta : 4 X 0.3	 = 0.9$  (Promotion) Free units: 1\n"
			+ "can of beans : 2 X 0.65  = 1.3$\n" + "apple : 0.25 X 1.99	 = 0.49$\n" + "TOTAL : 269";

	@Test
	public void test_validate_first_cart() {
		Cart cart = firstCart();
		assertThat(cart.print()).isEqualTo(FIRST_CART_PRINT);
	}

	@Test
	public void test_validate_second_cart() {
		Cart cart = secondCart();
		assertThat(cart.print()).isEqualTo(SECOND_CART_PRINT);

	}

	private Cart firstCart() {
		List<OrderItem> orders = new ArrayList<>();
		orders.add(orderOneCanOfBeans);
		orders.add(orderThreeCoke);
		Cart firstCart = new Cart(orders);
		return firstCart;
	}

	private Cart secondCart() {
		List<OrderItem> orders = new ArrayList<>();
		orders.add(orderFourFanta);
		orders.add(orderTwoCanOfBeans);
		orders.add(orderFourOuncesApple);
		Cart secondCart = new Cart(orders);
		return secondCart;
	}

}
