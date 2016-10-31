package example.coffe.application;

import com.google.common.base.Strings;

import example.coffe.domain.Account;
import example.coffe.domain.AccountRegistration;
import example.coffe.domain.CoffeRegister;

public class CoffeBoxApplication {
	
	private final CoffeRegister coffeRegister;
	private final AccountRegistration accountRegistration;
	private final CoffeBoxOutputBoundary outputBoundary;
	
	public CoffeBoxApplication(CoffeRegister coffeRegister, AccountRegistration accountRegistration, CoffeBoxOutputBoundary outputBoundary) {
		this.coffeRegister = coffeRegister;
		this.accountRegistration = accountRegistration;
		this.outputBoundary = outputBoundary;
	}

	public void registerUser(CreateUserRequest request) {
		UserRegisterationResponse successMessage = new UserRegisterationResponse("Success");
		UserRegisterationResponse failingMessage = new UserRegisterationResponse("Failed");
		
		if (Strings.isNullOrEmpty(request.getName())) {
			outputBoundary.showRegistrationResponse(failingMessage);
			return;
		}

		if (accountRegistration.createAccount(request.getName())) {
			outputBoundary.showRegistrationResponse(successMessage);
		} else {
			outputBoundary.showRegistrationResponse(failingMessage);
		}
		
	}

	public void buyCoffe(BuyCoffeRequest request) {
		Account account = accountRegistration.getAccountOfUser(request.getName());
		coffeRegister.debitCoffe(account, request.getCoffeType());
		outputBoundary.showBuyCoffeResponse(new BuyCoffeResponse("Success"));
	}

}
