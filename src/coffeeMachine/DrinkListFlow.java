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
 * Gives the option of choosing drink from menu returns PaymentFlow with
 * selected drink
 * 
 * @author Andrey
 * 
 */

public class DrinkListFlow implements Flow{
	private Drink drink;

	public DrinkListFlow() {
		
	}
	
	public Flow execute(CoffeeMachineState cm){
		
		MenuBuilder menuBuilder=new MenuBuilder();
		int index=1;
		for(Drink d:cm.getFiltratedDrinks()){
			menuBuilder.command(
					Integer.toString(index),
					cm.getFiltratedDrinks().get(index - 1).getName()
							+ Integer.toString(cm.getFiltratedDrinks()
									.get(index - 1).getPrice()),
					new DrinkSelection(d));
			index++;
		}

		menuBuilder.build();
		MenuModel menuModel = new MenuModel(menuBuilder);
		MenuController menuControler = new MenuController(menuModel);
		menuControler.start();

		return new PaymentFlow(drink);
	}

	private class DrinkSelection implements Executable {
		private Drink selectedDrink;
		public DrinkSelection(Drink drink) {
			this.selectedDrink = drink;
		}

		@Override
		public ResultStatus execute(List<String> params) {
			drink = new Drink(selectedDrink.getName(), selectedDrink.getPrice());
			return new ResultStatus("", true);
		}

		@Override
		public ParamRequirements requirements() {
			// TODO Auto-generated method stub
			return new ParamRequirements();
		}

	}
	
	public Drink getDrink() {
		return this.drink;
	}
}
