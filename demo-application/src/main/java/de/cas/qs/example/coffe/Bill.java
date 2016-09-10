package de.cas.qs.example.coffe;

import java.math.BigDecimal;

public class Bill {

	private Account account;

	public Bill(Account account) {
		this.account = account;
	}

	public boolean isEmpty() {
		return true;
	}

	public Object getSum() {
		return new BigDecimal("0.5");
	}

}
