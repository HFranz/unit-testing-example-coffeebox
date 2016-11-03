package example.coffe.boundary.response;

import java.util.Objects;

public class BuyCoffeResponse {

	private final boolean successfull;

	public BuyCoffeResponse(boolean successfull) {
		this.successfull = successfull;
	}
	
	public boolean isSuccessfull() {
		return successfull;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj == null || obj.getClass() != BuyCoffeResponse.class)
			return false;

		BuyCoffeResponse other = (BuyCoffeResponse) obj;
		
		return Objects.equals(successfull, other.successfull);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(successfull);
	}
	
}
