package example.coffe.delivery.cli;

import example.coffe.boundary.UserRegistrationController;
import example.coffe.boundary.UserRegistrationPresenter;
import example.coffe.boundary.response.RegisterationUserResponse;

public class CLIPresenter implements UserRegistrationPresenter {

	public CLIPresenter(UserRegistrationController requestBoundary) {
		
	}
	
	@Override
	public void present(RegisterationUserResponse response) {
		System.out.println("Response: " + response.getMessage());
	}

}
