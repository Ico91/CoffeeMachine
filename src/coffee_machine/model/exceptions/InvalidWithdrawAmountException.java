package coffee_machine.model.exceptions;

public class InvalidWithdrawAmountException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidWithdrawAmountException(String message)
	{
		super(message);
	}
}
