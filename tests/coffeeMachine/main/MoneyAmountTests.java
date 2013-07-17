package coffeeMachine.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.main.Coin;
import coffeeMachine.main.InvalidWithdrawAmountException;
import coffeeMachine.main.MoneyAmount;
import coffeeMachine.main.RequestResultStatus;
import coffeeMachine.main.Withdraw;

public class MoneyAmountTests {

	private MoneyAmount availableCoins;
	private int requestedAmount;

	@Before
	public void testMoneyAmount_SetUpObject() {
		Map<Coin, Integer> coins = new TreeMap<Coin, Integer>();
		coins.put(Coin.FIVE, 5);
		coins.put(Coin.TEN, 4);
		coins.put(Coin.TWENTY, 3);
		coins.put(Coin.FIFTY, 2);
		coins.put(Coin.LEV, 1);

		availableCoins = new MoneyAmount(coins);
	}

	@Test
	public void testGetSumOfCoinsValueAfterWithdraw_MustBeSuccess() {
		int before = availableCoins.getSumOfCoinsValue();
		availableCoins.withdraw(175);
		int actual = availableCoins.getSumOfCoinsValue();
		int expected = before - 175;
		assertTrue(expected == actual);
	}

	@Test
	public void testWithdraw_requestedAmountLessThanAvailable_ExpectSuccess() {
		requestedAmount = 175;
		try {
			Withdraw amount = availableCoins.withdraw(requestedAmount);
			assertEquals("Operation status should be successful",
					amount.getStatus(), RequestResultStatus.SUCCESSFUL);
			System.out.println("After withdrawing 175"
					+ amount.getChange().toString());
		} catch (InvalidWithdrawAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testWithdraw_requestedAmountLessThanAvailable_ExpectCorrectChangeResult() {
		requestedAmount = 235;
		Map<Coin, Integer> coinsToReturn = new TreeMap<Coin, Integer>();
		coinsToReturn.put(Coin.LEV, 1);
		coinsToReturn.put(Coin.FIFTY, 2);
		coinsToReturn.put(Coin.TWENTY, 1);
		coinsToReturn.put(Coin.TEN, 1);
		coinsToReturn.put(Coin.FIVE, 1);

		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals("The the coins should be the same",
				coinsToReturn.toString(), amount.getChange().getCoins()
						.toString());
	}

	@Test
	public void testWithdraw_requestedAmountMoreThanAvailable_ExpectInsufficientAmount() {
		requestedAmount = 375;
		try {
			Withdraw amount = availableCoins.withdraw(requestedAmount);
			assertEquals("Operation status should not be successful",
					amount.getStatus(), RequestResultStatus.INSUFFICIENT_AMOUNT);
		} catch (InvalidWithdrawAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testWithDraw_requestedAmountLessThanZero_ExpectException()
			throws InvalidWithdrawAmountException {
		requestedAmount = -50;
		availableCoins.withdraw(requestedAmount);
	}

	@Test
	public void testAdd_addSomeCoins_ExpectCorrectSumOfCoins() {
		System.out
				.println("After initialization: " + availableCoins.toString());
		Map<Coin, Integer> coinsToAdd = new TreeMap<Coin, Integer>();
		coinsToAdd.put(Coin.FIVE, 10);
		coinsToAdd.put(Coin.TEN, 9);
		coinsToAdd.put(Coin.TWENTY, 8);
		coinsToAdd.put(Coin.FIFTY, 7);
		coinsToAdd.put(Coin.LEV, 6);

		availableCoins.add(coinsToAdd);

		System.out.println("After addition: " + availableCoins.toString());
	}

	@After
	public void testMoneyAmount_TearDownObject() {
		availableCoins = null;
	}
}
