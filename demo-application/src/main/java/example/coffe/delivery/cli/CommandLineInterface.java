package example.coffe.delivery.cli;

import example.coffe.boundary.UserRegistrationResponseBoundary;
import example.coffe.boundary.response.RegisterationUserResponse;

public class CommandLineInterface implements UserRegistrationResponseBoundary {

	@Override
	public void present(RegisterationUserResponse response) {
		System.out.println("Response: " + response.getMessage());
	}

}
