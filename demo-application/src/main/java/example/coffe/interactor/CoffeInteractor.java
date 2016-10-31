package example.coffe.interactor;

import example.coffe.boundary.AccountGateway;
import example.coffe.boundary.CoffeController;
import example.coffe.boundary.CoffePresenter;
import example.coffe.boundary.request.BuyCoffeRequest;
import example.coffe.boundary.response.BuyCoffeResponse;
import example.coffe.entity.Account;
import example.coffe.entity.CoffeRegister;

public class CoffeInteractor implements CoffeController {
	
	private final CoffeRegister coffeRegister;
	private final AccountGateway accountRegistration;
	private final CoffePresenter outputBoundary;
	
	public CoffeInteractor(CoffeRegister coffeRegister, AccountGateway accountRegistration, CoffePresenter outputBoundary) {
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
