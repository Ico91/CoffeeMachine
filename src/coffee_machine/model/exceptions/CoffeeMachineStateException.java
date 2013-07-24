package coffee_machine.model.exceptions;

public class CoffeeMachineStateException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoffeeMachineStateException(String message) {
		super(message);
	}
}
