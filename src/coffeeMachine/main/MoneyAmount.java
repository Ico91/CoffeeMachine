package coffeeMachine.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class MoneyAmount {
	private Map<Coin, Integer> coins;

	public MoneyAmount() {
		coins = new TreeMap<Coin, Integer>();
		this.coins.put(Coin.FIVE, 0);
		this.coins.put(Coin.TEN, 0);
		this.coins.put(Coin.TWENTY, 0);
		this.coins.put(Coin.FIFTY, 0);
		this.coins.put(Coin.LEV, 0);
	}

	public MoneyAmount(Map<Coin, Integer> coins) {
		this.coins = coins;
	}

	public Map<Coin, Integer> getCoins() {
		return this.coins;
	}

	public void add(MoneyAmount coins) {
		for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
			entry.setValue(entry.getValue().intValue()
					+ coins.getCoins().get(entry.getKey()).intValue());
		}
	}

	public void add(Map<Coin, Integer> coins) {
		for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
			entry.setValue(entry.getValue().intValue()
					+ coins.get(entry.getKey()).intValue());
		}
	}

	public int getSumOfCoinsValue() {
		int amount = 0;
		for (Coin c : coins.keySet()) {
			amount += coins.get(c) * c.getValue();
		}
		return amount;
	}

	/***
	 * 
	 * @param amount
	 *            - The requested amount to withdraw from the specified money
	 *            amount object
	 * @return Withdraw object, holding the status of the operation and the
	 *         available coins which can be withdrawn
	 * @throws InvalidWithdrawAmountException
	 */
	public Withdraw withdraw(int amount) {
		Map<Coin, Integer> requestedCoins = new TreeMap<Coin, Integer>();

		if (amount < 0)
			throw new InvalidWithdrawAmountException(
					"Invalid withdraw amount. Cannot be less than zero");

		if (amount == 0)
			return new Withdraw(RequestResultStatus.SUCCESSFUL,
					new MoneyAmount(requestedCoins));

		for (Coin c : getSortedCoinTypes()) {
			if (amount > 0 && (amount - c.getValue() >= 0)) {
				int possibleCoinsToGet = amount / c.getValue();
				int totalAvailFromThisType = coins.get(c);
				if (totalAvailFromThisType >= possibleCoinsToGet) {
					requestedCoins.put(c, possibleCoinsToGet);
					coins.put(c, totalAvailFromThisType - possibleCoinsToGet);
					amount -= c.getValue() * possibleCoinsToGet;
				} else if (totalAvailFromThisType < possibleCoinsToGet) {
					requestedCoins.put(c, totalAvailFromThisType);
					coins.put(c, 0);
					amount -= c.getValue() * totalAvailFromThisType;
				}
			}
		}

		if (amount == 0) {
			return new Withdraw(RequestResultStatus.SUCCESSFUL,
					new MoneyAmount(requestedCoins));
		}

		return new Withdraw(RequestResultStatus.INSUFFICIENT_AMOUNT,
				new MoneyAmount());
	}

	/**
	 * @return List of sorted Coin by types from higher to lower
	 */
	private List<Coin> getSortedCoinTypes() {
		List<Coin> listOfAvailCoinTypes = new ArrayList<Coin>(coins.keySet());
		Collections.reverse(listOfAvailCoinTypes);
		return listOfAvailCoinTypes;
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
}
