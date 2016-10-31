package example.coffe.boundary;

import java.util.List;

import example.coffe.entity.Account;

public interface AccountGateway {

	boolean createAccount(String name);

	Account getAccountOfUser(String name);

	List<Account> getRegisteredAccounts();

}