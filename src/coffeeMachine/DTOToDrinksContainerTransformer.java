package coffeeMachine;

import coffeeMachine.exceptions.DTOToDrinksContainerException;

/***
 * Class used to transform CoffeeMachine.Drinks to DrinksContainer
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
	 * @param coffeeMachineDTO DTO Object
	 * @return DrinksContainer with all drinks loaded from CoffeeMachineStorage
	 */
	public DrinksContainer transform(CoffeeMachineDTO coffeeMachineDTO) {
		DrinksContainer drinksContainer = new DrinksContainer();
		for (CoffeeMachineDTO.Drinks.Drink d : coffeeMachineDTO.getDrinks()
				.getDrink()) {
			Drink drink = new Drink(d.getName(), d.getPrice());
			if(drinkAlreadyExist(drink, drinksContainer) == false) {
				drinksContainer.add(drink, d.getAmount());
			}
			else throw new DTOToDrinksContainerException("Duplicated values for drink " + d.getName());
		}

		return drinksContainer;
	}

	//	Check if drink is already added to drinksContainer
	private boolean drinkAlreadyExist(Drink drink, DrinksContainer drinksContainer) {
		try {
			drinksContainer.getDrinkQuantity(drink);
			return true;
		}
		catch(NullPointerException e) {
			return false;
		}
	}
}
