package coffeeMachine.main;

/**
 * Holds the current state of the coffee machine. Includes the lists of drinks
 * and coins which are currently available in the machine.
 * 
 * @author Hristo
 *
 */
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
	
}
