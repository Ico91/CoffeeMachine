package coffee_machine.storages.transformers;

import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.storages.transformers.dto.CoffeeMachineDTO;

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
		for (coffee_machine.storages.transformers.dto.DrinkDTO d : coffeeMachineDTO
				.getDrinks().getDrink()) {
			Drink drink = new Drink(d.getName(), d.getPrice());
			drinksContainer.add(drink, d.getAmount());
		}

		return drinksContainer;
	}
}
