package coffeeMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class DrinksContainer {
	private Map<Drink, Integer> drinks;
	
	private boolean additionClosed = false;
	
	public DrinksContainer()
	{
		drinks = new HashMap<Drink, Integer>();
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
	
	public int getDrinkQuantity(Drink drink)
	{
		Objects.requireNonNull(drink, "Specified drink is null");
		Integer drinkQuantity = drinks.get(drink);
		
		if(drinkQuantity == null)
			throw new IllegalArgumentException("Drink not found!");

		return drinkQuantity.intValue();
	}
	
	public void decreaseDrinkAmount(Drink drink)
	{
		Objects.requireNonNull(drink, "Specified drink is null");
		Integer drinkQuantity = drinks.get(drink);
		
		if(drinkQuantity == null)
			throw new IllegalArgumentException("Drink not found!");
		else {
			int currentDrinkAmount = drinkQuantity.intValue();
			drinks.put(drink, Integer.valueOf(--currentDrinkAmount));
		}
	}

	public Map<Drink, Integer> getDrinks() {
		return new TreeMap<Drink, Integer>(drinks);
	}
	
	public boolean isAdditionClosed()
	{
		return this.additionClosed;
	}

	@Override
	public int hashCode() {
		return drinks.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DrinksContainer))
			return false;
		DrinksContainer other = (DrinksContainer) obj;
		if (drinks == null) {
			if (other.drinks != null)
				return false;
		} else if (!drinks.equals(other.drinks))
			return false;
		return true;
	}

	
}
