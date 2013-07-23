package coffeeMachine.transformers.fromDto;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.DrinksContainer;
import coffeeMachine.MoneyAmount;
import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.exceptions.DTOToCoffeeMachineException;
import coffeeMachine.transformers.fromDto.exceptions.DTOToMoneyAmountException;
public class DTOToCoffeeMachineState {
	public DTOToCoffeeMachineState() {

	}

	public CoffeeMachineState transform(CoffeeMachineDTO coffeeMachineDTO) throws DTOToCoffeeMachineException {
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
