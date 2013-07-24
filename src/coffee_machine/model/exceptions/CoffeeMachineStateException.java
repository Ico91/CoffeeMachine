package coffee_machine.model.exceptions;

@SuppressWarnings("serial")
public class CoffeeMachineStateException extends Exception {
	public CoffeeMachineStateException(String message) {
		super(message);
	}
}
