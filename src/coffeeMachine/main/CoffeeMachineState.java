package coffeeMachine.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CoffeeMachineState {
	private MoneyAmount coins;
	private DrinksContainer drinks;
	
	public CoffeeMachineState(MoneyAmount coins, DrinksContainer drinks) {
		this.coins = coins;
		this.drinks = drinks;
	}

	public MoneyAmount getCoins() {
		return coins;
	}

	public void setCoins(MoneyAmount coins) {
		this.coins = coins;
	}

	public DrinksContainer getDrinks() {
		return drinks;
	}

	public void setDrinks(DrinksContainer drinks) {
		this.drinks = drinks;
	}
	
	public List<Drink> getFiltratedDrinks(){  // by Andrey
		List<Drink> drinksList=new ArrayList<Drink>();
		for(Map.Entry<Drink, Integer> entry: drinks.getDrinks().entrySet()){
			if(entry.getValue()!=0){
				drinksList.add(entry.getKey());
			}
		
		}
		return drinksList;
	}
	
}
