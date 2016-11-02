package example.coffe.interactor;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import example.coffe.boundary.EntityGateway;
import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;
import example.coffe.entity.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountInteractorTest {
	
	private AccountInteractor interactor;
	@Mock
	private OutputBoundary<RegisterationUserResponse> presenter;
	@Mock
	private EntityGateway<Account> accountRegistration;
	
	@Before
	public void setup() {
		interactor = new AccountInteractor(accountRegistration, presenter);
	}
	
	@Test
	public void testThatUserRegistrationFails() throws Exception {
		interactor.handle(new RegisterUserRequest(""));
		
		verify(presenter).output(new RegisterationUserResponse("Failed"));	
	}
	
	@Test
	public void testThatUserRegistrationPasses() throws Exception {
		interactor.handle(new RegisterUserRequest("Max Mustermann"));
		
		verify(presenter).output(new RegisterationUserResponse("Success"));
	}


}
