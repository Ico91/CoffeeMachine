package coffeeMachine.main;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.main.DrinksContainer;


public class DrinkListFlowTests {
	DrinkListFlow drinkFlow;
	CoffeeMachineState coffeeMachine;
	
	@Before
	public void testExecute_SetUpObject()
	{
		 drinkFlow=new DrinkListFlow();
		Map<Coin,Integer> coinMap=new TreeMap();
		coinMap.put(Coin.FIVE, 10);
		coinMap.put(Coin.TEN, 10);
		coinMap.put(Coin.TWENTY, 10);
		coinMap.put(Coin.FIFTY, 10);
		coinMap.put(Coin.LEV, 10);
		MoneyAmount moneyAmount=new MoneyAmount(coinMap);
		
		Map<Drink,Integer> drinkMap=new TreeMap();
		drinkMap.put(new Drink("Coffee",30), 10);
		drinkMap.put(new Drink("Choko",50), 10);
		drinkMap.put(new Drink("MilkChoko",60), 10);
		DrinksContainer drinksContainer=new DrinksContainer(drinkMap);
		coffeeMachine=new CoffeeMachineState(moneyAmount ,drinksContainer);
		
	}
	
	@Test
	public void test() {
		drinkFlow.execute(coffeeMachine);
		System.out.println(drinkFlow.getDrink().toString());
		assertEquals("Select 1 drink",new Drink("Coffee",30).toString(),drinkFlow.getDrink().toString());
	}

	@After
	public void testExecute_TearDownObject()
	{
		
	}
}