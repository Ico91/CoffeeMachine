package coffeeMachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;
import coffeeMachine.MoneyAmount;
import coffeeMachine.PaymentFlow;

public class PaymentFlowTests {

	private CoffeeMachineState coffeeMachine;
	private PaymentFlow payment;

	@Before
	public void setUpObject() {
		payment = new PaymentFlow(new Drink("Coffee", 45));
	}

	@Test
	public void insertEnoughMoney() {
		System.out.println("******************** TEST 2 *********************");
		System.out.println("Insert enough money to buy the drink: 30 stotinki");
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
		System.out.println("******************** TEST 1 *********************");
		System.out
				.println("Cancel the order before inserting enough money to" +
						"buy the drink (30 stotinki)");
		payment.execute(coffeeMachine);

		assertTrue(payment.isOrderCancelled());
	}

	@After
	public void tearDownObject() {
		payment = null;
	}

}
