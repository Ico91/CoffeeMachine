package coffeeMachine.main;

public interface Flow {
	public Flow execute(CoffeeMachineState machine);
}
