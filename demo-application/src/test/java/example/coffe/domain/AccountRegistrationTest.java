package example.coffe.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import example.coffe.domain.AccountRegistration;

public class AccountRegistrationTest {
	
	private static final String USER_NAME = "John Doe";
	
	private AccountRegistration accountRegistration;
	
	@Before
	public void setup() {
		this.accountRegistration = new AccountRegistration();
	}
	
	@Test
	public void testCreationOfAccount() throws Exception {
		assertTrue(accountRegistration.createAccount(USER_NAME));
	}
	
	@Test
	public void testThatOnlyOneAccountCanBeCreatedForAUser() throws Exception {
		accountRegistration.createAccount(USER_NAME);
		assertFalse(accountRegistration.createAccount(USER_NAME));
	}
}
