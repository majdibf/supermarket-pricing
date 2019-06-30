package pricing.rules;

import com.mbenfredj.kata.supermarket.AmountConverter;
import com.mbenfredj.kata.supermarket.OrderProduct;

public class nForP implements ISpecialPricingRule {

	private Integer units; // N units
	private Integer price; // For P price

	public nForP(Integer units, Integer price) {
		super();
		this.units = units;
		this.price = price;
	}

	@Override
	public Integer getTotalPrice(OrderProduct orderProduct) {
		return (this.price * (orderProduct.getUnits() / this.units))
				+ (orderProduct.getProduct().getPrice() * (orderProduct.getUnits() % this.units));
	}

	@Override
	public String getTotalPriceDetails(OrderProduct orderProduct) {
		return new StringBuilder().append(orderProduct.getProduct().getName())
				 .append(" :  ")
				 .append(this.units)
				 .append(" X ")
				 .append(AmountConverter.convertToDollar(orderProduct.getProduct().getPrice()))
				 .append(" = ")
				 .append(AmountConverter.convertToDollar(this.units * orderProduct.getProduct().getPrice()))
				 .append(" Promotion price: "+orderProduct.getTotalPrice())
				 .append("$")
				 .toString();
	}

}
