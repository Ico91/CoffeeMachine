package coffeeMachine.main;

public class Drink implements Comparable<Drink> {
	private String name;
	private int price;

	public Drink(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int compareTo(Drink drink) {
		int result;
		if (this.name.equalsIgnoreCase(drink.name)
				&& this.price == drink.getPrice()) {
			result = 0;
		} else if (this.price > drink.price) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}
}