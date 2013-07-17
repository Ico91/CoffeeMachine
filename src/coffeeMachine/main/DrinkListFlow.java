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
 * Gives the option of choosing drink from menu returns PaymentFlow with
 * selected drink
 * 
 * @author Andrey
 * 
 */

public class DrinkListFlow implements Flow{
	private Flow flow;
	private Drink drink;

	public DrinkListFlow() {
		drink = new Drink("drink", 0);
	}
	
	public Flow execute(CoffeeMachineState cm){
		
		MenuBuilder menuBuilder=new MenuBuilder();
		int index=0;
		for(Drink d:cm.getFiltratedDrinks()){
			menuBuilder.command(
					Integer.toString(index),
					cm.getFiltratedDrinks().get(index).getName()
							+ Integer.toString(cm.getFiltratedDrinks()
									.get(index).getPrice()),
					new DrinkSelection(drink, d.getName(), d.getPrice()));
									.get(index).getPrice()), new DrinkSelection(drink, d.getName(),d.getPrice()));
			index++;
		}

		menuBuilder.build();
		MenuModel menuModel = new MenuModel(menuBuilder);
		MenuController menuControler = new MenuController(menuModel);
		menuControler.start();

		flow = new PaymentFlow(drink);
		return flow;
		
		
		return new PaymentFlow(drink); 
		
	}

	/*
	 * private String neshto(Drink drink){ flow=new PaymentFlow(drink); return
	 * null; }
	 */

	private class DrinkSelection implements Executable {
		private Drink drink;
		private String name;
		private int price;

		public DrinkSelection(Drink drink, String name, int price) {
			this.name = name;
			this.price = price;
			this.drink = drink;
			this.name=name;
			this.price=price;
		}

		@Override
		public ResultStatus execute(List<String> params) {
			drink.setName(name);
			drink.setPrice(price);
			return new ResultStatus("", true);
		}

		@Override
		public ParamRequirements requirements() {
			// TODO Auto-generated method stub
			return new ParamRequirements();
		}

	}
}
