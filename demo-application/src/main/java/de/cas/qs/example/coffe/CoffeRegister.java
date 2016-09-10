package de.cas.qs.example.coffe;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Lists;

public class CoffeRegister {

	private List<Account> accounts = Lists.newArrayList();
	private List<Debit> debits = Lists.newArrayList();
	private CoffePriceList priceList;

	public CoffeRegister(CoffePriceList priceList) {
		this.priceList = priceList;
	}

	public boolean createAccount(String name) {
		if (existsAnAccountWithName(name))
			return false;

		accounts.add(new Account(UUID.randomUUID(), name));
		return true;
	}

	private boolean existsAnAccountWithName(String name) {
		return accounts.stream().anyMatch(account -> account.getName().equals(name));
	}

	public Account getAccountOfUser(final String name) {
		Optional<Account> registeredAccount = accounts.stream().filter(account -> account.getName().equals(name))
				.findFirst();

		if (registeredAccount.isPresent())
			return registeredAccount.get();
		else
			throw new RuntimeException("There exists no account for " + name);
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
