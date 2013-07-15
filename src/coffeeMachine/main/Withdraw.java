package coffeeMachine.main;

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

	public void setStatus(RequestResultStatus status) {
		this.status = status;
	}

	public void setChange(MoneyAmount change) {
		this.change = change;
	}
}