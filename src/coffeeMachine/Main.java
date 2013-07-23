package coffeeMachine;

public class Main {
	
	public static void main(String[] args) {
		// TODO: Initializations...
		
		CoffeeMachineState coffeeMachine;
		DrinksContainer drinksContainer = new DrinksContainer();
		drinksContainer.add(new Drink("Coffee", 30), 10)
				.add(new Drink("Tea", 40), 10)
				.add(new Drink("Hot chocolate", 50), 5).commit();

		MoneyAmount availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
		coffeeMachine = new CoffeeMachineState(availableCoins,
				drinksContainer);
		
		Flow flow = new DrinkListFlow();
		while(true) {
			flow = flow.execute(coffeeMachine);
		}
	}
	
}
