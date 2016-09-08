package de.cas.qs.example.coffe;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class DebitPosition {
	
	private UUID accountId;
	private CoffeType coffe;
	private BigDecimal price;
	private Instant timestamp;
	
	public DebitPosition(Instant timestamp, UUID accountId, CoffeType coffe, BigDecimal price) {
		this.timestamp = timestamp;
		this.accountId = accountId;
		this.coffe = coffe;
		this.price = price;
	}

	public UUID getAccountId() {
		return accountId;
	}
	
	public CoffeType getCoffe() {
		return coffe;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public Instant getTimestamp() {
		return timestamp;
	}

}
