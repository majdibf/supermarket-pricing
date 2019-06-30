package pricing.rules;

import com.mbenfredj.kata.supermarket.OrderProduct;

public interface ISpecialPricingRule {

	Integer getTotalPrice(OrderProduct orderProduct);

	String getTotalPriceDetails(OrderProduct orderProduct);
	

}
