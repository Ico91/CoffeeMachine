package coffeeMachine.transformers.fromDto.exceptions;

@SuppressWarnings("serial")
public class DTOToMoneyAmountException extends Exception {
	public DTOToMoneyAmountException(String message) {
		super(message);
	}
	
	public DTOToMoneyAmountException() {
		super("DTO to MoneyAmount exception");
	}
}
