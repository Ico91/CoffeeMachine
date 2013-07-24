package coffeeMachine.transformers.fromDto;

import coffeeMachine.Coin;
import coffeeMachine.MoneyAmount;
import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.dto.coffeeMachine.TypeCoin;
import coffeeMachine.transformers.fromDto.exceptions.DTOToMoneyAmountException;

/***
 * Class used to transform CoffeeMachineDTO.Money to MoneyAmount object
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class DTOToMoneyAmountTransformer {
	/**
	 * Constructs new DTOToMoneyAmountTransformer object
	 */
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
	public MoneyAmount transform(CoffeeMachineDTO coffeeMachineDto)
			throws DTOToMoneyAmountException {
		MoneyAmount moneyAmount = new MoneyAmount();
		for (coffeeMachine.dto.coffeeMachine.Coin c : coffeeMachineDto
				.getMoney().getCoin()) {
			Coin coin = coinTypeToCoin(c.getType());
			moneyAmount.add(coin, c.getAmount());
		}

		return moneyAmount;
	}

	// Convert coin type to new coin object
	private Coin coinTypeToCoin(
			coffeeMachine.dto.coffeeMachine.TypeCoin coinType)
			throws DTOToMoneyAmountException {
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
		//	Must not be thrown if DTO object is readed from XML file
		throw new DTOToMoneyAmountException("Invalid coin type.");
	}
}
