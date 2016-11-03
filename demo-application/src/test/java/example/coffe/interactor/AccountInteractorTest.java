package example.coffe.interactor;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import example.coffe.boundary.EntityGateway;
import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.request.RegisterUserRequest;
import example.coffe.boundary.response.RegisterationUserResponse;
import example.coffe.entity.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountInteractorTest {
	
	private static final Account ACCOUNT = new Account(UUID.randomUUID(), "Max Mustermann");
	private static final RegisterationUserResponse FAILED_RESPONSE = new RegisterationUserResponse(false);
	private static final RegisterationUserResponse SUCCESSFUL_RESPONSE = new RegisterationUserResponse(true, ACCOUNT);
	
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
		when(accountRegistration.findAll()).thenReturn(Stream.empty());
		
		interactor.handle(new RegisterUserRequest(null));
		
		verify(presenter).output(FAILED_RESPONSE);	
	}
	
	@Test
	public void testThatNewAccountIsCreated() throws Exception {
		when(accountRegistration.findAll()).thenReturn(Stream.empty());
		when(accountRegistration.getNewId()).thenReturn(ACCOUNT.getId());
		
		interactor.handle(new RegisterUserRequest(ACCOUNT.getName()));
		
		verify(accountRegistration).store(Mockito.eq(ACCOUNT));
		verify(presenter).output(Mockito.eq(SUCCESSFUL_RESPONSE));
	}
	
	@Test
	public void testThatNoAccountIsCreatedForAlreadyExisting() throws Exception {
		when(accountRegistration.findAll()).thenReturn(Lists.newArrayList(ACCOUNT).stream());
		when(accountRegistration.findById(ACCOUNT.getId())).thenReturn(Optional.of(ACCOUNT));
		
		interactor.handle(new RegisterUserRequest(ACCOUNT.getName()));
		
		verify(accountRegistration, never()).store(Mockito.any(Account.class));
		verify(presenter).output(Mockito.eq(FAILED_RESPONSE));
	}


}
