package example.coffe.interactor;

import com.google.common.base.Strings;

import example.coffe.boundary.EntityGateway;
import example.coffe.boundary.InputBoundary;
import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;
import example.coffe.entity.Account;

public class AccountInteractor implements InputBoundary<RegisterUserRequest> {
	
	private OutputBoundary<RegisterationUserResponse> registerUserResponse;
	private EntityGateway<Account> accountGateway;
	
	public AccountInteractor(EntityGateway<Account> accountGateway, OutputBoundary<RegisterationUserResponse> registerUserResponse) {
		this.accountGateway = accountGateway;
		this.registerUserResponse = registerUserResponse;
	}
	
	@Override
	public void handle(RegisterUserRequest request) {
		RegisterationUserResponse successMessage = new RegisterationUserResponse("Success");
		RegisterationUserResponse failingMessage = new RegisterationUserResponse("Failed");
		
		if (Strings.isNullOrEmpty(request.getName())) {
			registerUserResponse.output(failingMessage);
			return;
		}

		if (true) {
			registerUserResponse.output(successMessage);
		} else {
			registerUserResponse.output(failingMessage);
		}
	
	}

}
