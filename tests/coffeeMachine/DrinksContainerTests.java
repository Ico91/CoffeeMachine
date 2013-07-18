package coffeeMachine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.Drink;
import coffeeMachine.DrinksContainer;

public class DrinksContainerTests {

	private DrinksContainer drinks;

	@Before
	public void testDrinksContainer_SetUpObject() {
		drinks = new DrinksContainer();
		drinks.getDrinks().put(new Drink("Coffee", 45), 3);
		drinks.getDrinks().put(new Drink("Tea", 60), 2);
		drinks.getDrinks().put(new Drink("Hot Chocolate", 75), 1);
	}

	@Test
	public void testAddDrink_addToExistingDrink_expectCorrectQuantity() {
		Drink drink = new Drink("Coffee", 45);
		drinks.add(drink, 3);

		int expectedDrinkQuantity = 6;

		assertEquals("The quantity of the drink should be correct",
				expectedDrinkQuantity, drinks.getDrinks().get(drink).intValue());
	}

	@Test
	public void testAddDrink_addNewDrink_expectCorrectQuantity() {
		Drink drink = new Drink("Nescafe", 30);
		drinks.add(drink, 10);

		int expectedDrinkQuantity = 10;

		assertEquals("Expected amount is ten.", expectedDrinkQuantity, drinks
				.getDrinks().get(drink).intValue());
	}

	@Test(expected = IllegalStateException.class)
	public void testAddDrink_addNewDrinkAndCloseAddition_expectException() {
		Drink drink = new Drink("Nescafe", 30);
		drinks.add(drink, 10).commit();
		drinks.add(drink, 5);
	}

	@Test
	public void testDecreaseDrinkAmount_chooseAvailableDrink_expectLowerAmount() {
		Drink drink = new Drink("Coffee", 45);
		drinks.decreaseDrinkAmount(drink);

		int expectedDrinkAmount = 2;

		assertEquals("Expected and current drink amount should be equal",
				expectedDrinkAmount, drinks.getDrinks().get(drink).intValue());

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDecreaseDrinkAmount_chooseNotAvailableDrink_expectException()
	{
		Drink drink = new Drink("Nescafe", 30);
		drinks.decreaseDrinkAmount(drink);
	}

	@After
	public void testDrinksContainer_TearDownObject() {
		drinks = null;
	}
}
