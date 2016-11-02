package example.coffe.delivery.cli;

import example.coffe.boundary.InputBoundary;
import example.coffe.boundary.request.RegisterUserRequest;

public class CLIController {
	
	private InputBoundary<RegisterUserRequest> registerUser;

	public CLIController(InputBoundary<RegisterUserRequest> registerUser) {
		this.registerUser = registerUser;
	}
	
	public void createNewAccount(String name) {
		registerUser.handle(new RegisterUserRequest(name));
	}

}
