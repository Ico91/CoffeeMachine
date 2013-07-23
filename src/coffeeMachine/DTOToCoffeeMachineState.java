package coffeeMachine;

import coffeeMachine.exceptions.DTOToCoffeeMachineStateException;
import coffeeMachine.exceptions.DTOToDrinksContainerException;
import coffeeMachine.exceptions.DTOToMoneyAmountException;

public class DTOToCoffeeMachineState {
	public DTOToCoffeeMachineState() {

	}

	public CoffeeMachineState transform(CoffeeMachineDTO coffeeMachineDTO) throws DTOToCoffeeMachineStateException {
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

		} catch (DTOToDrinksContainerException | DTOToMoneyAmountException e) {
			throw new DTOToCoffeeMachineStateException(e.getMessage());
		}
	}
}
