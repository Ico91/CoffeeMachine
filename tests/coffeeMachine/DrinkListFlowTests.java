package coffeeMachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Andrey
 * Tests DrinkListFlow class
 */

public class DrinkListFlowTests {
	DrinkListFlow drinkFlow;
	CoffeeMachineState coffeeMachine;

	//initCoffeeMachine, adds 3 drink and coins
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
		drinkFlow.execute(coffeeMachine);
		assertNotNull("Name of selected drink is NULL",drinkFlow.getDrink().getName());
		assertNotNull("Price of selected drink is NULL",drinkFlow.getDrink().getPrice());
	}
	
	@Test
	public void testSelectingCoffeeEquals() {
		drinkFlow.execute(coffeeMachine);
		assertEquals("Selected drink not equals to given drink",new Drink("Coffee", 30), drinkFlow.getDrink());
	}
	
	@Ignore
	@Test
	public void testCoffeeMachineDrinksAmount(){
		//assertNotNull("No drinks in machine", coffeeMachine.getDrinks().getDrinks());
		
	}
	
	

	@After
	public void testExecute_TearDownObject() {

	}
}