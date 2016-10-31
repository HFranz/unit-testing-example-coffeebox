package example.coffe.boundary;

import example.coffe.boundary.request.RegisterUserRequest;

public interface UserRegistrationController {
	
	void handle(RegisterUserRequest request);

}
