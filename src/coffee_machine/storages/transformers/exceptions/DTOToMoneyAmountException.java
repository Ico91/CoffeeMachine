package coffee_machine.storages.transformers.exceptions;

public class DTOToMoneyAmountException extends Exception {
	private static final long serialVersionUID = 1L;

	public DTOToMoneyAmountException(String message) {
		super(message);
	}
	
	public DTOToMoneyAmountException() {
		super("DTO to MoneyAmount exception");
	}
}
