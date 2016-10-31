package example.coffe.boundary;

import example.coffe.boundary.request.RegisterUserRequest;

public interface UserRegistrationRequestBoundary {
	
	void handle(RegisterUserRequest request);

}
