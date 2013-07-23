package coffeeMachine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.flows.DrinksReportFlow;

public class DrinksReportFlowTests {

	private DrinksReportFlow report;

	@Before
	public void setUp() {
		report = new DrinksReportFlow();
	}

	@Test
	public void testCalculateOrderedDrinks() {
		DrinksContainer currentDrinks = new DrinksContainer();
		currentDrinks.add(new Drink("Coffee", 45), 3)
				.add(new Drink("Tea", 60), 7)
				.add(new Drink("Hot Chocolate", 75), 5).commit();
		DrinksContainer initialDrinks = new DrinksContainer();
		initialDrinks.add(new Drink("Coffee", 45), 10)
				.add(new Drink("Tea", 60), 10)
				.add(new Drink("Hot Chocolate", 75), 10).commit();
		DrinksContainer orderedDrinks = report.calculateOrderedDrinks(
				currentDrinks, initialDrinks);

		DrinksContainer expectedOrderedDrinks = new DrinksContainer();
		expectedOrderedDrinks.add(new Drink("Coffee", 45), 7)
				.add(new Drink("Tea", 60), 3)
				.add(new Drink("Hot Chocolate", 75), 5).commit();

		assertEquals("Expected ordered drinks should be equal to calculated",
				expectedOrderedDrinks, orderedDrinks);
	}

	@After
	public void tearDown() {
		report = null;
	}
}
