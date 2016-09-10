package de.cas.qs.example.coffe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class CoffeRegisterTest {
	private CoffePriceList priceList;
	private AccountRegistration accountRegistration;
	private CoffeRegister coffeRegister;
	private static final String USER_NAME = "John Doe";
	private Account USER_ACCOUNT = new Account(UUID.randomUUID(), USER_NAME);
	
	@Before
	public void setup() {
		accountRegistration = mock(AccountRegistration.class);
		when(accountRegistration.getRegisteredAccounts()).thenReturn(Lists.newArrayList(USER_ACCOUNT));
		priceList = new CoffePriceList();
		coffeRegister = new CoffeRegister(priceList, accountRegistration);
	}

	@Test
	public void testDebitOfCoffe() throws Exception {
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		
		coffeRegister.debitCoffe(USER_ACCOUNT, CoffeType.COFFE);
		
		CoffeBill bill = coffeRegister.createBill();
		assertThat(bill.getSum(USER_ACCOUNT), equalTo(new BigDecimal("0.5")));
	}
	
	@Test
	public void testDebitOfMultipleCoffes() throws Exception {
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		
		coffeRegister.debitCoffe(USER_ACCOUNT, CoffeType.COFFE);
		coffeRegister.debitCoffe(USER_ACCOUNT, CoffeType.COFFE);
		
		CoffeBill bill = coffeRegister.createBill();
		assertThat(bill.getSum(USER_ACCOUNT), equalTo(new BigDecimal("1.0")));
	}

	@Test
	public void testThatPriceChangeDoNotAffectExistingBills() throws Exception {
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("0.5"));
		coffeRegister.debitCoffe(USER_ACCOUNT,  CoffeType.COFFE);
		CoffeBill existingBill = coffeRegister.createBill();
		assertThat(existingBill.getSum(USER_ACCOUNT), equalTo(new BigDecimal("0.5")));
		
		priceList.definePrice(CoffeType.COFFE, new BigDecimal("3.0"));
		
		assertThat(existingBill.getSum(USER_ACCOUNT), equalTo(new BigDecimal("0.5")));
	}
}
