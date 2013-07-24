package coffeeMachine.flows;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import coffeeMachine.DrinksContainer;
import coffeeMachine.MoneyAmount;
import coffeeMachine.flows.DrinkListFlow;

/**
 * @author Andrey Tests DrinkListFlow class
 */

public class DrinkListFlowTests {
	DrinkListFlow drinkFlow;
	CoffeeMachineState coffeeMachine;
	private static InputStream in;
	private static PrintStream out;

	@BeforeClass
	public static void setUpClass() {
		in = System.in;
		out = System.out;
	}

	// initCoffeeMachine, adds 3 drink and coins
	@Before
	public void initCoffeeMachine() {
		drinkFlow = new DrinkListFlow();
		MoneyAmount moneyAmount = new MoneyAmount();
		DrinksContainer drinksContainer = new DrinksContainer();
		drinksContainer.add(new Drink("Coffee", 30), 10)
				.add(new Drink("Choko", 50), 10)
				.add(new Drink("MilkChoko", 60), 10).commit();
		coffeeMachine = new CoffeeMachineState(moneyAmount, drinksContainer);
	}

	@Test
	public void testSelectingCoffee() {
		String input = "1" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		drinkFlow.execute(coffeeMachine);
		assertNotNull("Name of selected drink is NULL", drinkFlow.getDrink()
				.getName());
		assertNotNull("Price of selected drink is NULL", drinkFlow.getDrink()
				.getPrice());
	}

	@Test
	public void testSelectingCoffeeEquals() {
		String input = "1" + System.lineSeparator();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		drinkFlow.execute(coffeeMachine);
		assertEquals("Selected drink not equals to given drink", new Drink(
				"Coffee", 30), drinkFlow.getDrink());
	}

	@After
	public void tearDownClass() {
		System.setOut(out);
		System.setIn(in);
	}
}