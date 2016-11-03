package example.coffe.interactor;

import java.util.Optional;

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

	public AccountInteractor(EntityGateway<Account> accountGateway,
			OutputBoundary<RegisterationUserResponse> registerUserResponse) {
		this.accountGateway = accountGateway;
		this.registerUserResponse = registerUserResponse;
	}

	@Override
	public void handle(RegisterUserRequest request) {
		String requestedAccountName = request.getName();

		if (Strings.isNullOrEmpty(requestedAccountName)) {
			sendFailingMessage();
			return;
		}
		
		Optional<Account> findById = accountGateway.findAll() //
				.filter(a -> a.getName().equals(requestedAccountName)) //
				.findFirst();

		if (findById.isPresent()) {
			sendFailingMessage();
		} else {
			Account newAccount = new Account(accountGateway.getNewId(), requestedAccountName);
			accountGateway.store(newAccount);
			registerUserResponse.output(new RegisterationUserResponse(true, newAccount));
		}

	}

	private void sendFailingMessage() {
		RegisterationUserResponse failingMessage = new RegisterationUserResponse(false);
		registerUserResponse.output(failingMessage);
	}

}
