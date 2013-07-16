package coffeeMachine.main;

import java.util.List;

import modules.menuModule.Executable;
import modules.menuModule.MenuBuilder;
import modules.menuModule.MenuController;
import modules.menuModule.MenuModel;
import modules.menuModule.ParamRequirements;
import modules.menuModule.ResultStatus;

public class PaymentFlow implements Flow {
	private Drink drink;
	private MoneyAmount userCoins;
	private boolean isOrderCancelled = false;

	public PaymentFlow(Drink drink) {
		this.drink = drink;
		userCoins = new MoneyAmount();
	}

	@Override
	public Flow execute(CoffeeMachineState machine) {
		MenuController menuController = new MenuController(buildMenu());

		do {
			System.out.println("Drink: " + drink.getName() + System.lineSeparator()
					+ "Price: " + drink.getPrice());
			menuController.start();
			if (isOrderCancelled)
				return new DrinkListFlow();
		} while (userCoins.getSumOfCoinsValue() < drink.getPrice());

		return new OrderFlow(drink, userCoins);
	}

	private MenuModel buildMenu() {
		MenuBuilder menuBuilder = new MenuBuilder();

		menuBuilder.command("1", "0.05 lv", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				// TODO Auto-generated method stub
				return new ResultStatus(addCoin(Coin.FIVE), true);
			}

			@Override
			public ParamRequirements requirements() {
				// TODO Auto-generated method stub
				return new ParamRequirements();
			}

		}).command("2", "0.10 lv", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				// TODO Auto-generated method stub
				return new ResultStatus(addCoin(Coin.TEN), true);
			}

			@Override
			public ParamRequirements requirements() {
				// TODO Auto-generated method stub
				return new ParamRequirements();
			}

		}).command("3", "0.20 lv", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				// TODO Auto-generated method stub
				return new ResultStatus(addCoin(Coin.TWENTY), true);
			}

			@Override
			public ParamRequirements requirements() {
				// TODO Auto-generated method stub
				return new ParamRequirements();
			}

		}).command("4", "0.50 lv", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				// TODO Auto-generated method stub
				return new ResultStatus(addCoin(Coin.FIFTY), true);
			}

			@Override
			public ParamRequirements requirements() {
				// TODO Auto-generated method stub
				return new ParamRequirements();
			}

		}).command("5", "1 lv", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				// TODO Auto-generated method stub
				return new ResultStatus(addCoin(Coin.LEV), true);
			}

			@Override
			public ParamRequirements requirements() {
				// TODO Auto-generated method stub
				return new ParamRequirements();
			}

		}).command("6", "Cancel payment.", new Executable() {

			@Override
			public ResultStatus execute(List<String> params) {
				// TODO Auto-generated method stub
				return new ResultStatus(cancelOrder(), true);
			}

			@Override
			public ParamRequirements requirements() {
				// TODO Auto-generated method stub
				return new ParamRequirements();
			}

		}).build();

		return new MenuModel(menuBuilder);
	}

	private String addCoin(Coin coin) {
		userCoins.add(coin);
		return "Accumulated sum: "
				+ String.valueOf(userCoins.getSumOfCoinsValue());
	}

	private String cancelOrder() {
		isOrderCancelled = true;
		return "Your order has been cancelled. "
				+ String.valueOf(userCoins.getSumOfCoinsValue()
						+ " stotinki has been returned to you.");
	}

	public Drink getDrink() {
		return drink;
	}

	public MoneyAmount getUserCoins() {
		return userCoins;
	}

	public boolean isOrderCancelled() {
		return isOrderCancelled;
	}
}
