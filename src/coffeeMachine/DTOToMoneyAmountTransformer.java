package coffeeMachine;

import coffeeMachine.exceptions.DTOToMoneyAmountException;
import coffeeMachine.DTO.coffeeMachineDTO.CoffeeMachineDTO;
import coffeeMachine.DTO.coffeeMachineDTO.TypeCoin;

/***
 * Class used to transform CoffeeMachineDTO.Money to MoneyAmount object
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class DTOToMoneyAmountTransformer {

	public DTOToMoneyAmountTransformer() {

	}

	/***
	 * Transform CoffeeMachineDTO.Money to MoneyAmount object
	 * 
	 * @param coffeeMachineDto
	 *            CoffeeMachineDTO object
	 * @return MoneyAmount object
	 * @throws DTOToMoneyAmountException
	 */
	public MoneyAmount transform(CoffeeMachineDTO coffeeMachineDto) {
		MoneyAmount moneyAmount = new MoneyAmount();
		for (coffeeMachine.DTO.coffeeMachineDTO.Coin c : coffeeMachineDto
				.getMoney().getCoin()) {
			Coin coin = coinTypeToCoin(c.getType());
			if (coinAlreadyExist(coin, moneyAmount) == false) {
				moneyAmount.add(coin, c.getAmount());
			} else
				throw new DTOToMoneyAmountException(
						"Duplicated values for coin of type " + c.getType());
		}

		return moneyAmount;
	}

	/***
	 * Check if the specified coin is already exist in MoneyAmount
	 * 
	 * @param coin
	 *            Coin to check
	 * @param moneyAmount
	 * @return True if the coin already exist, false otherwise
	 */
	private boolean coinAlreadyExist(Coin coin, MoneyAmount moneyAmount) {
		// TODO: ??? Based on exception ????
		try {
			moneyAmount.getCoin(coin);
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	// Convert coin type to new coin object
	private Coin coinTypeToCoin(coffeeMachine.DTO.coffeeMachineDTO.TypeCoin coinType) {
		if (coinType == TypeCoin.FIVE)
			return Coin.FIVE;
		if (coinType == TypeCoin.TEN)
			return Coin.TEN;
		if (coinType == TypeCoin.TWENTY)
			return Coin.TWENTY;
		if (coinType == TypeCoin.FIFTY)
			return Coin.FIFTY;
		if (coinType == TypeCoin.LEV)
			return Coin.LEV;

		throw new DTOToMoneyAmountException("Invalid coin type.");
	}
}
