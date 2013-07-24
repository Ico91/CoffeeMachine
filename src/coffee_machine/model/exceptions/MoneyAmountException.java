package coffee_machine.model.exceptions;

public class MoneyAmountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MoneyAmountException() {
		super();
	}

	public MoneyAmountException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MoneyAmountException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoneyAmountException(String message) {
		super(message);
	}

	public MoneyAmountException(Throwable cause) {
		super(cause);
	}

}
