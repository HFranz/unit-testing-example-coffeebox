package de.cas.qs.example.coffe.domain;

import java.math.BigDecimal;
import java.util.Map;

import com.google.common.collect.Maps;

public class CoffePriceList {

	private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal("0.0");
	private Map<CoffeType, BigDecimal> prices = Maps.newHashMap();
	
	public void definePrice(CoffeType coffeType, BigDecimal unitPrice) {
		prices.put(coffeType, unitPrice);
	}

	public BigDecimal getPrice(CoffeType coffeType) {
		return prices.getOrDefault(coffeType, DEFAULT_UNIT_PRICE);
	}

}
