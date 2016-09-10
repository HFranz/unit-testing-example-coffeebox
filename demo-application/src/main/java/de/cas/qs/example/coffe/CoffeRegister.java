package de.cas.qs.example.coffe;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

public class CoffeRegister {

	private AccountRegistration accountRegistration;
	private CoffePriceList priceList;
	private List<Debit> debits = Lists.newArrayList();

	public CoffeRegister(CoffePriceList priceList, AccountRegistration accountRegistration) {
		this.priceList = priceList;
		this.accountRegistration = accountRegistration;
	}

	public void debitCoffe(Account account, CoffeType coffeType) {
		BigDecimal priceForCoffeType = priceList.getPrice(coffeType);
		Debit newDebit = new Debit(Instant.now(), account.getId(), coffeType, priceForCoffeType);
		debits.add(newDebit);
	}

	public Bill createBill(Account account) {
		BigDecimal calculatedSum = debits.stream()
				.filter(debit -> account.getId().equals(debit.getAccountId()))
				.map(debit -> debit.getPrice())
				.reduce(new BigDecimal("0.0"), (partialSum, price) -> partialSum.add(price));
		return new Bill(account, calculatedSum);
	}

}
