package example.coffe.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import example.coffe.boundary.EntityGateway;

public class CoffeRegister {

	private EntityGateway<Account> accountGateway;
	private CoffePriceList priceList;
	private List<Debit> debits = Lists.newArrayList();

	public CoffeRegister(CoffePriceList priceList, EntityGateway<Account> accountGateway) {
		this.priceList = priceList;
		this.accountGateway = accountGateway;
	}

	public void debitCoffe(Account account, CoffeType coffeType) {
		BigDecimal priceForCoffeType = priceList.getPrice(coffeType);
		Debit newDebit = new Debit(Instant.now(), account.getId(), coffeType, priceForCoffeType);
		debits.add(newDebit);
	}

	public CoffeBill createBill() {
		Map<UUID, BigDecimal> calculatedSums = Maps.newHashMap();

		accountGateway.findAll().forEach(account -> {
			List<Debit> accountDebitsForNextBill = debits.stream() //
					.filter(debit -> account.getId().equals(debit.getAccountId())) //
					.collect(Collectors.toList());
			BigDecimal calculatedSum = accountDebitsForNextBill.stream() //
					.map(debit -> debit.getPrice()) //
					.reduce(BigDecimal.ZERO, (sum, price) -> sum.add(price));
			calculatedSums.put(account.getId(), calculatedSum);
			debits.removeAll(accountDebitsForNextBill);
		});
		return new CoffeBill(calculatedSums);
	}

}
