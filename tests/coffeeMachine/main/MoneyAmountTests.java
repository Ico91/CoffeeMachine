package coffeeMachine.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.main.InvalidWithdrawAmountException;
import coffeeMachine.main.MoneyAmount;
import coffeeMachine.main.RequestResultStatus;
import coffeeMachine.main.Withdraw;

public class MoneyAmountTests {

	private MoneyAmount availableCoins;
	private int requestedAmount;

	@Before
	public void testMoneyAmount_SetUpObject() {
		availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
	}

	@Test(expected = InvalidParameterException.class)
	public void testExplicitConstructor_MustThrowInvalidParameterException() {
		availableCoins = new MoneyAmount(1, 1, -1, 1, 1);
	}

	@Test
	public void testExplicitConstructor() {
		try {
			availableCoins = new MoneyAmount(1, 1, 1, 1, 1);
		} catch (InvalidParameterException e) {
			fail();
		}
	}

	@Test
	public void testGetSumOfCoinsValue_MustReturnCorrectSum() {
		int expected = 325;
		int actual = availableCoins.getSumOfCoinsValue();

		assertTrue(expected == actual);
	}

	@Test
	public void testGetSumOfCoinsValueAfterWithdraw_MustBeSuccess() {
		int before = availableCoins.getSumOfCoinsValue();
		availableCoins.withdraw(175);
		int actual = availableCoins.getSumOfCoinsValue();
		int expected = before - 175;
		assertTrue("Expected sum must be same as actual( " + expected + " "
				+ actual + ")", expected == actual);
	}

	@Test
	public void testWithdraw_requestedAmountLessThanAvailable_ExpectSuccess() {
		requestedAmount = 175;
		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals("Operation status should be successful",
				amount.getStatus(), RequestResultStatus.SUCCESSFUL);
	}

	@Test
	public void testEqual_MustReturnFalse() {
		MoneyAmount moneyAmount = new MoneyAmount(1, 1, 1, 1, 1);
		assertNotSame(moneyAmount, availableCoins);
	}

	@Test
	public void testWithdraw_requestedAmountLessThanAvailable_ExpectCorrectChangeResult() {
		requestedAmount = 235;
		MoneyAmount expected = new MoneyAmount(1, 1, 1, 2, 1);
		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals(expected, amount.getChange());
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

	@Test(expected = InvalidWithdrawAmountException.class)
	public void testWithDraw_requestedAmountLessThanZero_ExpectException()
			throws InvalidWithdrawAmountException {
		requestedAmount = -50;
		availableCoins.withdraw(requestedAmount);
	}

	@Test
	public void testAdd_addSomeCoins_ExpectCorrectSumOfCoins() {
		MoneyAmount moneyAmount = new MoneyAmount();
		availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 10).add(Coin.TEN, 9).add(Coin.TWENTY, 8)
				.add(Coin.FIFTY, 7).add(Coin.LEV, 6);

		int expected = availableCoins.getSumOfCoinsValue()
				+ moneyAmount.getSumOfCoinsValue();
		int actual = (availableCoins.mergeWith(moneyAmount))
				.getSumOfCoinsValue();

		assertTrue("Expected sum must be same as actual", expected == actual);
	}

	@After
	public void testMoneyAmount_TearDownObject() {
		availableCoins = null;
	}
}
