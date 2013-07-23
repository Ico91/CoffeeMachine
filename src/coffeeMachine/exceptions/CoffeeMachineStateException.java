package coffeeMachine.exceptions;

@SuppressWarnings("serial")
public class CoffeeMachineStateException extends RuntimeException {
	public CoffeeMachineStateException(String message) {
		super(message);
	}
}
