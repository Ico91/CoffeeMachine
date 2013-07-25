package coffee_machine.insufficient_amount;

import coffee_machine.MenuBasedFlow;
import coffee_machine.finalize_order.OrderFinalizeFlow;
import coffee_machine.list_drinks.DrinkListFlow;
import coffee_machine.menu.MenuBuilder;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Drink;
import coffee_machine.model.MoneyAmount;
import coffee_machine.model.Withdraw;

/**
 * 
 * Gives the option of choosing whether to continue the order flow by making the
 * drink and returning only the available coins in the machine as a change, or
 * cancel the order which returns the user inserted coins.
 * 
 * @author Hristo
 * 
 */
public class InsufficientAmountFlow extends MenuBasedFlow {

	private MoneyAmount userCoins;
	private Withdraw withdraw;
	private Drink drink;

	public InsufficientAmountFlow(Drink drink, MoneyAmount userCoins,
			Withdraw withdraw) {
		this.drink = drink;
		this.userCoins = userCoins;
		this.withdraw = withdraw;
	}
	
	/**
	 * Holds information of the user inserted coins
	 * 
	 * @return MoneyAmount object containing coins information
	 */
	public MoneyAmount getUserCoins() {
		return userCoins;
	}

	/**
	 * Gives information whether the withdraw operation was successful or not,
	 * and the coins which were withdrawn.
	 * 
	 * @return Withdraw object
	 */
	public Withdraw getWithdraw() {
		return withdraw;
	}

	/**
	 * Get the currently chosen drink to make.
	 * 
	 * @return Drink object holding the name and the price of the chosen drink.
	 */
	public Drink getDrink() {
		return drink;
	}

	@Override
	protected void initMenu( CoffeeMachineState cm, MenuBuilder menuBuilder ) {
		System.out
				.println("The machine does not have the neccessary coins to return your change."
						+ "\n What would you like to do: ");
		
		menuBuilder
			.command("1", "Make drink and return only available change",
				navigationCommand(new OrderFinalizeFlow(drink, withdraw)))
			.command("2", "Cancel order and return inserted coins",
				navigationCommandWithOutput(new DrinkListFlow(),
				"Your money have been returned to you: "
				+ userCoins.sumOfCoins()));
	}
}
