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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Withdraw))
			return false;
		Withdraw other = (Withdraw) obj;
		if (change == null) {
			if (other.change != null)
				return false;
		} else if (!change.equals(other.change))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return System.lineSeparator() + "Withdraw result: "
				+ System.lineSeparator() + "Request result status: "
				+ this.status + System.lineSeparator() + "Change: "
				+ this.change.getCoins().toString();
	}

}