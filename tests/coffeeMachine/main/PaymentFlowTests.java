package coffeeMachine.main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaymentFlowTests {

	private CoffeeMachineState coffeeMachine;
	private PaymentFlow payment;

	@Before
	public void testExecute_SetUpObject() {
		payment = new PaymentFlow(new Drink("Coffee", 45));
	}

	@Test
	public void testExecute_InsertEnoughMoney_CheckCorrectResult() {
		System.out.println("******************** TEST 2 *********************");
		System.out.println("Insert enough money to buy the drink: 30 stotinki");
		payment.execute(coffeeMachine);

		int drinkPrice = payment.getDrink().getPrice();
		MoneyAmount userCoins = payment.getUserCoins();

		boolean isMoneyEnough = false;
		if (drinkPrice <= userCoins.getSumOfCoinsValue())
			isMoneyEnough = true;

		assertTrue(isMoneyEnough);
	}

	@Test
	public void testExecute_CancelOrder() {
		System.out.println("******************** TEST 1 *********************");
		System.out
				.println("Cancel the order before inserting enough money to" +
						"buy the drink (30 stotinki)");
		payment.execute(coffeeMachine);

		assertTrue(payment.isOrderCancelled());
	}

	@After
	public void testExecute_TearDownObject() {
		payment = null;
	}

}
