package coffeeMachine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.dto.reports.DrinksReportDTO;
import coffeeMachine.transformers.toDto.OrderedDrinksContainerToDTO;

public class OrderedDrinksContainerToDTOTests {

	private OrderedDrinksContainerToDTO transformer;
	
	@Before
	public void setUp()
	{
		transformer = new OrderedDrinksContainerToDTO();
	}
	
	@Test
	public void testTransform() {
		DrinksReportDTO expectedReport = new DrinksReportDTO();
		DrinksReportDTO.OrderedDrinks reportOrderedDrinks = new DrinksReportDTO.OrderedDrinks();
		
		DrinksReportDTO.OrderedDrinks.Drink drink = new DrinksReportDTO.OrderedDrinks.Drink();
		drink.setAmount(7);
		drink.setName("Coffee");
		reportOrderedDrinks.getDrink().add(drink);
		drink = new DrinksReportDTO.OrderedDrinks.Drink();
		drink.setAmount(5);
		drink.setName("Hot Chocolate");
		reportOrderedDrinks.getDrink().add(drink);
		drink = new DrinksReportDTO.OrderedDrinks.Drink();
		drink.setAmount(3);
		drink.setName("Tea");
		reportOrderedDrinks.getDrink().add(drink);
		
		
		expectedReport.setOrderedDrinks(reportOrderedDrinks);
		expectedReport.setTotal(15);
		
		DrinksContainer orderedDrinks = new DrinksContainer();
		orderedDrinks.add(new Drink("Coffee", 45), 7)
				.add(new Drink("Tea", 60), 3)
				.add(new Drink("Hot Chocolate", 75), 5).commit();
		DrinksReportDTO drinksReport = transformer.transform(orderedDrinks);
		
		assertEquals("Expected DrinkReportDTO should be equal to the generated", expectedReport, drinksReport);
		
	}

	@After
	public void tearDown()
	{
		
	}
}
