package coffeeMachine.main;

import java.util.Map;

public class DrinksContainer {
	private Map<Drink, Integer> drinks;

	public DrinksContainer(Map<Drink, Integer> drinks) {
		this.drinks = drinks;
	}

	

	public Map<Drink, Integer> getDrinks() {
		return drinks;
	}

	public void setDrinks(Map<Drink, Integer> drinks) {
		this.drinks = drinks;
	}
	
	
}
