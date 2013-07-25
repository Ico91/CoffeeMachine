package coffee_machine.payment;

import java.util.List;

import coffee_machine.MenuBasedFlow;
import coffee_machine.list_drinks.DrinkListFlow;
import coffee_machine.menu.Command;
import coffee_machine.menu.ParamRequirements;
import coffee_machine.menu.ResultStatus;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Coin;
import coffee_machine.model.Drink;
import coffee_machine.model.MoneyAmount;

/**
 * Gives the ability to accumulate money by inserting coins into a temporary
 * stored container. Also holds information for the selected drink.
 * 
 * @author Hristo
 * 
 */
public class PaymentFlow extends MenuBasedFlow {
	private Drink drink;
	private MoneyAmount userCoins;

	public PaymentFlow(Drink drink) {
		this.drink = drink;
		userCoins = new MoneyAmount();
	}

	private class CoinInsertinCommand implements Command {
		private Coin coin;

		private CoinInsertinCommand(Coin coin) {
			this.coin = coin;
		}

		@Override
		public ResultStatus execute(List<String> params) {
			userCoins.add(this.coin, 1);
			printOrderInfo();

			boolean exit = (userCoins.sumOfCoins() < drink.getPrice()) ? false
					: true;
			return new ResultStatus("Accumulated sum: "
					+ String.valueOf(userCoins.sumOfCoins()), exit);
		}

		@Override
		public ParamRequirements requirements() {
			return new ParamRequirements();
		}

	}

	public void printOrderInfo() {
		System.out.println("Drink: " + drink.getName());
		System.out.println("Price: " + drink.getPrice());
	}

	public Drink getDrink() {
		return drink;
	}

	public MoneyAmount getUserCoins() {
		return userCoins;
	}

	@Override
	protected void initMenu(CoffeeMachineState cm) {
		printOrderInfo();

		menuBuilder
				.command("1", "0.05 lv", new CoinInsertinCommand(Coin.FIVE))
				.command("2", "0.10 lv", new CoinInsertinCommand(Coin.TEN))
				.command("3", "0.20 lv", new CoinInsertinCommand(Coin.TWENTY))
				.command("4", "0.50 lv", new CoinInsertinCommand(Coin.FIFTY))
				.command("5", "1 lv", new CoinInsertinCommand(Coin.LEV))
				.command(
						"6",
						"Cancel payment.",
						navigationCommandWithOutput(
								new DrinkListFlow(),
								"Your order has been cancelled. "
										+ String.valueOf(userCoins.sumOfCoins()
												+ " stotinki has been returned to you.")));
	}
}
