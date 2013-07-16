package coffeeMachine.main;

public enum Coin {
	FIVE(5), TEN(10), TWENTY(20), FIFTY(50), LEV(100);
	private int value;
	
	private Coin(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
