package coffeeMachine.exceptions;

@SuppressWarnings("serial")
public class CoffeeMachineStateException extends Exception {
	public CoffeeMachineStateException(String message) {
		super(message);
	}
}
