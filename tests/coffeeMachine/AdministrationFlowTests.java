package coffeeMachine;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coffeeMachine.flows.AdministrationFlow;
import coffeeMachine.flows.DrinkListFlow;
import coffeeMachine.flows.DrinksReportFlow;
import coffeeMachine.flows.Flow;

public class AdministrationFlowTests {
	private Flow administrationFlow;
	private CoffeeMachineState coffeeMachine;
	private static InputStream in;
	private static PrintStream out;

	@BeforeClass
	public static void setUpClass() {
		in = System.in;
		out = System.out;
	}

	@Before
	public void setUpObject() {

		DrinksContainer drinksContainer = new DrinksContainer();
		drinksContainer.add(new Drink("Coffee", 30), 10)
				.add(new Drink("Tea", 40), 10)
				.add(new Drink("Hot chocolate", 50), 5).commit();

		MoneyAmount availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
		this.coffeeMachine = new CoffeeMachineState(availableCoins,
				drinksContainer);

		this.administrationFlow = new AdministrationFlow();
	}

	@Test
	public void testReportSelection() {
		String input = "1" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		assertTrue((this.administrationFlow.execute(coffeeMachine)).getClass() == DrinksReportFlow.class);
	}

	@Test
	public void testExit() {
		String input = "2" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		assertTrue((this.administrationFlow.execute(coffeeMachine)).getClass() == DrinkListFlow.class);
	}

	@After
	public void tearDownObject() {
		System.setIn(in);
		System.setOut(out);
	}

}
