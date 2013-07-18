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
		this.initialDrinksAmount = drinks;
	}

	public MoneyAmount getCoins() {
		return coins;
	}

	public void setCoins(MoneyAmount coins) {
		this.coins = coins;
	}

	public DrinksContainer getCurrentDrinks() {
		return this.currentDrinksAmount;
	}
	
	public DrinksContainer getInitialDrinks() {
		return this.initialDrinksAmount;
	}
	
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
