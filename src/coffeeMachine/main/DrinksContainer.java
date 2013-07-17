package coffeeMachine.main;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class DrinksContainer {
	private Map<Drink, Integer> drinks;
	private boolean additionClosed = false;
	
	public DrinksContainer()
	{
		drinks = new TreeMap<Drink, Integer>();
	}
	
	public DrinksContainer add(Drink drink, int quantity)
	{
		if(additionClosed)
			throw new IllegalStateException("Cannot add any more drinks to collection!");
		Objects.requireNonNull(drink, "Specified drink is null");
		Integer drinkQuantity = drinks.get(drink);
		
		if(drinkQuantity == null)
		{
			int currentQuantity = 0;
			drinks.put(drink, Integer.valueOf(quantity + currentQuantity));
		}
		else
			drinks.put(drink, Integer.valueOf(quantity) + drinkQuantity);
		return this;
	}
	
	public DrinksContainer commit()
	{
		additionClosed = true;
		return this;
	}
	
	public void decreaseDrinkAmount(Drink drink)
	{
		Integer drinkQuantity = drinks.get(drink);
		
		if(drinkQuantity == null)
			throw new IllegalArgumentException("Drink not found!");
		else {
			int currentDrinkAmount = drinkQuantity.intValue();
			drinks.put(drink, Integer.valueOf(--currentDrinkAmount));
		}
	}

	public Map<Drink, Integer> getDrinks() {
		return drinks;
	}
	
	public void setDrinks(Map<Drink, Integer> drinks)
	{
		this.drinks = drinks;
	}
	
}
