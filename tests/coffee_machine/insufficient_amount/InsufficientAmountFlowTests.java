package coffee_machine.insufficient_amount;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.model.MoneyAmount;
import coffee_machine.model.Withdraw;

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
		coffeeMachine = new CoffeeMachineState(new MoneyAmount(), new DrinksContainer());
		Withdraw withdraw = new Withdraw(
				Withdraw.WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT,
				new MoneyAmount());

		flow = new InsufficientAmountFlow(drink, userCoins, withdraw);
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
