package example.coffe.interactor;

import java.util.Optional;

import example.coffe.boundary.EntityGateway;
import example.coffe.boundary.InputBoundary;
import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.request.BuyCoffeRequest;
import example.coffe.boundary.response.BuyCoffeResponse;
import example.coffe.entity.Account;
import example.coffe.entity.CoffeRegister;

public class CoffeInteractor implements InputBoundary<BuyCoffeRequest> {

	private final CoffeRegister coffeRegister;
	private final EntityGateway<Account> accountRegistration;
	private final OutputBoundary<BuyCoffeResponse> buyCoffeOutputBoundary;

	public CoffeInteractor(CoffeRegister coffeRegister, EntityGateway<Account> accountRegistration,
			OutputBoundary<BuyCoffeResponse> buyCoffeOutputBoundary) {
		this.coffeRegister = coffeRegister;
		this.accountRegistration = accountRegistration;
		this.buyCoffeOutputBoundary = buyCoffeOutputBoundary;
	}

	@Override
	public void handle(BuyCoffeRequest request) {
		Optional<Account> account = accountRegistration.findById(request.getAccountId());
		if (account.isPresent()) {
			coffeRegister.debitCoffe(account.get(), request.getCoffeType());
			buyCoffeOutputBoundary.output(new BuyCoffeResponse(true));
		} else {
			buyCoffeOutputBoundary.output(new BuyCoffeResponse(false));
		}
	}

}
