package coffee_machine.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffee_machine.model.Coin;
import coffee_machine.model.MoneyAmount;
import coffee_machine.model.Withdraw;
import coffee_machine.model.Withdraw.WithdrawRequestResultStatus;
import coffee_machine.model.exceptions.InvalidWithdrawAmountException;
import coffee_machine.model.exceptions.MoneyAmountException;

public class MoneyAmountTests {

	private MoneyAmount availableCoins;
	private int requestedAmount;

	@Before
	public void setUpObject() {
		availableCoins = new MoneyAmount().add(Coin.FIVE, 5).add(Coin.TEN, 4)
				.add(Coin.TWENTY, 3).add(Coin.FIFTY, 2).add(Coin.LEV, 1);
	}
	
	@Test
	public void getCoinsForSuccess() {
		int expected = 5;
		int actual = availableCoins.getCoin(Coin.FIVE);
		assertTrue("Actual coins must be 5", expected == actual);
	}
	
	@Test(expected=MoneyAmountException.class)
	public void getCoinsWithHigherValue() {
		this.availableCoins.getCoins(Coin.LEV, 100);
	}
	
	@Test
	public void withdrawWithZeroAmount() {
		Withdraw expected = new Withdraw(WithdrawRequestResultStatus.SUCCESSFUL, new MoneyAmount());
		assertEquals(expected, this.availableCoins.withdraw(0));
	}
	
	@Test
	public void getCoinsForFail() {
		int expected = 5;
		int actual = availableCoins.getCoin(Coin.FIFTY);
		
		assertFalse("Expected value must be different than actual", expected == actual);
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
	public void testGetSumOfCoinsValue() {
		assertEquals(325, availableCoins.sumOfCoins());
	}

	@Test
	public void testGetSumOfCoinsValueAfterWithdraw() {
		int before = availableCoins.sumOfCoins();

		Withdraw withdraw = availableCoins.withdraw(175);
		assertEquals(WithdrawRequestResultStatus.SUCCESSFUL, withdraw.getStatus());

		int actual = availableCoins.sumOfCoins();
		int expected = before - 175;
		assertTrue("Expected sum must be same as actual( " + expected + " "
				+ actual + ")", expected == actual);
	}

	@Test
	public void testWithdrawLessMoneyThanAvailable() {
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
	public void testEqualMustReturnFalse() {
		MoneyAmount moneyAmount = new MoneyAmount();
		moneyAmount.add(Coin.FIVE, 5).add(Coin.TEN, 4).add(Coin.TWENTY, 3)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);
		assertNotSame(moneyAmount, availableCoins);
	}

	@Test
	public void testCorrectChangeResult() {
		requestedAmount = 235;
		MoneyAmount expected = new MoneyAmount();
		expected.add(Coin.FIVE, 1).add(Coin.TEN, 1).add(Coin.TWENTY, 1)
				.add(Coin.FIFTY, 2).add(Coin.LEV, 1);

		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals(expected, amount.getChange());
	}

	@Test
	public void testWithdrawMoreMoneyThanAvailable() {
		requestedAmount = 375;
		Withdraw amount = availableCoins.withdraw(requestedAmount);
		assertEquals("Operation status should not be successful",
				amount.getStatus(), WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT);

	}

	@Test(expected = InvalidWithdrawAmountException.class)
	public void testWithdrawAmountLessThanZero()
			throws InvalidWithdrawAmountException {
		requestedAmount = -50;
		availableCoins.withdraw(requestedAmount);
	}

	@Test
	public void testAddCoins() {
		MoneyAmount moneyAmount = new MoneyAmount();
		availableCoins = new MoneyAmount();
		availableCoins.add(Coin.FIVE, 10).add(Coin.TEN, 9).add(Coin.TWENTY, 8)
				.add(Coin.FIFTY, 7).add(Coin.LEV, 6);

		int expected = availableCoins.sumOfCoins() + moneyAmount.sumOfCoins();
		int actual = (availableCoins.add(moneyAmount)).sumOfCoins();

		assertTrue("Expected sum must be same as actual", expected == actual);
	}

	@After
	public void tearDownObject() {
		availableCoins = null;
	}
}
