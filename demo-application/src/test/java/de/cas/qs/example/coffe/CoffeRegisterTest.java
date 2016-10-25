package de.cas.qs.example.coffe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CoffeRegisterTest {
	
	private static final String USER_NAME = "John Doe";
	private CoffeRegister coffeRegister;
	
	@Before
	public void setup() {
		coffeRegister = new CoffeRegister();
	}

	@Test
	public void testCreationOfAccount() throws Exception {
		assertTrue(coffeRegister.createAccount(USER_NAME));
	}
	
	@Test
	public void testThatOnlyOneAccountCanBeCreatedForAUser() throws Exception {
		coffeRegister.createAccount(USER_NAME);
		assertFalse(coffeRegister.createAccount(USER_NAME));
	}
	
	@Test
	public void testDebitOfCoffe() throws Exception {
		coffeRegister.createAccount(USER_NAME);
		Account account = coffeRegister.getAccountOfUser(USER_NAME);
		
		coffeRegister.debitCoffe(account, CoffeType.COFFE);
		
		Bill bill = coffeRegister.createBill(account);
		assertThat(bill.getSum(), equalTo(new BigDecimal("0.5")));
	}
}
