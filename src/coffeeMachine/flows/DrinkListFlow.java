package coffeeMachine.flows;

import java.util.List;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;

import modules.menuModule.Executable;
import modules.menuModule.MenuBuilder;
import modules.menuModule.MenuController;
import modules.menuModule.MenuModel;
import modules.menuModule.ParamRequirements;
import modules.menuModule.ResultStatus;

/**
 * 
 * Gives the option of choosing drink from menu returns PaymentFlow with
 * selected drink
 * 
 * @author Andrey
 * 
 */

public class DrinkListFlow implements Flow {
	private Drink drink;
	private Flow flow;

	public DrinkListFlow() {

	}

	public Flow execute(CoffeeMachineState cm) {

		MenuBuilder menuBuilder = new MenuBuilder();
		int index = 1;
		for (Drink d : cm.getFiltratedDrinks()) {
			menuBuilder.command(
					Integer.toString(index),
					cm.getFiltratedDrinks().get(index - 1).getName() + " - "
							+ Integer.toString(cm.getFiltratedDrinks()
									.get(index - 1).getPrice()),
					new DrinkSelection(d));
			index++;
		}
		menuBuilder.hiddenCommand("secret", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				setFlow(new AdministrationFlow());
				return new ResultStatus("", true);
			}

			@Override
			public ParamRequirements requirements() {
				return new ParamRequirements();
			}

		});
		menuBuilder.build();
		MenuModel menuModel = new MenuModel(menuBuilder);
		MenuController menuControler = new MenuController(menuModel);
		menuControler.start();

		return this.flow;
	}

	private class DrinkSelection implements Executable {
		private Drink selectedDrink;

		public DrinkSelection(Drink drink) {
			this.selectedDrink = drink;
		}

		@Override
		public ResultStatus execute(List<String> params) {
			drink = new Drink(selectedDrink.getName(), selectedDrink.getPrice());
			setFlow(new PaymentFlow(getDrink()));

			return new ResultStatus("", true);
		}

		@Override
		public ParamRequirements requirements() {
			return new ParamRequirements();
		}

	}

	public Drink getDrink() {
		return this.drink;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}
}
