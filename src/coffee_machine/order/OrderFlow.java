package coffee_machine.order;

import coffee_machine.Flow;
import coffee_machine.finalize_order.OrderFinalizeFlow;
import coffee_machine.insufficient_amount.InsufficientAmountFlow;
import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Drink;
import coffee_machine.model.MoneyAmount;
import coffee_machine.model.Withdraw;
import coffee_machine.model.Withdraw.WithdrawRequestResultStatus;


/**
 * Calculates the necessary change to return and the total number of coins.
 * Based on the ability to return the change in appropriate coins, decides
 * whether to continue making the drink or give the user the option to choose
 * how to finish the order.
 * 
 * @author Hristo
 *
 */
public class OrderFlow implements Flow {
	private Drink drink;
	private MoneyAmount userCoins;
	private Withdraw withdraw;
	
	public OrderFlow(Drink drink, MoneyAmount userCoins)
	{
		this.drink = drink;
		this.userCoins = userCoins;
	}
	
	/**
	 * Calculates the change, based on the coins of the user
	 * and the drink price, and sums the user inserted coins 
	 * with the ones currently available in the coffee machine. 
	 * The change is the needed money amount, which has to be appropriately
	 * withdrawn from all available coins. If this is possible the
	 * operation continues to finalizing the order, otherwise the user has
	 * to choose how to finish the order.
	 *
	 * @param CoffeeMachineState - the current state of the coffee machine,
	 * which includes the available drinks and coins.
	 * @return Flow object
	 */
	public Flow execute(CoffeeMachineState coffeeMachine)
	{	
		MoneyAmount allCoins = new MoneyAmount();

		allCoins = coffeeMachine.getCoins().add(userCoins);
		
		int change = userCoins.sumOfCoins() - drink.getPrice();
		
		this.withdraw = allCoins.withdraw(change);
		
		if(withdraw.getStatus() == WithdrawRequestResultStatus.SUCCESSFUL)
			return new OrderFinalizeFlow(drink, withdraw);
		
		return new InsufficientAmountFlow(drink, userCoins, withdraw);
	}
	
	/**
	 * Gives information whether the withdraw operation was
	 * successful or not, and the coins which were withdrawn.
	 * @return Withdraw object
	 */
	public Withdraw getWithdraw()
	{
		return this.withdraw;
	}
}
