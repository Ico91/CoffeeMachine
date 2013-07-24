package coffeeMachine.transformers.fromDto;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.DrinksContainer;
import coffeeMachine.MoneyAmount;
import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.exceptions.DTOToCoffeeMachineException;
import coffeeMachine.transformers.fromDto.exceptions.DTOToMoneyAmountException;

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

			// TODO: Validations if needed
			return coffeeMachineState;

		} catch (DTOToMoneyAmountException e) {
			throw new DTOToCoffeeMachineException(e.getMessage());
		}
	}
}
