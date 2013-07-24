package coffee_machine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Used to hold a collection of different types of drinks.
 * The collection itself is a Map of Drink class and an Integer
 * which shows the current amount of every drink type. The class
 * gives access to the drinks collection through a set of methods
 * which are used to add a new drink, check or decrease the curent
 * amount of a specified drink.
 * 
 * @author Hristo
 *
 */
public class DrinksContainer {
	private Map<Drink, Integer> drinks;
	
	private boolean additionClosed = false;
	
	public DrinksContainer()
	{
		drinks = new HashMap<Drink, Integer>();
	}
	
	/**
	 * Gives the ability to add a new drink to the collection,
	 * specifying its quantity or increase the current amount
	 * of a drink, given as a parameter to the method.
	 * Call commit() method after adding all drink information
	 * to close the addition. (This will not allow any more adding
	 * to the collection)
	 * @param drink - the type of drink
	 * @param quantity - the amount to add
	 * @return same instance of the class, allowing method chaining
	 */
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
	
	/**
	 * Calling this method will commit currently added drinks and will close
	 * the collection, thus not allowing any more addition to it.
	 * 
	 * @return the same instance of the class.
	 */
	public DrinksContainer commit()
	{
		additionClosed = true;
		return this;
	}
	
	/**
	 * Gives information of the amount of the given drink.
	 * @param drink - specified drink type
	 * @return current amount of the drink as an int
	 */
	public int getDrinkQuantity(Drink drink)
	{
		Objects.requireNonNull(drink, "Specified drink is null");
		Integer drinkQuantity = drinks.get(drink);
		
		if(drinkQuantity == null)
			throw new IllegalArgumentException("Drink not found!");

		return drinkQuantity.intValue();
	}
	
	/**
	 * Lowers the amount of the specified drink.
	 * @param drink
	 */
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

	/**
	 * 
	 * @return the collection of drinks
	 */
	public Map<Drink, Integer> getDrinks() {
		return new HashMap<Drink, Integer>(drinks);
	}
	
	/**
	 * Used to check whether the addition of drinks to the collection
	 * is closed.
	 * @return true if the addition is closed
	 */
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
		if (!drinks.equals(other.drinks))
			return false;
		return true;
	}
	// by Andrey
	@Override
	public String toString(){
		String map="";
		for (Map.Entry<Drink, Integer> entry : drinks.entrySet()) {
		     map+="Key: " + entry.getKey().toString() + ". Value: " + entry.getValue();
		}
		return map;
		}
	
}
