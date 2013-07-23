package coffeeMachine.exceptions;

@SuppressWarnings("serial")
public class DTOToMoneyAmountException extends RuntimeException {
	public DTOToMoneyAmountException(String message) {
		super(message);
	}
	
	public DTOToMoneyAmountException() {
		super("DTO to MoneyAmount exception");
	}
}
