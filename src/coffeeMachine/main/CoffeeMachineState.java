package coffeeMachine.main;

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
