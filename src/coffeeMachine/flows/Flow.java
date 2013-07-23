package coffeeMachine.flows;

import coffeeMachine.CoffeeMachineState;

public interface Flow {
	public Flow execute(CoffeeMachineState machine);
}
