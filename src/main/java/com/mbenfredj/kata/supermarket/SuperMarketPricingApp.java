package com.mbenfredj.kata.supermarket;

import java.util.ArrayList;
import java.util.List;

import com.mbenfredj.kata.supermarket.OrderProduct.OrderProductBuilder;

import pricing.rules.ISpecialPricingRule;
import pricing.rules.nForP;
import pricing.rules.nPlusM;

public class SuperMarketPricingApp {

	public static void main(String[] args) {
	//can of beans
	Product canOfBeans = Product.ProductBuilder.create().withName("can of beans")
				.withPrice(65).withSaleType(ProductSaleType.UNIT).build();
	OrderProduct canOfBeansOrder = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(7).build();

	//coke
	Product coke = Product.ProductBuilder.create().withName("coke").withPrice(45)
			.withSaleType(ProductSaleType.UNIT).build();
	ISpecialPricingRule cokePricingRule = new nForP(3, 100);
	OrderProduct cokeOrder = OrderProductBuilder.create().withProduct(coke).withSpecialPricingRule(cokePricingRule)
			.withUnits(1).build();
	
	//fanta
	Product fanta = Product.ProductBuilder.create().withName("fanta").withPrice(30)
			.withSaleType(ProductSaleType.UNIT).build();
	ISpecialPricingRule fantaPricingRule = new nPlusM(1, 3);
	OrderProduct fantaOrder = OrderProductBuilder.create().withProduct(fanta).withSpecialPricingRule(fantaPricingRule)
			.withUnits(4).build();
	
	//apple
	Product apple = Product.ProductBuilder.create().withName("apple").withPrice(299)
			.withSaleType(ProductSaleType.WEIGHT).build();
	OrderProduct appleOrder = OrderProductBuilder.create().withProduct(apple).withUnits(18).build();
	
	//Total chart
	List<OrderProduct> orders = new ArrayList<OrderProduct>();
	orders.add(canOfBeansOrder);
	orders.add(cokeOrder);
	orders.add(fantaOrder);
	orders.add(appleOrder);
	Chart chart = new Chart(orders);
	System.out.println(chart.print());
		
	}

}
