package coffeeMachine;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

import coffeeMachine.exceptions.InvalidWithdrawAmountException;
import coffeeMachine.exceptions.MoneyAmountException;

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
	//TODO bad idea - depends on all available coin types
	public MoneyAmount(int coinsOfFive, int coinsOfTen, int coinsOfTwenty,
			int coinsOfFifty, int coinsOfLev) {
		if (coinsOfFive < 0 || coinsOfTen < 0 || coinsOfTwenty < 0
				|| coinsOfFifty < 0 || coinsOfLev < 0) {
			throw new InvalidParameterException(
					"The amount of coins cannot be negative number");
		}

		//TODO why TreeMap is used ?
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
	//TODO name too long
	public int getSumOfCoinsValue() {
		int amount = 0;
		for (Coin c : coins.keySet()) {
			amount += coins.get(c) * c.getValue();
		}
		return amount;
	}

	/***
	 *  Add coins to current available coins
	 * @param coin Type of coin
	 * @param count Count of coins to add
	 */
		public MoneyAmount add(Coin coin, int count) {
			if (count < 0)
				//TODO check the javadoc for this class and decide whether to use it
				throw new InvalidParameterException(
						"Count of coins cannot be negative number!");
			int totalCount = this.coins.get(coin) + count;
			this.coins.put(coin, totalCount);
			
			return this;
		}
	
	/***
	 * Merge the current MoneyAmount with specified MoneyAmount object and
	 * returns new money amount object
	 * 
	 * @param moneyAmount
	 *            MoneyAmount object to merge with
	 * @return Result of merging
	 */
	//TODO as this class is not immutable such behavior could be surprising.
	//why not update this object's state ?
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
				requestedCoins);
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
		
		//TODO could coins ever be null ?
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
