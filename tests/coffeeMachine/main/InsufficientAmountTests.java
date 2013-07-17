package coffeeMachine.main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InsufficientAmountTests {

	private InsufficientAmountFlow flow;
	private CoffeeMachineState coffeeMachine;

	@Before
	public void testExecute_SetUpObject() {
		Drink drink = new Drink("Coffee", 45);
		MoneyAmount userCoins = new MoneyAmount();
		Withdraw withdraw = new Withdraw(
				RequestResultStatus.INSUFFICIENT_AMOUNT, new MoneyAmount());

		flow = new InsufficientAmountFlow(drink, userCoins, withdraw);
	}

	@Test
	public void testExecute_continueMakingDrinkAndDontReturnChange() {
		System.out.println("***** TEST 2 *****"
				+ "\nExpect continuing the order" + System.lineSeparator());
		flow.execute(coffeeMachine);

		boolean isOrderFinalizeFlow = false;

		if (flow.getNextFlow() instanceof OrderFinalizeFlow)
			isOrderFinalizeFlow = true;

		assertTrue(isOrderFinalizeFlow);
	}
	
	@Test
	public void testExecute_cancelOrderAndReturnUserCoins() {
		System.out.println("***** TEST 1 *****"
				+ "\nExpect cancelling the order" + System.lineSeparator());

		flow.execute(coffeeMachine);
		boolean isDrinkListFlow = false;

		if (flow.getNextFlow() instanceof DrinkListFlow)
			isDrinkListFlow = true;

		assertTrue(isDrinkListFlow);
	}

	@Test
	public void testExecute_cancelOrder_expectReturnUserCoins() {
		System.out.println("\n***** TEST Cancel order*****");

		flow.execute(coffeeMachine);
		MoneyAmount expectedCoins = new MoneyAmount();

		assertEquals(
				"Returned coins should be the same as the user had inserted",
				expectedCoins.toString(), flow.getUserCoins().toString());
		System.out.println("\nExpected: " + expectedCoins.getSumOfCoinsValue());
	}

	@After
	public void testExecute_TearDownObject() {
		flow = null;
	}
}
