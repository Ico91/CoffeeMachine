package coffeeMachine.main;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

/***
 * Class which contains a collection of coins and provides options to add and
 * withdraw coins
 */

public class MoneyAmount {
	private Map<Coin, Integer> coins;

	/***
	 * Constructs a new MoneyAmount object
	 */
	public MoneyAmount() {
		this(0, 0, 0, 0, 0);
	}

	/***
	 * Constructs a new MoneyAmount object with specified amount of coins of
	 * every type
	 * 
	 * @param coinsOfFive
	 *            Amount of coins of five
	 * @param coinsOfTen
	 *            Amount of coins of ten
	 * @param coinsOfTwenty
	 *            Amount of coins of twenty
	 * @param coinsOfFifty
	 *            Amount of coins of fifty
	 * @param coinsOfLev
	 *            Amount of coins of lev
	 */
	public MoneyAmount(int coinsOfFive, int coinsOfTen, int coinsOfTwenty,
			int coinsOfFifty, int coinsOfLev) {
		if (coinsOfFive < 0 || coinsOfTen < 0 || coinsOfTwenty < 0
				|| coinsOfFifty < 0 || coinsOfLev < 0) {
			throw new InvalidParameterException(
					"The amount of coins cannot be negative number");
		}

		coins = new TreeMap<Coin, Integer>();
		this.coins.put(Coin.FIVE, coinsOfFive);
		this.coins.put(Coin.TEN, coinsOfTen);
		this.coins.put(Coin.TWENTY, coinsOfTwenty);
		this.coins.put(Coin.FIFTY, coinsOfFifty);
		this.coins.put(Coin.LEV, coinsOfLev);
	}

	/***
	 * Get all available coins
	 * 
	 * @return Map<Coin, Integer> containing all coins
	 */
	public Map<Coin, Integer> getCoins() {
		return this.coins;
	}

	/***
	 * Accumulates the sum of coins
	 * 
	 * @return Accumulated sum
	 */
	public int getSumOfCoinsValue() {
		int amount = 0;
		for (Coin c : coins.keySet()) {
			amount += coins.get(c) * c.getValue();
		}
		return amount;
	}

	/***
	 * Merge the current MoneyAmount with specified MoneyAmount object and
	 * returns new money amount object
	 * 
	 * @param moneyAmount
	 *            MoneyAmount object to merge with
	 * @return Result of merging
	 */
	public MoneyAmount mergeWith(MoneyAmount moneyAmount) {
		MoneyAmount moneyAmountToReturn = new MoneyAmount();

		for (Coin c : this.coins.keySet()) {
			int totalCoinsToAdd = this.coins.get(c)
					+ moneyAmount.getCoins().get(c);
			moneyAmountToReturn.add(c, totalCoinsToAdd);
		}

		return moneyAmountToReturn;
	}

	/***
	 * Add the passed count of coins of type five to currently available amount
	 * 
	 * @param amount
	 *            Amount of coins to add
	 * @return MoneyAmount object after addition
	 */
	public MoneyAmount addCointsOfFive(int amount) {
		add(Coin.FIVE, amount);
		return this;
	}

	/***
	 * Add the passed count of coins of type ten to currently available amount
	 * 
	 * @param amount
	 *            Amount of coins to add
	 * @return MoneyAmount object after addition
	 */
	public MoneyAmount addCointsOfTen(int count) {
		add(Coin.TEN, count);
		return this;
	}

	/***
	 * Add the passed count of coins of type twenty to currently available
	 * amount
	 * 
	 * @param amount
	 *            Amount of coins to add
	 * @return MoneyAmount object after addition
	 */
	public MoneyAmount addCointsOfTwenty(int count) {
		add(Coin.TWENTY, count);
		return this;
	}

	/***
	 * Add the passed count of coins of type fifty to currently available amount
	 * 
	 * @param amount
	 *            Amount of coins to add
	 * @return MoneyAmount object after addition
	 */
	public MoneyAmount addCointsOfFifty(int count) {
		add(Coin.FIFTY, count);
		return this;
	}

	/***
	 * Add the passed count of coins of type lev to currently available amount
	 * 
	 * @param amount
	 *            Amount of coins to add
	 * @return MoneyAmount object after addition
	 */
	public MoneyAmount addCointsOfLev(int count) {
		add(Coin.LEV, count);
		return this;
	}

	/***
	 * 
	 * @param amount
	 *            The requested amount to withdraw from the specified money
	 *            amount object
	 * @return Withdraw object, holding the status of the operation and the
	 *         available coins which can be withdrawn
	 * @throws InvalidWithdrawAmountException
	 */
	public Withdraw withdraw(int amount) {
		MoneyAmount requestedCoins = new MoneyAmount();

		if (amount < 0)
			throw new InvalidWithdrawAmountException(
					"Invalid withdraw amount. Cannot be less than zero");

		if (amount == 0)
			return new Withdraw(RequestResultStatus.SUCCESSFUL, requestedCoins);

		for (Coin c : getSortedCoinTypes()) {
			if (amount > 0 && (amount - c.getValue() >= 0)) {
				int possibleCoinsToGet = amount / c.getValue();
				int totalAvailFromThisType = coins.get(c);

				if (totalAvailFromThisType >= possibleCoinsToGet) {
					requestedCoins.add(c, possibleCoinsToGet);
					this.getCoins(c, possibleCoinsToGet);
					amount -= c.getValue() * possibleCoinsToGet;
				} else if (totalAvailFromThisType < possibleCoinsToGet) {
					requestedCoins.add(c, totalAvailFromThisType);
					this.getCoins(c, totalAvailFromThisType);
					amount -= c.getValue() * totalAvailFromThisType;
				}
			}
		}

		if (amount == 0) {
			return new Withdraw(RequestResultStatus.SUCCESSFUL, requestedCoins);
		}

		return new Withdraw(RequestResultStatus.INSUFFICIENT_AMOUNT,
				new MoneyAmount());
	}

	@Override
	public int hashCode() {
		return coins.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyAmount other = (MoneyAmount) obj;
		if (coins == null) {
			if (other.coins != null)
				return false;
		} else if (!coins.equals(other.coins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String output = System.lineSeparator();
		for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
			Coin key = entry.getKey();
			Integer value = entry.getValue();

			output += key + " - " + value.intValue() + System.lineSeparator();
		}

		return output;
	}

	// Add coins to current available coins
	private void add(Coin coin, int count) {
		if (count < 0)
			throw new InvalidParameterException(
					"Count of coins cannot be negative number!");
		int totalCount = this.coins.get(coin) + count;
		this.coins.put(coin, totalCount);
	}

	// Get coins from current available coins
	private void getCoins(Coin coin, int count) {
		int availableCoins = this.coins.get(coin);
		if (availableCoins < count)
			throw new MoneyAmountException(
					"Cannot get more coins than the available!");
		int totalCount = availableCoins - count;
		this.coins.put(coin, totalCount);
	}

	// Returns list of coins in descending amount order
	private List<Coin> getSortedCoinTypes() {
		List<Coin> listOfAvailCoinTypes = new ArrayList<Coin>(coins.keySet());
		Collections.reverse(listOfAvailCoinTypes);
		return listOfAvailCoinTypes;
	}
}
