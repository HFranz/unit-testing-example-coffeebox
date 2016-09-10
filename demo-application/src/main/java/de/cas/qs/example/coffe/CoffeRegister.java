package de.cas.qs.example.coffe;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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

	public CoffeBill createBill() {
		Map<UUID, BigDecimal> calculatedSums = Maps.newHashMap();
		for (Account account : accountRegistration.getRegisteredAccounts()) {
			BigDecimal calculatedSum = debits.stream()
					.filter(debit -> account.getId().equals(debit.getAccountId()))
					.map(debit -> debit.getPrice())
					.reduce(new BigDecimal("0.0"), (partialSum, price) -> partialSum.add(price));
			calculatedSums.put(account.getId(), calculatedSum);
		}
		return new CoffeBill(calculatedSums);
	}

}
