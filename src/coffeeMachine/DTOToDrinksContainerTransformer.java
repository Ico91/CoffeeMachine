package coffeeMachine;

import coffeeMachine.DTO.coffeeMachineDTO.CoffeeMachineDTO;

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
		for (coffeeMachine.DTO.coffeeMachineDTO.Drink d : coffeeMachineDTO
				.getDrinks().getDrink()) {
			Drink drink = new Drink(d.getName(), d.getPrice());
			drinksContainer.add(drink, d.getAmount());
		}

		return drinksContainer;
	}
}
