package coffeeMachine;

import coffeeMachine.exceptions.CoffeeMachineStateException;
import coffeeMachine.flows.DrinkListFlow;
import coffeeMachine.flows.Flow;
import coffeeMachine.storages.CoffeeMachineStorage;

public class Main {

	public static void main(String[] args) {
		CoffeeMachineStorage coffeeMachineStorage = new CoffeeMachineStorage();
		CoffeeMachineState coffeeMachineState;
		
		try {
			coffeeMachineState = coffeeMachineStorage.load(
					"resources/coffeeMachine.xml", "src/CoffeeMachineDTO.xsd");
			Flow flow = new DrinkListFlow();
			while (true) {
				flow = flow.execute(coffeeMachineState);
			}
		} catch (CoffeeMachineStateException e) {
			System.out.println(e.getMessage());
			System.out.println("Machine cannot start!");
		}
	}
}
