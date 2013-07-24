package coffee_machine.finalize_order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coffee_machine.finalize_order.OrderFinalizeFlow;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Coin;
import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.model.MoneyAmount;
import coffee_machine.model.Withdraw;
import coffee_machine.model.Withdraw.WithdrawRequestResultStatus;

public class OrderFinalizeFlowTests {
	private CoffeeMachineState coffeeMachine;
	private OrderFinalizeFlow orderFinalizeFlow;
	private static InputStream in;
	private static PrintStream out;
	
	@BeforeClass
	public static void SetUpClass() {
		in = System.in;
		out = System.out;
	}
	
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
	public void executeWithSuccessfulChange() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Withdraw withdraw = new Withdraw(
				WithdrawRequestResultStatus.SUCCESSFUL, new MoneyAmount().add(
						Coin.TEN, 30));
		String expected = "Your Coffee is ready." + System.lineSeparator()
				+ "Your change is: ";
		expected += withdraw.getChange().toString() + System.lineSeparator();

		orderFinalizeFlow = new OrderFinalizeFlow(new Drink("Coffee", 30),
				withdraw);
		orderFinalizeFlow.execute(coffeeMachine);

		assertEquals(expected, output.toString());
	}

	@Test
	public void executeWithInsufficienAmount() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Withdraw withdraw = new Withdraw(
				WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT,
				new MoneyAmount().add(Coin.FIFTY, 1));
		orderFinalizeFlow = new OrderFinalizeFlow(new Drink("Coffee", 30),
				withdraw);
		String expected = "Your Coffee is ready." + System.lineSeparator()
				+ "You receive: " + withdraw.getChange().toString()
				+ " as change." + System.lineSeparator();

		orderFinalizeFlow.execute(coffeeMachine);
		assertEquals(expected, output.toString());
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
	
	@After
	public void tearDownObject() {
		System.setIn(in);
		System.setOut(out);
	}
}
