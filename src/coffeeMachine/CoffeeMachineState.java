package coffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Holds the current state of the coffee machine. Includes the lists of drinks
 * and coins which are currently available in the machine.
 * 
 * @author Hristo
 *
 */
public class CoffeeMachineState {
	private MoneyAmount coins;
	private DrinksContainer currentDrinksAmount;
	private final DrinksContainer initialDrinksAmount;
	
	public CoffeeMachineState(MoneyAmount coins, DrinksContainer drinks) {
		this.coins = coins;
		this.currentDrinksAmount = drinks;
		this.initialDrinksAmount = new DrinksContainer();
		
		for(Drink drink : currentDrinksAmount.getDrinks().keySet())
		{
			this.initialDrinksAmount.add(drink, currentDrinksAmount.getDrinkQuantity(drink));
		}
		this.initialDrinksAmount.commit();

	}

	/**
	 * Get information of the currently contained coins in the machine.
	 * @return MoneyAmount object, holding information of the coins in the machine.
	 */
	public MoneyAmount getCoins() {
		return coins;
	}

	public void setCoins(MoneyAmount coins) {
		this.coins = coins;
	}

	/**
	 * Get the information of the currently
	 * contained drinks in the machine
	 * @return DrinksContainer object, holding information of the currently available drinks
	 */
	public DrinksContainer getCurrentDrinks() {
		return this.currentDrinksAmount;
	}
	
	/**
	 * Get the information of the contained drinks 
	 * when the machine was initialized.
	 * @return DrinksContainer object, holding information of the initially available drinks
	 */
	public DrinksContainer getInitialDrinks() {
		return this.initialDrinksAmount;
	}
	
	/**
	 * Use this method to get a list of the currently available
	 * drinks in the machine, which amount is more than zero.
	 * @return a list of the available drinks in the machine
	 */
	public List<Drink> getFiltratedDrinks(){  // by Andrey
		List<Drink> drinksList=new ArrayList<Drink>();
		for(Map.Entry<Drink, Integer> entry: this.currentDrinksAmount.getDrinks().entrySet()){
			if(entry.getValue()!=0){
				drinksList.add(entry.getKey());
			}
		
		}
		return drinksList;
	}
	
}
