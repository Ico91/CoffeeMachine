package coffee_machine.model.exceptions;

@SuppressWarnings("serial")
public class InvalidWithdrawAmountException extends RuntimeException {
	public InvalidWithdrawAmountException(String message)
	{
		super(message);
	}
}
