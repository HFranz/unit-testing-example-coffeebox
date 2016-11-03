package example.coffe.interactor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import example.coffe.boundary.EntityGateway;
import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.request.BuyCoffeRequest;
import example.coffe.boundary.response.BuyCoffeResponse;
import example.coffe.entity.Account;
import example.coffe.entity.CoffeRegister;
import example.coffe.entity.CoffeType;

@RunWith(MockitoJUnitRunner.class)
public class CoffeInteractorTest {

	private static final Account ACCOUNT = new Account(UUID.randomUUID(), "Mister X");
	@Mock
	private CoffeRegister coffeRegister;
	@Mock
	private EntityGateway<Account> accountGateway;
	@Mock
	private OutputBoundary<BuyCoffeResponse> buyCoffe;
	
	private CoffeInteractor coffeBoxApp;
	
	@Before
	public void setup() {
		coffeBoxApp = new CoffeInteractor(coffeRegister, accountGateway, buyCoffe);
	}
	
	@Test
	public void testThatCoffeIsOrderPasses() throws Exception {
		when(accountGateway.findById(ACCOUNT.getId())).thenReturn(Optional.of(ACCOUNT));
		
		coffeBoxApp.handle(new BuyCoffeRequest(CoffeType.COFFE, ACCOUNT.getId()));
		
		verify(coffeRegister).debitCoffe(ACCOUNT, CoffeType.COFFE);
		verify(buyCoffe).output(new BuyCoffeResponse(true));
	}

	@Test
	public void testThatCoffeRequestFailsForNotExistingAccount() throws Exception {
		when(accountGateway.findById(ACCOUNT.getId())).thenReturn(Optional.empty());
		
		coffeBoxApp.handle(new BuyCoffeRequest(CoffeType.ESPRESSO, ACCOUNT.getId()));
		
		verify(buyCoffe).output(new BuyCoffeResponse(false));
	}
}
