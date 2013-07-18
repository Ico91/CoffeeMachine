package coffeeMachine;

import java.util.List;

import modules.menuModule.Executable;
import modules.menuModule.MenuBuilder;
import modules.menuModule.MenuController;
import modules.menuModule.MenuModel;
import modules.menuModule.ParamRequirements;
import modules.menuModule.ResultStatus;

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
				.command("1", "Make drink and don't return change",
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

	public MoneyAmount getUserCoins() {
		return userCoins;
	}

	public Withdraw getWithdraw() {
		return withdraw;
	}

	public Drink getDrink() {
		return drink;
	}

	public Flow getNextFlow() {
		return nextFlow;
	}
}
