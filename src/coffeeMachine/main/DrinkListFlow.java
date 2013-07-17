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

<<<<<<< HEAD
public class DrinkListFlow implements Flow {
=======


public class DrinkListFlow implements Flow{
>>>>>>> 4fcaac2d4021108990bc42fb20539849ff060766
	private Flow flow;
	private Drink drink;

	public DrinkListFlow() {
		drink = new Drink("drink", 0);
	}
<<<<<<< HEAD

	public Flow execute(CoffeeMachineState cm) {

		MenuBuilder menuBuilder = new MenuBuilder();
		final int index = 1;
		for ( Drink d : cm.getFiltratedDrinks() ) {
=======
	
	public Drink getDrink(){ // for testing
		return drink;
	}
	
	public Flow execute(CoffeeMachineState cm){
		
		MenuBuilder menuBuilder=new MenuBuilder();
		int index=0;
		for(Drink d:cm.getFiltratedDrinks()){
>>>>>>> 4fcaac2d4021108990bc42fb20539849ff060766
			menuBuilder.command(
					Integer.toString(index),
					cm.getFiltratedDrinks().get(index).getName()
							+ Integer.toString(cm.getFiltratedDrinks()
<<<<<<< HEAD
									.get(index).getPrice()),
					new DrinkSelection(drink, d.getName(), d.getPrice()));

=======
									.get(index).getPrice()), new DrinkSelection(drink, d.getName(),d.getPrice()));
			index++;
>>>>>>> 4fcaac2d4021108990bc42fb20539849ff060766
		}

		menuBuilder.build();
		MenuModel menuModel = new MenuModel(menuBuilder);
		MenuController menuControler = new MenuController(menuModel);
		menuControler.start();
<<<<<<< HEAD

		flow = new PaymentFlow(drink);
		return flow;
=======
		
		
		return new PaymentFlow(drink); 
		
>>>>>>> 4fcaac2d4021108990bc42fb20539849ff060766
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
<<<<<<< HEAD
			this.name = name;
			this.price = price;
=======
			this.drink = drink;
			this.name=name;
			this.price=price;
>>>>>>> 4fcaac2d4021108990bc42fb20539849ff060766
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
