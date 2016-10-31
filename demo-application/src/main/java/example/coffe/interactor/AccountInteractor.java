package example.coffe.interactor;

import com.google.common.base.Strings;

import example.coffe.boundary.AccountGateway;
import example.coffe.boundary.UserRegistrationController;
import example.coffe.boundary.UserRegistrationPresenter;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;

public class AccountInteractor implements UserRegistrationController {
	
	private UserRegistrationPresenter userRegistrationPresenter;
	private AccountGateway accountGateway;
	
	public AccountInteractor(AccountGateway accountGateway) {
		this.accountGateway = accountGateway;
	}
	
	@Override
	public void handle(RegisterUserRequest request) {
		RegisterationUserResponse successMessage = new RegisterationUserResponse("Success");
		RegisterationUserResponse failingMessage = new RegisterationUserResponse("Failed");
		
		if (Strings.isNullOrEmpty(request.getName())) {
			userRegistrationPresenter.present(failingMessage);
			return;
		}

		if (accountGateway.createAccount(request.getName())) {
			userRegistrationPresenter.present(successMessage);
		} else {
			userRegistrationPresenter.present(failingMessage);
		}
	
	}

	public void setUserRegistrationPresenter(UserRegistrationPresenter presenter) {
		this.userRegistrationPresenter = presenter;
	}
}
