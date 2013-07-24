package coffee_machine.menu;
@SuppressWarnings("serial")
public class InvalidCommandException extends Exception {
	public InvalidCommandException(String message) {
		super(message);
	}

}
