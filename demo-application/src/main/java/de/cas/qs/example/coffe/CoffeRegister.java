package de.cas.qs.example.coffe;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

public class CoffeRegister {

	private List<Account> accounts = Lists.newArrayList();
	
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

}
