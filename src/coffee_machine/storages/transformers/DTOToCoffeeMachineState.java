package coffee_machine.storages.transformers;

import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.DrinksContainer;
import coffee_machine.model.MoneyAmount;
import coffee_machine.storages.transformers.dto.CoffeeMachineDTO;
import coffee_machine.storages.transformers.exceptions.DTOToCoffeeMachineException;
import coffee_machine.storages.transformers.exceptions.DTOToMoneyAmountException;

/**
 * Class used to transform CoffeeMachineDTO to CoffeeMachineState
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class DTOToCoffeeMachineState {

	/**
	 * Constructs new DTOToCoffeeMachineState object
	 */
	public DTOToCoffeeMachineState() {

	}

	/**
	 * Transform CoffeeMachineDTO object to CoffeeMachineState object
	 * 
	 * @param coffeeMachineDTO
	 *            CoffeeMachineDTO object to transform
	 * @return CoffeeMachineState object
	 * @throws DTOToCoffeeMachineException
	 */
	public CoffeeMachineState transform(CoffeeMachineDTO coffeeMachineDTO)
			throws DTOToCoffeeMachineException {
		DTOToDrinksContainerTransformer dtoToDrinksContainer = new DTOToDrinksContainerTransformer();
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		DrinksContainer drinksContainer = dtoToDrinksContainer
				.transform(coffeeMachineDTO);
		try {
			MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
					.transform(coffeeMachineDTO);

			CoffeeMachineState coffeeMachineState = new CoffeeMachineState(
					moneyAmount, drinksContainer);

			return coffeeMachineState;

		} catch (DTOToMoneyAmountException e) {
			throw new DTOToCoffeeMachineException(e.getMessage());
		}
	}
}
