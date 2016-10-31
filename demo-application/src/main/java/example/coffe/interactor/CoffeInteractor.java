package example.coffe.interactor;

import example.coffe.boundary.CoffeRequestBoundary;
import example.coffe.boundary.CoffeResponseBoundary;
import example.coffe.boundary.request.BuyCoffeRequest;
import example.coffe.boundary.response.BuyCoffeResponse;
import example.coffe.entity.Account;
import example.coffe.entity.AccountRegistration;
import example.coffe.entity.CoffeRegister;

public class CoffeInteractor implements CoffeRequestBoundary {
	
	private final CoffeRegister coffeRegister;
	private final AccountRegistration accountRegistration;
	private final CoffeResponseBoundary outputBoundary;
	
	public CoffeInteractor(CoffeRegister coffeRegister, AccountRegistration accountRegistration, CoffeResponseBoundary outputBoundary) {
		this.coffeRegister = coffeRegister;
		this.accountRegistration = accountRegistration;
		this.outputBoundary = outputBoundary;
	}

	@Override
	public void handle(BuyCoffeRequest request) {
		Account account = accountRegistration.getAccountOfUser(request.getName());
		coffeRegister.debitCoffe(account, request.getCoffeType());
		outputBoundary.present(new BuyCoffeResponse("Success"));
		
	}

}
