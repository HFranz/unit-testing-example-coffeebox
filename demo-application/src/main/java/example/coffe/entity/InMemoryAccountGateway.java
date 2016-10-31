package example.coffe.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Lists;

import example.coffe.boundary.AccountGateway;

public class InMemoryAccountGateway implements AccountGateway {
	
	private List<Account> accounts = Lists.newArrayList();

	@Override
	public boolean createAccount(String name) {
		if (existsAnAccountWithName(name))
			return false;

		accounts.add(new Account(UUID.randomUUID(), name));
		return true;
	}
	
	@Override
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

	@Override
	public List<Account> getRegisteredAccounts() {
		return Collections.unmodifiableList(accounts);
	}
}
