package coffee_machine.model.exceptions;

@SuppressWarnings("serial")
public class MoneyAmountException extends RuntimeException {

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
