package coffeeMachine.main;

import java.util.Map;

/***
 * Finalizing order and show information about ordered drink and change to the
 * user
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class OrderFinalizeFlow implements Flow {

	private Drink drink;
	private Withdraw change;

	/***
	 * 
	 * @param drink
	 *            - Ordered drink
	 * @param change
	 *            - Returned change
	 */
	public OrderFinalizeFlow(Drink drink, Withdraw change) {
		this.drink = drink;
		this.change = change;
	}

	@Override
	public Flow execute(CoffeeMachineState machine) {
		printOrderInformation();

		finalizeDrinkOrder(machine);
		removeChangeFromMachine(machine);

		return new DrinkListFlow();
	}

	void removeChangeFromMachine(CoffeeMachineState machine) {
		MoneyAmount moneyInMachine = machine.getCoins();
		moneyInMachine.withdraw(this.change.getChange().getSumOfCoinsValue());
		machine.setCoins(moneyInMachine);
	}

	private void printOrderInformation() {
		System.out.println("Your " + this.drink.getName() + " is ready.");
		if (this.change.getStatus() == RequestResultStatus.SUCCESSFUL) {
			System.out.println("Your change is: "
					+ this.change.getChange().toString());
		} else {
			System.out.println("You receive: "
					+ this.change.getChange().toString() + " as change.");
		}
	}

	void finalizeDrinkOrder(CoffeeMachineState machine) {
		DrinksContainer drinksContainer = machine.getDrinks();
		Map<Drink, Integer> drinks = drinksContainer.getDrinks();
		int countOfSelectedDrink = drinks.get(drink);

		countOfSelectedDrink--;
		drinks.put(this.drink, countOfSelectedDrink);

		drinksContainer.setDrinks(drinks);
		machine.setDrinks(drinksContainer);
	}

	/***
	 * Getter for ordered drink
	 * 
	 * @return ordered drink
	 */
	public Drink getDrink() {
		return drink;
	}

	/***
	 * Setter for ordered drink
	 * 
	 * @param drink
	 *            - ordered drink
	 */
	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	/***
	 * Getter for returned change
	 * 
	 * @return Withdraw object containing returned change
	 */
	public Withdraw getChange() {
		return change;
	}

	/***
	 * Setter for returned change
	 * 
	 * @param Withdraw
	 *            object containing returned change
	 */
	public void setChange(Withdraw change) {
		this.change = change;
	}
}
