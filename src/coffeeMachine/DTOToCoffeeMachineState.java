package coffeeMachine;

import coffeeMachine.exceptions.DTOToCoffeeMachineStateException;
import coffeeMachine.exceptions.DTOToMoneyAmountException;
import coffeeMachine.DTO.coffeeMachineDTO.CoffeeMachineDTO;
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

		} catch (DTOToMoneyAmountException e) {
			throw new DTOToCoffeeMachineStateException(e.getMessage());
		}
	}
}
