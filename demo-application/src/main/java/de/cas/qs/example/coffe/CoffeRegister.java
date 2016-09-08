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
	private List<DebitPosition> debitPositions = Lists.newArrayList();

	public Bill createBill() {
		return new Bill();
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
		debitPositions.add(new DebitPosition(Instant.now(), account.getId(), coffeType, new BigDecimal("0.0")));
	}

	public Optional<DebitPosition> getMostRecentDebitPositionOfAccount(Account account) {
		DebitPosition mostRecentDebitPosition = null;

		for (DebitPosition debitPosition : debitPositions) {
			if (!debitPosition.getAccountId().equals(account.getId()))
				continue;

			if (Objects.isNull(mostRecentDebitPosition))
				mostRecentDebitPosition = debitPosition;
			else
				mostRecentDebitPosition = getLaterDebitPosition(mostRecentDebitPosition, debitPosition);
		}

		return Optional.ofNullable(mostRecentDebitPosition);
	}

	private DebitPosition getLaterDebitPosition(DebitPosition first, DebitPosition second) {
		return first.getTimestamp().isAfter(second.getTimestamp()) ? first : second;
	}

}
