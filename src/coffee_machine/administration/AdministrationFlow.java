package coffee_machine.administration;

import coffee_machine.MenuBasedFlow;
import coffee_machine.list_drinks.DrinkListFlow;
import coffee_machine.menu.MenuBuilder;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.reports.DrinksReportFlow;

/***
 * Gives the option of choosing between generating new Drink orders report or
 * return to drink selection menu.
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class AdministrationFlow extends MenuBasedFlow {
	@Override
	protected void initMenu( CoffeeMachineState cm, MenuBuilder menuBuilder ) {
		menuBuilder
			.command("1", "Generate drink orders report", navigationCommand( new DrinksReportFlow() ) )
			.command("2", "Exit", navigationCommand( new DrinkListFlow() ) );
	}
}
