package coffeeMachine;

import static org.junit.Assert.*;

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
import coffeeMachine.PaymentFlow;

public class PaymentFlowTests {

	private CoffeeMachineState coffeeMachine;
	private PaymentFlow payment;
	private static InputStream in;
	private static PrintStream out;

	@BeforeClass
	public static void setUpClass() {
		in = System.in;
		out = System.out;
	}

	@Before
	public void setUpObject() {
		payment = new PaymentFlow(new Drink("Coffee", 45));
	}

	@Test
	public void insertEnoughMoney() {
		String input = "1" + System.lineSeparator() + "2"
				+ System.lineSeparator() + "2" + System.lineSeparator() + "2"
				+ System.lineSeparator() + "2" + System.lineSeparator() + "2"
				+ System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		payment.execute(coffeeMachine);

		int drinkPrice = payment.getDrink().getPrice();
		MoneyAmount userCoins = payment.getUserCoins();

		boolean isMoneyEnough = false;
		if (drinkPrice <= userCoins.sumOfCoins())
			isMoneyEnough = true;

		assertTrue(isMoneyEnough);
	}

	@Test
	public void cancelOrder() {
		String input = "1" + System.lineSeparator() + "1"
				+ System.lineSeparator() + "2" + System.lineSeparator() + "6"
				+ System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		payment.execute(coffeeMachine);

		assertTrue(payment.isOrderCancelled());
	}

	@After
	public void tearDownClass() {
		System.setOut(out);
		System.setIn(in);
	}
}
