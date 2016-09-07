

public class KaffeekasseTest {

  @Test
  public void testAbrechnung() {
      Kaffeekasse kk = new Kaffeekasse();

      assertTrue(kk.erstelleAbrechnung() == 0);
  }
}
