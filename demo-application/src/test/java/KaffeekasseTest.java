import static org.junit.Assert.*;

import org.junit.Test;

public class KaffeekasseTest {

	@Test
	public void testAbrechnung() {
		Kaffeekasse kk = new Kaffeekasse();

		assertTrue(kk.erstelleAbrechnung() == 0);
	}

	@Test
	public void failingTest() throws Exception {
		assertTrue(false);
	}
}
