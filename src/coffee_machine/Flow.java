package coffee_machine;

import coffee_machine.model.CoffeeMachineState;

public interface Flow {
	public Flow execute(CoffeeMachineState machine);
}
