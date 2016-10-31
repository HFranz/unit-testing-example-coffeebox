package example.coffe.application;

import example.coffe.boundary.AccountGateway;
import example.coffe.delivery.cli.CommandLineInterface;
import example.coffe.entity.InMemoryAccountGateway;
import example.coffe.interactor.AccountInteractor;

public class Main {

	public static void main(String[] args) {	
		AccountGateway accountGateway = new InMemoryAccountGateway();
		AccountInteractor urc = new AccountInteractor(accountGateway);
		CommandLineInterface cli = new CommandLineInterface(urc);
		urc.setUserRegistrationPresenter(cli);
		cli.start();
	}

}
