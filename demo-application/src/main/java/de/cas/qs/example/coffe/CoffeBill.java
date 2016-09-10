package de.cas.qs.example.coffe;

import java.math.BigDecimal;

public class CoffeBill {

	private Account account;
	private BigDecimal sum;

	public CoffeBill(Account account, BigDecimal sum) {
		this.account = account;
		this.sum = sum;
	}

	public BigDecimal getSum() {
		return sum;
	}
	
	public Account getAccount() {
		return account;
	}

}
