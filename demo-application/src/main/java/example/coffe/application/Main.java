package example.coffe.application;

import example.coffe.boundary.EntityGateway;
import example.coffe.delivery.cli.CLIController;
import example.coffe.delivery.cli.CLIPresenter;
import example.coffe.delivery.cli.CommandLineInterface;
import example.coffe.entity.Account;
import example.coffe.interactor.AccountInteractor;
import example.coffe.persistence.InMemoryAccountGateway;

public class Main {

	public static void main(String[] args) {	
		EntityGateway<Account> accountGateway = new InMemoryAccountGateway();
		CLIPresenter presenter = new CLIPresenter();
		AccountInteractor urc = new AccountInteractor(accountGateway, presenter);
		CLIController controller = new CLIController(urc);
		CommandLineInterface cli = new CommandLineInterface(controller, presenter);
		cli.start();
	}

}
