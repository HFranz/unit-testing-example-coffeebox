package example.coffe.interactor;

import com.google.common.base.Strings;

import example.coffe.boundary.UserRegistrationRequestBoundary;
import example.coffe.boundary.UserRegistrationResponseBoundary;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;
import example.coffe.entity.AccountRegistration;

public class AccountInteractor implements UserRegistrationRequestBoundary {
	
	private UserRegistrationResponseBoundary presenter;
	private AccountRegistration accountRegistration;
	
	public AccountInteractor(AccountRegistration accountRegistration, UserRegistrationResponseBoundary presenter) {
		this.accountRegistration = accountRegistration;
		this.presenter = presenter;
	}
	
	@Override
	public void handle(RegisterUserRequest request) {
		RegisterationUserResponse successMessage = new RegisterationUserResponse("Success");
		RegisterationUserResponse failingMessage = new RegisterationUserResponse("Failed");
		
		if (Strings.isNullOrEmpty(request.getName())) {
			presenter.present(failingMessage);
			return;
		}

		if (accountRegistration.createAccount(request.getName())) {
			presenter.present(successMessage);
		} else {
			presenter.present(failingMessage);
		}
	
	}

}
