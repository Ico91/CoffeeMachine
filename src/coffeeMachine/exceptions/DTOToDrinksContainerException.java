package coffeeMachine.exceptions;

@SuppressWarnings("serial")
public class DTOToDrinksContainerException extends RuntimeException {
	public DTOToDrinksContainerException(String message) {
		super(message);
	}
}