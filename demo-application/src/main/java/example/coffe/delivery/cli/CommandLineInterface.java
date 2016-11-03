package example.coffe.delivery.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineInterface {
	
	private CLIController controller;
	private CLIPresenter presenter;

	public CommandLineInterface(CLIController controller, CLIPresenter presenter) {
		this.controller = controller;
		this.presenter = presenter;
	}

	public void start() {
		boolean keepRunning = true;
		
		System.out.println("Hello I'm the CoffeBox. What can I do for you?");
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (keepRunning) {
			try {
				String nextLine = br.readLine();
				
				boolean isRegistrationCommand = nextLine.startsWith("Register");
				
				if (isRegistrationCommand) {
					String name = nextLine.substring("Register".length(), nextLine.length()).toString();
					controller.createNewAccount(name);
					String viewModel = presenter.getViewModel();
					System.out.println(viewModel);
				} else {
					System.err.println("Unkown command '" + nextLine + "'");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
