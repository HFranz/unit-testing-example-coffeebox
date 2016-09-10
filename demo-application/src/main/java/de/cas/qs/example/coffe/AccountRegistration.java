package de.cas.qs.example.coffe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Lists;

public class AccountRegistration {
	
	private List<Account> accounts = Lists.newArrayList();

	public boolean createAccount(String name) {
		if (existsAnAccountWithName(name))
			return false;

		accounts.add(new Account(UUID.randomUUID(), name));
		return true;
	}
	
	public Account getAccountOfUser(final String name) {
		Optional<Account> registeredAccount = accounts.stream().filter(account -> account.getName().equals(name))
				.findFirst();

		if (registeredAccount.isPresent())
			return registeredAccount.get();
		else
			throw new RuntimeException("There exists no account for " + name);
	}

	private boolean existsAnAccountWithName(String name) {
		return accounts.stream().anyMatch(account -> account.getName().equals(name));
	}

	public List<Account> getRegisteredAccounts() {
		return Collections.unmodifiableList(accounts);
	}
}
