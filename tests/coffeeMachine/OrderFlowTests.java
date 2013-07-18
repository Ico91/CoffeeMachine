package coffeeMachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.Withdraw.WithdrawRequestResultStatus;

public class OrderFlowTests {

	private OrderFlow orderFlow;
	private CoffeeMachineState coffeeMachine;

	@Before
	public void testOrderFlow_setUpObject() {
		this.coffeeMachine = new CoffeeMachineState(new MoneyAmount(),
				new DrinksContainer());
	}

	@Test
	public void testExecute_enoughCoinsToReturn() {
		Drink drink = new Drink("Coffee", 65);
		MoneyAmount userCoins = new MoneyAmount();
		userCoins.add(Coin.FIVE, 1).add(Coin.TEN, 1).add(Coin.TWENTY, 1)
				.add(Coin.FIFTY, 1).add(Coin.LEV, 1);
		orderFlow = new OrderFlow(drink, userCoins);

		orderFlow.execute(coffeeMachine);

		Withdraw withdrawResult = orderFlow.getWithdraw();
		Withdraw expectedWithdraw = new Withdraw(
				WithdrawRequestResultStatus.SUCCESSFUL, new MoneyAmount()
						.add(Coin.FIVE, 0).add(Coin.TEN, 0).add(Coin.TWENTY, 1)
						.add(Coin.FIFTY, 0).add(Coin.LEV, 1));
		boolean isEqual = false;

		if (withdrawResult.equals(expectedWithdraw))
			isEqual = true;

		assertTrue(isEqual);
	}

	@Test
	public void testExecute_notEnoughCoinsToReturn() {
		Drink drink = new Drink("Coffee", 45);
		MoneyAmount userCoins = new MoneyAmount().add(Coin.FIVE, 1)
				.add(Coin.TEN, 1).add(Coin.TWENTY, 1).add(Coin.FIFTY, 1)
				.add(Coin.LEV, 1);
		orderFlow = new OrderFlow(drink, userCoins);

		orderFlow.execute(coffeeMachine);

		Withdraw withdrawResult = orderFlow.getWithdraw();
		Withdraw expectedWithdraw = new Withdraw(
				WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT,
				new MoneyAmount().add(Coin.FIVE, 1).add(Coin.TEN, 1)
						.add(Coin.TWENTY, 1).add(Coin.FIFTY, 0)
						.add(Coin.LEV, 1));
		System.out.println("Expected " + expectedWithdraw.toString());
		boolean isEqual = false;

		if (withdrawResult.equals(expectedWithdraw))
			isEqual = true;

		assertTrue(isEqual);
	}

	@After
	public void testOrderFlow_tearDown() {
		orderFlow = null;
	}

}
