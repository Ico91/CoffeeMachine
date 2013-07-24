package coffee_machine.administration;

import java.util.List;

import coffee_machine.Flow;
import coffee_machine.list_drinks.DrinkListFlow;
import coffee_machine.menu.Executable;
import coffee_machine.menu.MenuBuilder;
import coffee_machine.menu.MenuController;
import coffee_machine.menu.MenuModel;
import coffee_machine.menu.ParamRequirements;
import coffee_machine.menu.ResultStatus;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.reports.DrinksReportFlow;


/***
 * Gives the option of choosing between generating new Drink orders report or
 * return to drink selection menu.
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class AdministrationFlow implements Flow {

	private Flow nextFlow;

	@Override
	public Flow execute(CoffeeMachineState machine) {
		MenuBuilder menuBuilder = new MenuBuilder();

		MenuModel menuModel = buildMenu(menuBuilder);
		MenuController menuController = new MenuController(menuModel);
		menuController.start();

		return this.nextFlow;
	}

	/*
	 * @param menuBuilder
	 */
	private MenuModel buildMenu(MenuBuilder menuBuilder) {
		menuBuilder.command("1", "Generate drink orders report",
				new Executable() {

					@Override
					public ParamRequirements requirements() {
						return new ParamRequirements();
					}

					@Override
					public ResultStatus execute(List<String> params) {
						setNextFlow(new DrinksReportFlow());
						return new ResultStatus("", true);
					}
				}).command("2", "Exit", new Executable() {

			@Override
			public ParamRequirements requirements() {
				return new ParamRequirements();
			}

			@Override
			public ResultStatus execute(List<String> params) {
				setNextFlow(new DrinkListFlow());
				return new ResultStatus("", true);
			}
		});
		return menuBuilder.build();
	}

	public Flow getNextFlow() {
		return nextFlow;
	}

	public void setNextFlow(Flow nextFlow) {
		this.nextFlow = nextFlow;
	}
}
