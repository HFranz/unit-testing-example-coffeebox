import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.cas.qs.example.coffe.Bill;
import de.cas.qs.example.coffe.CoffeRegister;

public class CoffeRegisterTest {
	
	private static final String USER_NAME = "John Doe";
	private CoffeRegister coffeRegister;
	
	@Before
	public void setup() {
		coffeRegister = new CoffeRegister();
	}

	@Test
	public void testCreationOfEmptyBill() throws Exception {
		Bill bill = coffeRegister.createBill();
		
		assertTrue(bill.isEmpty());
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
}
