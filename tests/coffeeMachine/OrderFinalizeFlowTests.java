package coffeeMachine;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;
import coffeeMachine.DrinksContainer;
import coffeeMachine.MoneyAmount;
import coffeeMachine.OrderFinalizeFlow;
import coffeeMachine.Withdraw;

public class OrderFinalizeFlowTests {
	private CoffeeMachineState coffeeMachine;
	private OrderFinalizeFlow orderFinalizeFlow;

	@Before
	public void setUpObject() {
		DrinksContainer drinksContainer = new DrinksContainer();
		drinksContainer.add(new Drink("Coffee", 30), 10)
				.add(new Drink("Tea", 40), 10)
				.add(new Drink("Hot chocolate", 50), 5).commit();

		MoneyAmount availableCoins = new MoneyAmount(5, 4, 3, 2, 1);
		this.coffeeMachine = new CoffeeMachineState(availableCoins,
				drinksContainer);
	}

	@Test
	public void testRemoveChangeFromMachine() {
		MoneyAmount money = new MoneyAmount(2, 5, 0, 0, 0);

		orderFinalizeFlow = new OrderFinalizeFlow(new Drink("Coffee", 30),
				money.withdraw(30));
		int expected = coffeeMachine.getCoins().getSumOfCoinsValue() - 30;

		orderFinalizeFlow.removeChangeFromMachine(coffeeMachine);

		int actual = coffeeMachine.getCoins().getSumOfCoinsValue();

		assertTrue(expected == actual);
	}

	@Test
	public void testFinalizeDrinkOrderMustReturnCorrectAmountOfDrinks() {

		MoneyAmount change = new MoneyAmount(2, 2, 0, 0, 0);
		Withdraw withdraw = change.withdraw(30);
		Drink drink = new Drink("Coffee", 30);
		int expected = coffeeMachine.getDrinks().getDrinks().get(drink) - 1;

		orderFinalizeFlow = new OrderFinalizeFlow(drink, withdraw);
		orderFinalizeFlow.finalizeDrinkOrder(coffeeMachine);

		int actual = coffeeMachine.getDrinks().getDrinks().get(drink);

		assertTrue(expected == actual);
	}
}
