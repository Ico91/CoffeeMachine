package coffeeMachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;
import coffeeMachine.DrinkListFlow;
import coffeeMachine.InsufficientAmountFlow;
import coffeeMachine.MoneyAmount;
import coffeeMachine.OrderFinalizeFlow;
import coffeeMachine.Withdraw;

public class InsufficientAmountTests {

	private InsufficientAmountFlow flow;
	private CoffeeMachineState coffeeMachine;

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
		System.out.println("***** TEST - Continue making the drink"
				+ " and return only available change expected *****"
				+ System.lineSeparator());
		flow.execute(coffeeMachine);

		boolean isOrderFinalizeFlow = false;

		if (flow.getNextFlow() instanceof OrderFinalizeFlow)
			isOrderFinalizeFlow = true;

		assertTrue(isOrderFinalizeFlow);
	}

	@Test
	public void cancelOrder() {
		System.out.println("***** TEST - Cancel the order "
				+ "and go to correct flow *****" + "\n"
				+ System.lineSeparator());

		flow.execute(coffeeMachine);
		boolean isDrinkListFlow = false;

		if (flow.getNextFlow() instanceof DrinkListFlow)
			isDrinkListFlow = true;

		assertTrue(isDrinkListFlow);
	}

	@Test
	public void returnUserCoins() {
		System.out.println("\n***** TEST - Cancel the order "
				+ "and check returned money *****"
				+ System.lineSeparator());

		flow.execute(coffeeMachine);
		MoneyAmount expectedCoins = new MoneyAmount();

		assertEquals(
				"Returned coins should be the same as the user had inserted",
				expectedCoins.toString(), flow.getUserCoins().toString());
		System.out.println("\nExpected: " + expectedCoins.sumOfCoins());
	}

	@After
	public void tearDownObject() {
		flow = null;
	}
}
