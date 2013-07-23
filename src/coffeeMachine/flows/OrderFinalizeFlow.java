package coffeeMachine.flows;

import java.util.Objects;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;
import coffeeMachine.Withdraw;
import coffeeMachine.Withdraw.WithdrawRequestResultStatus;

/***
 * Finalizing order and print information about ordered drink and change
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class OrderFinalizeFlow implements Flow {

	private Drink drink;
	private Withdraw change;

	/***
	 * Constructs new OrderFinalizeFlow object
	 * @param drink
	 *            - Ordered drink
	 * @param change
	 *            - Returned change
	 */
	public OrderFinalizeFlow(Drink drink, Withdraw change) {
		Objects.requireNonNull(drink, "Drink cannot be null");
		Objects.requireNonNull(change, "Change cannot be null");
		
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
		machine.getCoins().withdraw(this.change.getChange().sumOfCoins());
	}

	private void printOrderInformation() {
		System.out.println("Your " + this.drink.getName() + " is ready.");
		if (this.change.getStatus() == WithdrawRequestResultStatus.SUCCESSFUL) {
			System.out.println("Your change is: "
					+ this.change.getChange().toString());
		} else {
			System.out.println("You receive: "
					+ this.change.getChange().toString() + " as change.");
		}
	}
	
	void finalizeDrinkOrder(CoffeeMachineState machine) {
		machine.getCurrentDrinks().decreaseDrinkAmount(drink);
	}

	/***
	 * Getter for ordered drink
	 * 
	 * @return Ordered drink
	 */
	public Drink getDrink() {
		return drink;
	}

	/***
	 * Setter for Ordered drink
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
