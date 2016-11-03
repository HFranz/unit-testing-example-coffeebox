package example.coffe.entity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import example.coffe.boundary.EntityGateway;

@RunWith(MockitoJUnitRunner.class)
public class CoffeRegisterTest {

	private CoffeRegister coffeRegister;
	@Mock
	private CoffePriceList priceList;
	@Mock
	private EntityGateway<Account> accountRegistration;
	
	private Account ACCOUNT1 = new Account(UUID.randomUUID(), "John Doe");
	private Account ACCOUNT2 = new Account(UUID.randomUUID(), "Max Muster");

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		coffeRegister = new CoffeRegister(priceList, accountRegistration);
		List<Account> accounts = Arrays.asList(ACCOUNT1, ACCOUNT2);
		when(accountRegistration.findAll()).thenReturn(accounts.stream(), accounts.stream());
		when(priceList.getPrice(CoffeType.COFFE)).thenReturn(new BigDecimal("0.5"));
	}

	@Test
	public void testDebitOfCoffe() throws Exception {
		coffeRegister.debitCoffe(ACCOUNT1, CoffeType.COFFE);

		CoffeBill bill = coffeRegister.createBill();
		assertThat(bill.getSum(ACCOUNT1), is(equalTo(new BigDecimal("0.5"))));
	}

	@Test
	public void testDebitOfMultipleCoffes() throws Exception {
		coffeRegister.debitCoffe(ACCOUNT1, CoffeType.COFFE);
		coffeRegister.debitCoffe(ACCOUNT1, CoffeType.COFFE);

		CoffeBill bill = coffeRegister.createBill();
		assertThat(bill.getSum(ACCOUNT1), is(equalTo(new BigDecimal("1.0"))));
	}

	@Test
	public void testThatDebitsAreClearedAfterBillCreation() throws Exception {
		coffeRegister.debitCoffe(ACCOUNT1, CoffeType.COFFE);

		coffeRegister.createBill();

		CoffeBill bill = coffeRegister.createBill();
		assertThat(bill.getSum(ACCOUNT1), is(equalTo(BigDecimal.ZERO)));
	}

	@Test
	public void testThatSumRemainsZeroIfNoCoffesAreDebited() throws Exception {
		CoffeBill bill = coffeRegister.createBill();

		assertThat(bill.getSum(ACCOUNT1), is(equalTo(BigDecimal.ZERO)));
	}
}
