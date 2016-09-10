package de.cas.qs.example.coffe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class CoffeRegisterTest {
	private CoffePriceList priceList;
	private AccountRegistration accountRegistration;
	private CoffeRegister coffeRegister;
	private static final String USER_NAME = "John Doe";
	private Account USER_ACCOUNT = new Account(UUID.randomUUID(), USER_NAME);
	
	@Before
	public void setup() {
		accountRegistration = mock(AccountRegistration.class);
		when(accountRegistration.getAccountOfUser(USER_NAME)).thenReturn(USER_ACCOUNT);
		priceList = new CoffePriceList();
		coffeRegister = new CoffeRegister(priceList, accountRegistration);
	}

	@Test
	public void testDebitOfCoffe() throws Exception {
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		
		coffeRegister.debitCoffe(USER_ACCOUNT, CoffeType.COFFE);
		
		Bill bill = coffeRegister.createBill(USER_ACCOUNT);
		assertThat(bill.getSum(), equalTo(new BigDecimal("0.5")));
	}
	
	@Test
	public void testDebitOfMultipleCoffes() throws Exception {
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		
		coffeRegister.debitCoffe(USER_ACCOUNT, CoffeType.COFFE);
		coffeRegister.debitCoffe(USER_ACCOUNT, CoffeType.COFFE);
		
		Bill bill = coffeRegister.createBill(USER_ACCOUNT);
		assertThat(bill.getSum(), equalTo(new BigDecimal("1.0")));
	}

	@Test
	public void testThatPriceChangeDoNotAffectExistingBills() throws Exception {
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		coffeRegister.debitCoffe(USER_ACCOUNT,  CoffeType.COFFE);
		Bill existingBill = coffeRegister.createBill(USER_ACCOUNT);
		assertThat(existingBill.getSum(), equalTo(new BigDecimal("0.5")));
		
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("3.0"));
		
		assertThat(existingBill.getSum(), equalTo(new BigDecimal("0.5")));
	}
}
