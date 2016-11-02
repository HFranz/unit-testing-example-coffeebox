package example.coffe.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import example.coffe.boundary.EntityGateway;

public class InMemoryAccountGateway implements EntityGateway<Account> {

	private List<Account> accounts = Lists.newArrayList();

	@Override
	public Account findById(String id) {
		Optional<Account> registeredAccount = accounts.stream().filter(account -> account.getName().equals(id))
				.findFirst();

		return registeredAccount.orElseThrow(() -> new RuntimeException("There exists no account for " + id));
	}

	@Override
	public List<Account> findAll() {
		return Collections.unmodifiableList(accounts);
	}

	@Override
	public void store(Account entity) {
		accounts.add(entity);
	}
}
