package pricing.rules;

import com.mbenfredj.kata.supermarket.AmountConverter;
import com.mbenfredj.kata.supermarket.OrderProduct;

public class nPlusM implements ISpecialPricingRule {
	private Integer freeUnit; // N free unit from
	private Integer purchasedUnit; // M purchased unit

	public nPlusM(Integer freeUnit, Integer purchasedUnit) {
		super();
		this.freeUnit = freeUnit;
		this.purchasedUnit = purchasedUnit;
	}

	@Override
	public Integer getTotalPrice(OrderProduct orderProduct) {
		Integer nbFreeUnit = orderProduct.getUnits() / purchasedUnit * freeUnit;
		return orderProduct.getProduct().getPrice() * (orderProduct.getUnits() - nbFreeUnit);
	}

	@Override
	public String getTotalPriceDetails(OrderProduct orderProduct) {
		return new StringBuilder().append(orderProduct.getProduct().getName())
				 .append(" : ")
				 .append(orderProduct.getUnits())
				 .append(" X ")
				 .append(AmountConverter.convertToDollar(orderProduct.getProduct().getPrice()))
				 .append("\t")
				 .append(" = ")
				 .append(AmountConverter.convertToDollar(orderProduct.getTotalPrice()))
				 .append("$ ")
				 .append(" (Promotion) Free units: "+ orderProduct.getUnits() / purchasedUnit * freeUnit)
				 .toString();
	}

}
