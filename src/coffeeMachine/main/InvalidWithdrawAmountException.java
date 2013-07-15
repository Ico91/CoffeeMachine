package coffeeMachine.main;

@SuppressWarnings("serial")
public class InvalidWithdrawAmountException extends RuntimeException {
	public InvalidWithdrawAmountException(String message)
	{
		super(message);
	}
}
