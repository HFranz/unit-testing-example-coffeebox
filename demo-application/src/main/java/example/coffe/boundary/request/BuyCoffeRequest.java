package example.coffe.boundary.request;

import java.util.Objects;
import java.util.UUID;

import example.coffe.entity.CoffeType;

public class BuyCoffeRequest {

	private final CoffeType coffeType;
	private final UUID accountId;

	public BuyCoffeRequest(CoffeType coffeType, UUID accountId) {
		this.coffeType = coffeType;
		this.accountId = accountId;
	}

	public CoffeType getCoffeType() {
		return coffeType;
	}

	public UUID getAccountId() {
		return accountId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj == null || obj.getClass() != BuyCoffeRequest.class)
			return false;

		BuyCoffeRequest other = (BuyCoffeRequest) obj;

		return Objects.equals(coffeType, other.coffeType) && Objects.equals(accountId, other.accountId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(coffeType, accountId);
	}

	@Override
	public String toString() {
		return "BuyCoffeRequest [coffeType=" + coffeType + ", accountId=" + accountId + "]";
	}
}
