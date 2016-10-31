package example.coffe.delivery.cli;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import example.coffe.boundary.UserRegistrationController;
import example.coffe.boundary.UserRegistrationPresenter;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;

public class CommandLineInterface implements UserRegistrationPresenter {
	
	private UserRegistrationController userRegistrationController;

	public CommandLineInterface(UserRegistrationController userRegistrationController) {
		this.userRegistrationController = userRegistrationController;
	}

	@Override
	public void present(RegisterationUserResponse response) {
		System.out.println("Response: " + response.getMessage());
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
					userRegistrationController.handle(new RegisterUserRequest(name));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
