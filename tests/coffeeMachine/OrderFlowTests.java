package coffeeMachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderFlowTests {

	private OrderFlow orderFlow;
	private CoffeeMachineState coffeeMachine;
	
	@Before
	public void testOrderFlow_setUpObject() {
		this.coffeeMachine = new CoffeeMachineState(new MoneyAmount(), new DrinksContainer());
	}

	@Test
	public void testExecute_enoughCoinsToReturn() {
		Drink drink = new Drink("Coffee", 65);
		MoneyAmount userCoins = new MoneyAmount(1, 1, 1, 1, 1);
		orderFlow = new OrderFlow(drink, userCoins);

		orderFlow.execute(coffeeMachine);
		
		Withdraw withdrawResult = orderFlow.getWithdraw();
		Withdraw expectedWithdraw = new Withdraw(
				RequestResultStatus.SUCCESSFUL, new MoneyAmount(0, 0, 1, 0, 1));
		boolean isEqual = false;

		if (withdrawResult.equals(expectedWithdraw))
			isEqual = true;

		assertTrue(isEqual);
	}

	@Test
	public void testExecute_notEnoughCoinsToReturn() {
		Drink drink = new Drink("Coffee", 45);
		MoneyAmount userCoins = new MoneyAmount(1, 1, 1, 1, 1);
		orderFlow = new OrderFlow(drink, userCoins);

		orderFlow.execute(coffeeMachine);
		
		Withdraw withdrawResult = orderFlow.getWithdraw();
		Withdraw expectedWithdraw = new Withdraw(
				RequestResultStatus.INSUFFICIENT_AMOUNT, new MoneyAmount(1, 1, 1, 0, 1));
		System.out.println("Expected " + expectedWithdraw.toString());
		boolean isEqual = false;

		if (withdrawResult.equals(expectedWithdraw))
			isEqual = true;

		assertTrue(isEqual);
	}

	@After
	public void testOrderFlow_tearDown() {
		orderFlow = null;
	}

}
