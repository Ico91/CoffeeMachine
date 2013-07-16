package coffeeMachine.main;

import java.util.Map;
import java.util.TreeMap;

public class MoneyAmount {
	private Map<Coin, Integer> coins;
	
	public MoneyAmount()
	{
		coins = new TreeMap<Coin, Integer>();
		this.coins.put(Coin.FIVE, 0);
		this.coins.put(Coin.TEN, 0);
		this.coins.put(Coin.TWENTY, 0);
		this.coins.put(Coin.FIFTY, 0);
		this.coins.put(Coin.LEV, 0);
	}
	
	public MoneyAmount(Map<Coin, Integer> coins)
	{
		this.coins = coins;
	}
	
	public Map<Coin, Integer> getCoins()
	{
		return this.coins;
	}
	
	public void add(MoneyAmount coins)
	{
		for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
			entry.setValue(entry.getValue().intValue() + coins.getCoins().get(entry.getKey()).intValue());
		}
	}
	
	public void add(Map<Coin, Integer> coins)
	{
		for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
			entry.setValue(entry.getValue().intValue() + coins.get(entry.getKey()).intValue());
		}
	}
	
	public void add(Coin coin)
	{
		int count = coins.get(coin).intValue();
		coins.put(coin, Integer.valueOf(++count));
	}
	
	public int getSumOfCoinsValue() {
		int amount = 0;
		for(Coin c : coins.keySet()) {
			amount += coins.get(c) * c.getValue();
		}
		return amount;
	}
	
	/***
	 * 
	 * @param amount - The requested amount to withdraw from the specified money amount object
	 * @return Withdraw object, holding the status of the operation and the available coins which can be withdrawn
	 * @throws InvalidWithdrawAmountException
	 */
	public Withdraw withdraw(int amount)
	{
		MoneyAmount requestedCoins = new MoneyAmount();
		boolean isAmountInsufficient = false;
		if(amount < 0)
			throw new InvalidWithdrawAmountException("Invalid withdraw amount. Cannot be less than zero");
		
		if(amount == 0)
			return new Withdraw(RequestResultStatus.SUCCESSFUL, requestedCoins);
		
		if(amount >= 100)
		{
			int requestedCoinsCount = amount / 100;
			int availableCoinsCount = this.coins.get(Coin.LEV).intValue();
			requestedCoins.getCoins().put(Coin.LEV, requestedCoinsCount);
			if(availableCoinsCount < requestedCoinsCount)
			{
				requestedCoins.getCoins().put(Coin.LEV, availableCoinsCount);
				amount -= availableCoinsCount * 100;
			}
			else
				amount %= 100;
		}
		
		if(amount >= 50)
		{
			int requestedCoinsCount = amount / 50;
			int availableCoinsCount = this.coins.get(Coin.FIFTY).intValue();
			requestedCoins.getCoins().put(Coin.FIFTY, requestedCoinsCount);
			if(availableCoinsCount <= requestedCoinsCount)
			{
				requestedCoins.getCoins().put(Coin.FIFTY, availableCoinsCount);
				amount -= availableCoinsCount * 50;
			}
			else
				amount %= 50;
		}
		
		if(amount >= 20)
		{
			int requestedCoinsCount = amount / 20;
			int availableCoinsCount = this.coins.get(Coin.TWENTY).intValue();
			requestedCoins.getCoins().put(Coin.TWENTY, requestedCoinsCount);
			if(availableCoinsCount <= requestedCoinsCount)
			{
				requestedCoins.getCoins().put(Coin.TWENTY, availableCoinsCount);
				amount -= availableCoinsCount * 20;
			}
			else
				amount %= 20;
		}
		
		if(amount >= 10)
		{
			int requestedCoinsCount = amount / 10;
			int availableCoinsCount = this.coins.get(Coin.TEN).intValue();
			requestedCoins.getCoins().put(Coin.TEN, requestedCoinsCount);
			if(availableCoinsCount <= requestedCoinsCount)
			{
				requestedCoins.getCoins().put(Coin.TEN, availableCoinsCount);
				amount -= availableCoinsCount * 10;
			}
			else
				amount %= 10;
		}
		
		if(amount >= 5)
		{
			int requestedCoinsCount = amount / 5;
			int availableCoinsCount = this.coins.get(Coin.FIVE).intValue();
			requestedCoins.getCoins().put(Coin.FIVE, requestedCoinsCount);
			if(availableCoinsCount < requestedCoinsCount)
			{
				requestedCoins.getCoins().put(Coin.FIVE, availableCoinsCount);
				amount -= availableCoinsCount * 5;
				isAmountInsufficient = true;
			}
		}
		
		if(isAmountInsufficient)
			return new Withdraw(RequestResultStatus.INSUFFICIENT_AMOUNT, requestedCoins);
		
		return new Withdraw(RequestResultStatus.SUCCESSFUL, requestedCoins);
	}
	
	@Override
	public String toString()
	{
		String output = System.lineSeparator();
		for (Map.Entry<Coin, Integer> entry : this.coins.entrySet()) {
			Coin key = entry.getKey();
			Integer value = entry.getValue();

			output += key + " - " + value.intValue() + System.lineSeparator();
		}
		
		return output;
	}
}
