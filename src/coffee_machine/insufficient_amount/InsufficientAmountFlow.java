package coffee_machine.insufficient_amount;

import java.util.List;

import coffee_machine.Flow;
import coffee_machine.finalize_order.OrderFinalizeFlow;
import coffee_machine.list_drinks.DrinkListFlow;
import coffee_machine.menu.Executable;
import coffee_machine.menu.MenuBuilder;
import coffee_machine.menu.MenuController;
import coffee_machine.menu.MenuModel;
import coffee_machine.menu.ParamRequirements;
import coffee_machine.menu.ResultStatus;
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
public class InsufficientAmountFlow implements Flow {

	private MoneyAmount userCoins;
	private Withdraw withdraw;
	private Drink drink;

	private Flow nextFlow;

	public InsufficientAmountFlow(Drink drink, MoneyAmount userCoins,
			Withdraw withdraw) {
		this.drink = drink;
		this.userCoins = userCoins;
		this.withdraw = withdraw;
	}

	/**
	 * 
	 * Prints the available options to the user. Based on the user's choice this
	 * method either continues making the drink and returning only the available
	 * coins or cancels the order, returning the coins inserted by the user.
	 * 
	 * @param CoffeeMachineState
	 *            - current state of the coffee machine
	 * @return Flow object
	 * 
	 */
	@Override
	public Flow execute(CoffeeMachineState coffeeMachine) {
		System.out
				.println("The machine does not have the neccessary coins to return your change."
						+ "\n What would you like to do: ");

		
		MenuController menuController = new MenuController(buildMenu());

		menuController.start();

		return nextFlow;
	}
	
	private MenuModel buildMenu()
	{
		MenuBuilder menuBuilder = new MenuBuilder();
		menuBuilder
				.command("1", "Make drink and return only available change",
						new Executable() {

							@Override
							public ResultStatus execute(List<String> params) {
								nextFlow = new OrderFinalizeFlow(drink, withdraw);
								return new ResultStatus("", true);
							}

							@Override
							public ParamRequirements requirements() {
								return new ParamRequirements();
							}

						})
				.command("2", "Cancel order and return inserted coins",
						new Executable() {

							@Override
							public ResultStatus execute(List<String> params) {
								nextFlow = new DrinkListFlow();
								return new ResultStatus(
										"Your money has been returned to you: "
												+ userCoins
														.sumOfCoins(),
										true);
							}

							@Override
							public ParamRequirements requirements() {
								return new ParamRequirements();
							}

						}).build();

		return new MenuModel(menuBuilder);
	}

	/**
	 * Holds information of the user inserted coins
	 * @return MoneyAmount object containing coins information
	 */
	public MoneyAmount getUserCoins() {
		return userCoins;
	}

	/**
	 * Gives information whether the withdraw operation was
	 * successful or not, and the coins which were withdrawn.
	 * @return Withdraw object
	 */
	public Withdraw getWithdraw() {
		return withdraw;
	}

	/**
	 * Get the currently chosen drink to make.
	 * @return Drink object holding the name and the price of the chosen drink.
	 */
	public Drink getDrink() {
		return drink;
	}

	/**
	 * Get the next point of the order execution.
	 * @return interface Flow object
	 */
	public Flow getNextFlow() {
		return nextFlow;
	}
}
