package coffeeMachine.main;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class OrderFinalizeFlowTests {
	private CoffeeMachineState coffeeMachine;
	private OrderFinalizeFlow orderFinalizeFlow;

	@Before
	public void testMoneyAmount_SetUpObject() {
		Drink drink = new Drink("Coffee", 30);
		Map<Coin, Integer> coins = new TreeMap<Coin, Integer>();
		coins.put(Coin.FIVE, 5);
		coins.put(Coin.TEN, 4);
		coins.put(Coin.TWENTY, 3);
		coins.put(Coin.FIFTY, 2);
		coins.put(Coin.LEV, 1);

		Map<Drink, Integer> availableDrinks = new TreeMap<Drink, Integer>();
		availableDrinks.put(drink, 50);
		availableDrinks.put(new Drink("Tea", 40), 10);
		availableDrinks.put(new Drink("Hot chocolate", 50), 5);

		MoneyAmount availableCoins = new MoneyAmount(coins);
		this.coffeeMachine = new CoffeeMachineState(availableCoins,
				new DrinksContainer(availableDrinks));
	}

	@Test
	public void testFinalizeDrinkOrderMustReturnCorrectAmountOfDrinks() {
		Map<Coin, Integer> coins = new TreeMap<Coin, Integer>();
		coins.put(Coin.FIVE, 2);
		coins.put(Coin.TEN, 2);
		coins.put(Coin.TWENTY, 0);
		coins.put(Coin.FIFTY, 0);
		coins.put(Coin.LEV, 0);

		MoneyAmount change = new MoneyAmount(coins);
		Withdraw withdraw = change.withdraw(30);
		Drink drink = new Drink("Coffee", 30);

		orderFinalizeFlow = new OrderFinalizeFlow(drink, withdraw);
		orderFinalizeFlow.finalizeDrinkOrder(coffeeMachine);
		int expected = 49;
		int actual = coffeeMachine.getDrinks().getDrinks().get(drink);

		assertTrue(expected == actual);
	}
}
