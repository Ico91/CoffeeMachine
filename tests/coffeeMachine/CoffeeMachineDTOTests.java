package coffeeMachine;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoffeeMachineDTOTests {
	CoffeeMachineDTO coffeeMachineDto = new CoffeeMachineDTO();

	@Test
	public void UnmarshallingDrinksContainer() {
		DrinksContainer dinksContainer = new DrinksContainer();
		dinksContainer.add(new Drink("Coffee", 30), 10);
		System.out.println("Hand insert value: " + dinksContainer.toString());

		coffeeMachineDto.UnmarshallingDrinks();
		assertEquals(coffeeMachineDto.getDrinksContainer(), dinksContainer);
	}

}
