package coffee_machine.reports;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.reports.DrinksReportFlow;
import coffee_machine.reports.transformers.dto.DrinksReportDTO;

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
	
	@Test (expected = NullPointerException.class)
	public void testCalculateOrderedDrinksWithNull()
	{
		DrinksContainer currentDrinks = null;
		DrinksContainer initialDrinks = null;
		
		report.calculateOrderedDrinks(currentDrinks, initialDrinks);
	}
	
	@Test (expected = NullPointerException.class)
	public void testSaveWithNull()
	{
		DrinksReportDTO drinksReport = null;
		report.save(drinksReport);
		
	}

	@After
	public void tearDown() {
		report = null;
	}
}
