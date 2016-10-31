package example.coffe.boundary.request;

import example.coffe.entity.CoffeType;

public class BuyCoffeRequest {

	private final CoffeType coffeType;
	private final String name;

	public BuyCoffeRequest(CoffeType coffeType, String name) {
		this.coffeType = coffeType;
		this.name = name;
	}

	public CoffeType getCoffeType() {
		return coffeType;
	}
	
	public String getName() {
		return name;
	}
	
}
