package coffeeMachine.main;

import java.util.List;

import modules.menuModule.Executable;
import modules.menuModule.MenuBuilder;
import modules.menuModule.MenuController;
import modules.menuModule.MenuModel;
import modules.menuModule.ParamRequirements;
import modules.menuModule.ResultStatus;

/**
 * 
 * Gives the option of choosing whether to continue the order flow by
 * making the drink and returning only the available coins in the machine 
 * as a change, or cancel the order which returns the user inserted coins.  
 * 
 * @author Hristo
 * 
 */
public class InsufficientAmountFlow implements Flow {

	private MoneyAmount userCoins;
	private Withdraw withdraw;
	private Drink drink;

	private Flow flow;

	public InsufficientAmountFlow(Drink drink, MoneyAmount userCoins,
			Withdraw withdraw) {
		this.drink = drink;
		this.userCoins = userCoins;
		this.withdraw = withdraw;
	}

	/**
	 * 
	 * @param CoffeeMachineState - current state of the coffee machine
	 * @return Flow object
	 * 
	 */
	@Override
	public Flow execute(CoffeeMachineState machine) {
		System.out
				.println("The machine does not have the neccessary coins to return your change."
						+ "\n What would you like to do: ");

		MenuBuilder menuBuilder = new MenuBuilder();
		menuBuilder
				.command("1", "Make drink and don't return change",
						new Executable() {

							@Override
							public ResultStatus execute(List<String> params) {
								return new ResultStatus(
										InitOrderFinalizeFlow(), true);
							}

							@Override
							public ParamRequirements requirements() {
								// TODO Auto-generated method stub
								return new ParamRequirements();
							}

						})
				.command("2", "Cancel order and return inserted coins",
						new Executable() {

							@Override
							public ResultStatus execute(List<String> params) {
								// TODO Auto-generated method stub
								return new ResultStatus(InitDrinkListFlow(),
										true);
							}

							@Override
							public ParamRequirements requirements() {
								// TODO Auto-generated method stub
								return new ParamRequirements();
							}

						}).build();

		MenuModel menuModel = new MenuModel(menuBuilder);
		MenuController menuController = new MenuController(menuModel);

		menuController.start();

		return flow;
	}

	private String InitOrderFinalizeFlow() {
		flow = new OrderFinalizeFlow(drink, withdraw);
	}

	private String InitDrinkListFlow() {
		System.out
				.println("Order canceled. The money you've inserted are returned to You!"
						+ userCoins.toString());
		flow = new DrinkListFlow();
	}

}
