package com.mbenfredj.kata.supermarket;

import java.util.ArrayList;
import java.util.List;

import com.mbenfredj.kata.supermarket.business.rules.ISpecialPricingRule;
import com.mbenfredj.kata.supermarket.business.rules.nForP;
import com.mbenfredj.kata.supermarket.business.rules.nPlusM;
import com.mbenfredj.kata.supermarket.domain.Cart;
import com.mbenfredj.kata.supermarket.domain.OrderItem;
import com.mbenfredj.kata.supermarket.domain.OrderItem.OrderProductBuilder;
import com.mbenfredj.kata.supermarket.domain.Product;
import com.mbenfredj.kata.supermarket.domain.ProductSaleType;

public class SuperMarketPricingApp {

	public static void main(String[] args) {
	//can of beans
	Product canOfBeans = Product.ProductBuilder.create().withName("can of beans")
				.withPrice(65).withSaleType(ProductSaleType.UNIT).build();
	OrderItem canOfBeansOrder = OrderProductBuilder.create().withProduct(canOfBeans).withUnits(7).build();

	//coke
	ISpecialPricingRule cokePricingRule = new nForP(3, 100);
	Product coke = Product.ProductBuilder.create().withName("coke").withPrice(45)
			.withSaleType(ProductSaleType.UNIT).withSpecialPricingRule(cokePricingRule).build();
	OrderItem cokeOrder = OrderProductBuilder.create().withProduct(coke)
			.withUnits(1).build();
	
	//fanta
	ISpecialPricingRule fantaPricingRule = new nPlusM(1, 3);
	Product fanta = Product.ProductBuilder.create().withName("fanta").withPrice(30)
			.withSaleType(ProductSaleType.UNIT).withSpecialPricingRule(fantaPricingRule).build();
	OrderItem fantaOrder = OrderProductBuilder.create().withProduct(fanta)
			.withUnits(4).build();
	
	//apple
	Product apple = Product.ProductBuilder.create().withName("apple").withPrice(299)
			.withSaleType(ProductSaleType.WEIGHT).build();
	OrderItem appleOrder = OrderProductBuilder.create().withProduct(apple).withUnits(18).build();
	
	//Total cart
	List<OrderItem> orders = new ArrayList<OrderItem>();
	orders.add(canOfBeansOrder);
	orders.add(cokeOrder);
	orders.add(fantaOrder);
	orders.add(appleOrder);
	Cart cart = new Cart(orders);
	System.out.println(cart.print());
		
	}

}
