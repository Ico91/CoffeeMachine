package coffeeMachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DrinkListFlowTests {
	DrinkListFlow drinkFlow;
	CoffeeMachineState coffeeMachine;

	@Before
	public void testExecute_SetUpObject() {
		drinkFlow = new DrinkListFlow();
		MoneyAmount moneyAmount = new MoneyAmount(10, 10, 10, 10, 10);
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
	
	@Test
	public void testCoffeeMachineDrinksAmount(){
		assertNotNull("No drinks in machine", coffeeMachine.getDrinks().getDrinks());
		
	}
	
	

	@After
	public void testExecute_TearDownObject() {

	}
}