package coffee_machine.list_drinks;

import java.util.List;

import coffee_machine.Flow;
import coffee_machine.administration.AdministrationFlow;
import coffee_machine.menu.Executable;
import coffee_machine.menu.MenuBuilder;
import coffee_machine.menu.MenuController;
import coffee_machine.menu.MenuModel;
import coffee_machine.menu.ParamRequirements;
import coffee_machine.menu.ResultStatus;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Drink;
import coffee_machine.payment.PaymentFlow;


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
