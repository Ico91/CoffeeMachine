package coffee_machine.list_drinks;

import java.util.List;

import coffee_machine.MenuBasedFlow;
import coffee_machine.administration.AdministrationFlow;
import coffee_machine.menu.Executable;
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

public class DrinkListFlow extends MenuBasedFlow {
	private Drink drink;

	public DrinkListFlow() {

	}

	private class DrinkSelection implements Executable {
		private Drink selectedDrink;

		public DrinkSelection(Drink drink) {
			this.selectedDrink = drink;
		}

		@Override
		public ResultStatus execute(List<String> params) {
			drink = new Drink(selectedDrink.getName(), selectedDrink.getPrice());
			setNext(new PaymentFlow(getDrink()));

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

	@Override
	protected void initMenu(CoffeeMachineState cm) {
		int index = 1;
		for (Drink d : cm.getFiltratedDrinks()) {
			menuBuilder.command(
					Integer.toString(index),
					cm.getFiltratedDrinks().get(index - 1).getName()
							+ " - "
							+ Integer.toString(cm.getFiltratedDrinks()
									.get(index - 1).getPrice()),
					new DrinkSelection(d));
			index++;
		}
		menuBuilder.hiddenCommand("secret",
				navigationCommand(new AdministrationFlow()));
	}
}
