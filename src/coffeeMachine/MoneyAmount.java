package coffeeMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Objects;

import coffeeMachine.Withdraw.WithdrawRequestResultStatus;
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
		coins = new HashMap<Coin, Integer>();
		this.coins.put(Coin.FIVE, 0);
		this.coins.put(Coin.TEN, 0);
		this.coins.put(Coin.TWENTY, 0);
		this.coins.put(Coin.FIFTY, 0);
		this.coins.put(Coin.LEV, 0);

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
	public int sumOfCoins() {
		int amount = 0;
		for (Coin c : coins.keySet()) {
			amount += coins.get(c) * c.getValue();
		}
		return amount;
	}
	
	/***
	 * Return amount of coins from specified type
	 * @param coin Type of coin
	 * @return Available coins of this type
	 */
	public int getCoin(Coin coin) {
		return this.coins.get(coin);
	}
	
	/***
	 * Add coins to current available coins
	 * 
	 * @param coin
	 *            Type of coin
	 * @param count
	 *            Count of coins to add
	 */
	public MoneyAmount add(Coin coin, int count) {
		Objects.requireNonNull(coin, "Coin cannot be null");
		if (count < 0)
			throw new IllegalArgumentException(
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
	public MoneyAmount add(MoneyAmount moneyAmount) {
		for (Coin c : this.coins.keySet()) {
			int totalCoinsToAdd = this.coins.get(c)
					+ moneyAmount.getCoins().get(c);
			this.coins.put(c, totalCoinsToAdd);
		}

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
			return new Withdraw(WithdrawRequestResultStatus.SUCCESSFUL,
					requestedCoins);

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
			return new Withdraw(WithdrawRequestResultStatus.SUCCESSFUL,
					requestedCoins);
		}

		return new Withdraw(WithdrawRequestResultStatus.INSUFFICIENT_AMOUNT,
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

		if (!coins.equals(other.coins))
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
		Collections.sort(listOfAvailCoinTypes);
		Collections.reverse(listOfAvailCoinTypes);
		return listOfAvailCoinTypes;
	}
}
