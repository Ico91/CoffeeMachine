package coffeeMachine.flows;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;
import coffeeMachine.MoneyAmount;
import coffeeMachine.Withdraw;

public class InsufficientAmountFlowTests {

	private InsufficientAmountFlow flow;
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
		Drink drink = new Drink("Coffee", 45);
		MoneyAmount userCoins = new MoneyAmount();
		Withdraw withdraw = new Withdraw(
				Withdraw.WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT,
				new MoneyAmount());

		flow = new InsufficientAmountFlow(drink, userCoins, withdraw);
	}

	@Test
	public void continueOrderFlow() {
		String input = "1" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		flow.execute(coffeeMachine);

		boolean isOrderFinalizeFlow = false;

		if (flow.getNextFlow() instanceof OrderFinalizeFlow)
			isOrderFinalizeFlow = true;

		assertTrue(isOrderFinalizeFlow);
	}

	@Test
	public void cancelOrder() {
		String input = "2" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		flow.execute(coffeeMachine);
		boolean isDrinkListFlow = false;

		if (flow.getNextFlow() instanceof DrinkListFlow)
			isDrinkListFlow = true;

		assertTrue(isDrinkListFlow);
	}

	@Test
	public void returnUserCoins() {
		String input = "2" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		flow.execute(coffeeMachine);
		MoneyAmount expectedCoins = new MoneyAmount();

		assertEquals(
				"Returned coins should be the same as the user had inserted",
				expectedCoins.toString(), flow.getUserCoins().toString());
	}

	@After
	public void tearDownObject() {
		flow = null;
		System.setIn(in);
		System.setOut(out);
	}
}
