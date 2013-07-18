package coffeeMachine;

public interface Flow {
	public Flow execute(CoffeeMachineState machine);
}
