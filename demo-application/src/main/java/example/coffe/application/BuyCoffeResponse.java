package example.coffe.application;

import java.util.Objects;

public class BuyCoffeResponse {

	private final String message;

	public BuyCoffeResponse(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj == null || obj.getClass() != BuyCoffeResponse.class)
			return false;

		BuyCoffeResponse other = (BuyCoffeResponse) obj;
		
		return Objects.equals(message, other.message);
	}
	
}
