package coffeeMachine.transformers.fromDto;

import coffeeMachine.Drink;
import coffeeMachine.DrinksContainer;
import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;

/***
 * Class used to transform CoffeeMachine.Drinks to DrinksContainer
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class DTOToDrinksContainerTransformer {

	/***
	 * Creates new DTOToDrinksContainerTransformer object
	 */
	public DTOToDrinksContainerTransformer() {

	}

	/***
	 * Transform CoffeeMachineDTO.Drinks to DrinksContainer
	 * 
	 * @param coffeeMachineDTO
	 *            DTO Object
	 * @return DrinksContainer with all drinks loaded from CoffeeMachineStorage
	 */
	public DrinksContainer transform(CoffeeMachineDTO coffeeMachineDTO) {
		DrinksContainer drinksContainer = new DrinksContainer();
		for (coffeeMachine.dto.coffeeMachine.Drink d : coffeeMachineDTO
				.getDrinks().getDrink()) {
			Drink drink = new Drink(d.getName(), d.getPrice());
			drinksContainer.add(drink, d.getAmount());
		}

		return drinksContainer;
	}
}
