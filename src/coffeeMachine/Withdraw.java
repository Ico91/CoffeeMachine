package coffeeMachine;

public class Withdraw {
	private RequestResultStatus status;
	private MoneyAmount change;

	public Withdraw(RequestResultStatus status, MoneyAmount change) {
		this.status = status;
		this.change = change;
	}

	public RequestResultStatus getStatus() {
		return status;
	}

	public MoneyAmount getChange() {
		return change;
	}

}