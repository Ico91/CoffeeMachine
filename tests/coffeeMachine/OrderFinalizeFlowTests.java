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

		MoneyAmount availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
		this.coffeeMachine = new CoffeeMachineState(availableCoins,
				drinksContainer);
	}

	@Test
	public void testRemoveChangeFromMachine() {
		MoneyAmount money = new MoneyAmount();
		money.add(Coin.FIVE, 2).add(Coin.TEN, 5).add(Coin.TWENTY, 0)
				.add(Coin.FIFTY, 0).add(Coin.LEV, 0);

		orderFinalizeFlow = new OrderFinalizeFlow(new Drink("Coffee", 30),
				money.withdraw(30));
		int expected = coffeeMachine.getCoins().sumOfCoins() - 30;

		orderFinalizeFlow.removeChangeFromMachine(coffeeMachine);

		int actual = coffeeMachine.getCoins().sumOfCoins();

		assertTrue(expected == actual);
	}

	@Test
	public void testFinalizeDrinkOrderMustReturnCorrectAmountOfDrinks() {

		MoneyAmount change = new MoneyAmount();
		change.add(Coin.FIVE, 2).add(Coin.TEN, 2).add(Coin.TWENTY, 0)
				.add(Coin.FIFTY, 0).add(Coin.LEV, 0);
		
		Withdraw withdraw = change.withdraw(30);
		Drink drink = new Drink("Coffee", 30);
		int expected = coffeeMachine.getCurrentDrinks().getDrinks().get(drink) - 1;

		orderFinalizeFlow = new OrderFinalizeFlow(drink, withdraw);
		orderFinalizeFlow.finalizeDrinkOrder(coffeeMachine);

		int actual = coffeeMachine.getCurrentDrinks().getDrinks().get(drink);

		assertTrue(expected == actual);
	}
}
