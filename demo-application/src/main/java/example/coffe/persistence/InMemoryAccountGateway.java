package example.coffe.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import example.coffe.boundary.EntityGateway;
import example.coffe.entity.Account;

public class InMemoryAccountGateway implements EntityGateway<Account> {

	private List<Account> accounts = Lists.newArrayList();

	@Override
	public Optional<Account> findById(UUID id) {
		return accounts.stream().filter(account -> account.getId().equals(id))
				.findFirst();
	}

	@Override
	public Stream<Account> findAll() {
		return accounts.stream();
	}

	@Override
	public void store(Account entity) {
		accounts.add(entity);
	}

	@Override
	public UUID getNewId() {
		return UUID.randomUUID();
	}
}
