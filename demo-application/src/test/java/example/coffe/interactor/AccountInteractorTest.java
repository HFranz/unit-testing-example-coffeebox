package example.coffe.interactor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import example.coffe.boundary.UserRegistrationResponseBoundary;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;
import example.coffe.entity.AccountRegistration;

@RunWith(MockitoJUnitRunner.class)
public class AccountInteractorTest {
	
	private AccountInteractor interactor;
	@Mock
	private UserRegistrationResponseBoundary presenter;
	@Mock
	private AccountRegistration accountRegistration;
	
	@Before
	public void setup() {
		interactor = new AccountInteractor(accountRegistration, presenter);
	}
	
	@Test
	public void testThatUserRegistrationFails() throws Exception {
		interactor.handle(new RegisterUserRequest(""));
		
		verify(presenter).present(new RegisterationUserResponse("Failed"));	
	}
	
	@Test
	public void testThatUserRegistrationPasses() throws Exception {
		when(accountRegistration.createAccount("Max Mustermann")).thenReturn(true);
		
		interactor.handle(new RegisterUserRequest("Max Mustermann"));
		
		verify(presenter).present(new RegisterationUserResponse("Success"));
	}


}
