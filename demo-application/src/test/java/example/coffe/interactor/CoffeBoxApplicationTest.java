package example.coffe.interactor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import example.coffe.boundary.AccountGateway;
import example.coffe.boundary.CoffePresenter;
import example.coffe.boundary.request.BuyCoffeRequest;
import example.coffe.boundary.response.BuyCoffeResponse;
import example.coffe.entity.Account;
import example.coffe.entity.CoffeRegister;
import example.coffe.entity.CoffeType;

@RunWith(MockitoJUnitRunner.class)
public class CoffeBoxApplicationTest {

	private static final String NAME = "Mister Foo";
	@Mock
	private CoffeRegister coffeRegister;
	@Mock
	private AccountGateway accountRegistration;
	@Mock
	private CoffePresenter coffeBoxOutputBoundary;
	
	private CoffeInteractor coffeBoxApp;
	
	@Before
	public void setup() {
		coffeBoxApp = new CoffeInteractor(coffeRegister, accountRegistration, coffeBoxOutputBoundary);
	}
	
	@Test
	public void testThatCoffeIsOrderPasses() throws Exception {
		Account account = new Account(UUID.randomUUID(), NAME);
		when(accountRegistration.getAccountOfUser(NAME)).thenReturn(account);
		
		coffeBoxApp.handle(new BuyCoffeRequest(CoffeType.COFFE, NAME));
		
		verify(coffeRegister).debitCoffe(account, CoffeType.COFFE);
		verify(coffeBoxOutputBoundary).present(new BuyCoffeResponse("Success"));
	}
}
