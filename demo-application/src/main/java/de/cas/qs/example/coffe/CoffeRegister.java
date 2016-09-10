package de.cas.qs.example.coffe;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.Lists;

public class CoffeRegister {

	private AccountRegistration accountRegistration;
	private List<Account> accounts = Lists.newArrayList();
	private List<Debit> debits = Lists.newArrayList();
	private CoffePriceList priceList;

	public CoffeRegister(CoffePriceList priceList, AccountRegistration accountRegistration) {
		this.priceList = priceList;
		this.accountRegistration = accountRegistration;
	}

	public void debitCoffe(Account account, CoffeType coffeType) {
		debits.add(new Debit(Instant.now(), account.getId(), coffeType, priceList.getPrice(coffeType)));
	}

	public Bill createBill(Account account) {
		BigDecimal calculatedSum = debits.stream()
				.filter(debit -> Objects.equals(account.getId(), debit.getAccountId()))
				.map(debit -> debit.getPrice())
				.reduce(new BigDecimal("0.0"), (partialSum, price) -> partialSum.add(price));
		return new Bill(account, calculatedSum);
	}

}
