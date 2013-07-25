package coffee_machine.list_drinks;

import java.util.List;

import coffee_machine.MenuBasedFlow;
import coffee_machine.administration.AdministrationFlow;
import coffee_machine.menu.Executable;
import coffee_machine.menu.MenuBuilder;
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

	private class DrinkSelection implements Executable {
		
		private final Drink selectedDrink;

		public DrinkSelection(Drink drink) {
			this.selectedDrink = drink;
		}

		@Override
		public ResultStatus execute(List<String> params) {
			setNext( new PaymentFlow( selectedDrink ) );

			return new ResultStatus("", true);
		}

		@Override
		public ParamRequirements requirements() {
			return new ParamRequirements();
		}
	}

	@Override
	protected void initMenu( CoffeeMachineState cm, MenuBuilder menuBuilder ) {
		int index = 1;
		for (Drink d : cm.getFiltratedDrinks()) {
			menuBuilder.command(
					Integer.toString( index++ ),
					drinkInfo( d ),
					new DrinkSelection(d)
			);
		}
		menuBuilder.hiddenCommand( "secret", navigationCommand( new AdministrationFlow() ) );
	}

	private static String drinkInfo(Drink d) {
		return String.format( "%s - %d", d.getName(), d.getPrice() );
	}
}
