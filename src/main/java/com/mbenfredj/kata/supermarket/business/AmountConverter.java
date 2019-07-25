package com.mbenfredj.kata.supermarket.business;

public class AmountConverter {

	public static Double convertToDollar(Integer amount) {
		return amount/(double)100;
	}
}
