package coffeeMachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DrinksContainerTests {

	private DrinksContainer drinks;

	@Before
	public void setUpObject() {
		drinks = new DrinksContainer();
		drinks.add(new Drink("Coffee", 45), 3).add(new Drink("Tea", 60), 2)
				.add(new Drink("Hot Chocolate", 75), 1);
	}

	@Test
	public void addToExistingDrink() {
		Drink drink = new Drink("Coffee", 45);
		drinks.add(drink, 3);
		int expectedDrinkQuantity = 6;

		assertEquals("The quantity of the drink should be correct",
				expectedDrinkQuantity, drinks.getDrinks().get(drink).intValue());
	}

	@Test
	public void addNewDrink() {
		Drink drink = new Drink("Nescafe", 30);
		drinks.add(drink, 10);

		int expectedDrinkQuantity = 10;

		assertEquals("Expected amount is ten.", expectedDrinkQuantity, drinks
				.getDrinks().get(drink).intValue());
	}

	@Test(expected = IllegalStateException.class)
	public void closeAdditionAndAddDrink() {
		Drink drink = new Drink("Nescafe", 30);
		drinks.add(drink, 10).commit();
		drinks.add(drink, 5);
	}

	@Test(expected = NullPointerException.class)
	public void addNullDrink() {
		Drink drink = new Drink("Nescafe", 10);
		drink = null;
		drinks.add(drink, 1);
	}

	@Test
	public void testCommit() {
		Drink drink = new Drink("Nescafe", 30);
		drinks.add(drink, 10).commit();

		assertTrue(drinks.isAdditionClosed());
	}

	@Test
	public void pickAvailableDrink() {
		Drink drink = new Drink("Coffee", 45);
		drinks.decreaseDrinkAmount(drink);

		int expectedDrinkAmount = 2;

		assertEquals("Expected and current drink amount should be equal",
				expectedDrinkAmount, drinks.getDrinks().get(drink).intValue());

	}

	@Test(expected = NullPointerException.class)
	public void decreaseNullDrinkAmount() {
		Drink drink = new Drink("Nescafe", 30);
		drink = null;
		drinks.decreaseDrinkAmount(drink);
	}

	@Test(expected = NullPointerException.class)
	public void getNullDrinkAmount() {
		Drink drink = new Drink("Nescafe", 30);
		drink = null;
		drinks.getDrinkQuantity(drink);
	}

	@Test
	public void getAvailableDrinkAmount() {
		Drink drink = new Drink("Coffee", 45);
		int expectedDrinkAmount = 3;

		assertEquals("Expected and actual drink amount should be equal",
				expectedDrinkAmount, drinks.getDrinkQuantity(drink));
	}

	@Test(expected = IllegalArgumentException.class)
	public void pickNotAvailableDrink() {
		Drink drink = new Drink("Nescafe", 30);
		drinks.decreaseDrinkAmount(drink);
	}

	@After
	public void tearDownObject() {
		drinks = null;
	}
}
