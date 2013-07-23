package coffeeMachine.flows;

import java.util.List;

import coffeeMachine.CoffeeMachineState;

import modules.menuModule.Executable;
import modules.menuModule.MenuBuilder;
import modules.menuModule.MenuController;
import modules.menuModule.MenuModel;
import modules.menuModule.ParamRequirements;
import modules.menuModule.ResultStatus;

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
