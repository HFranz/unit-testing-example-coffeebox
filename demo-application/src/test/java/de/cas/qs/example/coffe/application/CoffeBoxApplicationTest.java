package de.cas.qs.example.coffe.application;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.cas.qs.example.coffe.domain.Account;
import de.cas.qs.example.coffe.domain.AccountRegistration;
import de.cas.qs.example.coffe.domain.CoffeRegister;
import de.cas.qs.example.coffe.domain.CoffeType;

@RunWith(MockitoJUnitRunner.class)
public class CoffeBoxApplicationTest {

	private static final String NAME = "Mister Foo";
	@Mock
	private CoffeRegister coffeRegister;
	@Mock
	private AccountRegistration accountRegistration;
	@Mock
	private CoffeBoxOutputBoundary coffeBoxOutputBoundary;
	
	private CoffeBoxApplication coffeBoxApp;
	
	@Before
	public void setup() {
		coffeBoxApp = new CoffeBoxApplication(coffeRegister, accountRegistration, coffeBoxOutputBoundary);
	}
	
	@Test
	public void testThatUserRegistrationFails() throws Exception {
		coffeBoxApp.registerUser(new CreateUserRequest(""));
		
		verify(coffeBoxOutputBoundary).showRegistrationResponse(new UserRegisterationResponse("Failed"));	
	}
	
	@Test
	public void testThatUserRegistrationPasses() throws Exception {
		when(accountRegistration.createAccount("Max Mustermann")).thenReturn(true);
		
		coffeBoxApp.registerUser(new CreateUserRequest("Max Mustermann"));
		
		verify(coffeBoxOutputBoundary).showRegistrationResponse(new UserRegisterationResponse("Success"));
	}

	@Test
	public void testThatCoffeIsOrderPasses() throws Exception {
		Account account = new Account(UUID.randomUUID(), NAME);
		when(accountRegistration.getAccountOfUser(NAME)).thenReturn(account);
		
		coffeBoxApp.buyCoffe(new BuyCoffeRequest(CoffeType.COFFE, NAME));
		
		verify(coffeRegister).debitCoffe(account, CoffeType.COFFE);
		verify(coffeBoxOutputBoundary).showBuyCoffeResponse(new BuyCoffeResponse("Success"));
	}
}
