package de.cas.qs.example.coffe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CoffeRegisterTest {
	
	private static final String USER_NAME = "John Doe";
	private CoffePriceList priceList;
	private CoffeRegister coffeRegister;
	
	@Before
	public void setup() {
		priceList = new CoffePriceList();
		coffeRegister = new CoffeRegister(priceList);
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
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		
		coffeRegister.debitCoffe(account, CoffeType.COFFE);
		
		Bill bill = coffeRegister.createBill(account);
		assertThat(bill.getSum(), equalTo(new BigDecimal("0.5")));
	}
	
	@Test
	public void testDebitOfMultipleCoffes() throws Exception {
		coffeRegister.createAccount(USER_NAME);
		Account account = coffeRegister.getAccountOfUser(USER_NAME);
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		
		coffeRegister.debitCoffe(account, CoffeType.COFFE);
		coffeRegister.debitCoffe(account, CoffeType.COFFE);
		
		Bill bill = coffeRegister.createBill(account);
		assertThat(bill.getSum(), equalTo(new BigDecimal("1.0")));
	}

	@Test
	public void testThatPriceChangeDoNotAffectExistingBills() throws Exception {
		coffeRegister.createAccount(USER_NAME);
		Account account = coffeRegister.getAccountOfUser(USER_NAME);
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		coffeRegister.debitCoffe(account,  CoffeType.COFFE);
		Bill existingBill = coffeRegister.createBill(account);
		assertThat(existingBill.getSum(), equalTo(new BigDecimal("0.5")));
		
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("3.0"));
		
		assertThat(existingBill.getSum(), equalTo(new BigDecimal("0.5")));
	}
}
