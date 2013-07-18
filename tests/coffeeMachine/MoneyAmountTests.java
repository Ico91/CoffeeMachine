package coffeeMachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.Coin;
import coffeeMachine.MoneyAmount;
import coffeeMachine.Withdraw;
import coffeeMachine.Withdraw.WithdrawRequestResultStatus;
import coffeeMachine.exceptions.InvalidWithdrawAmountException;

//TODO test method names are too long
public class MoneyAmountTests {

	private MoneyAmount availableCoins;
	private int requestedAmount;

	@Before
	public void testMoneyAmount_SetUpObject() {
		availableCoins = new MoneyAmount().add(Coin.FIVE, 5).add(Coin.TEN, 4)
				.add(Coin.TWENTY, 3).add(Coin.FIFTY, 2).add(Coin.LEV, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConstructorParameter() {
		availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 1).add(Coin.TEN, 1).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 1).add(Coin.LEV, -1);
	}

	@Test
	public void testExplicitConstructor() {
		availableCoins.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
	}

	@Test
	public void testGetSumOfCoinsValue_MustReturnCorrectSum() {
		assertEquals(325, availableCoins.sumOfCoins());
	}

	@Test
	public void testGetSumOfCoinsValueAfterWithdraw_MustBeSuccess() {
		int before = availableCoins.sumOfCoins();

		Withdraw withdraw = availableCoins.withdraw(175);
		assertEquals(WithdrawRequestResultStatus.SUCCESSFUL, withdraw.getStatus());

		int actual = availableCoins.sumOfCoins();
		int expected = before - 175;
		assertTrue("Expected sum must be same as actual( " + expected + " "
				+ actual + ")", expected == actual);
	}

	@Test
	public void testWithdraw_requestedAmountLessThanAvailable_ExpectSuccess() {
		requestedAmount = 175;
		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals("Operation status should be successful",
				amount.getStatus(), WithdrawRequestResultStatus.SUCCESSFUL);
	}

	@Test
	public void testEquals() {
		MoneyAmount moneyAmount1 = new MoneyAmount().add(Coin.FIVE, 5).add(
				Coin.TEN, 4);
		MoneyAmount moneyAmount2 = new MoneyAmount().add(Coin.FIVE, 5).add(
				Coin.TEN, 4);

		assertEquals(moneyAmount1, moneyAmount2);

		moneyAmount1.add(Coin.TWENTY, 20);

		assertNotSame(moneyAmount1, moneyAmount2);
	}

	@Test(expected = NullPointerException.class)
	public void addNullTest() {
		new MoneyAmount().add(null, 12);
	}

	@Test
	public void mergeTest() {
		MoneyAmount moneyAmount1 = new MoneyAmount().add(Coin.FIFTY, 1);
		MoneyAmount moneyAmount2 = new MoneyAmount().add(Coin.FIFTY, 2);

		moneyAmount1.add(moneyAmount2);

		Integer expectedFifties = 3;
		assertEquals("Expected must be equal to actual value", expectedFifties,
				moneyAmount1.getCoins().get(Coin.FIFTY));
	}

	@Test
	public void testEqual_MustReturnFalse() {
		MoneyAmount moneyAmount = new MoneyAmount();
		moneyAmount.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
		assertNotSame(moneyAmount, availableCoins);
	}

	@Test
	public void testWithdraw_requestedAmountLessThanAvailable_ExpectCorrectChangeResult() {
		requestedAmount = 235;
		MoneyAmount expected = new MoneyAmount();
		expected.add(Coin.FIVE, 1).add(Coin.TEN, 1).add(Coin.TWENTY, 1)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);

		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals(expected, amount.getChange());
	}

	@Test
	public void testWithdraw_requestedAmountMoreThanAvailable_ExpectInsufficientAmount() {
		requestedAmount = 375;
		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals("Operation status should not be successful",
				amount.getStatus(), WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT);

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

		int expected = availableCoins.sumOfCoins() + moneyAmount.sumOfCoins();
		int actual = (availableCoins.add(moneyAmount)).sumOfCoins();

		assertTrue("Expected sum must be same as actual", expected == actual);
	}

	@After
	public void testMoneyAmount_TearDownObject() {
		availableCoins = null;
	}
}
