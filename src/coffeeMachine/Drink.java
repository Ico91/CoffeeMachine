package coffeeMachine;

/**
 * Holds information for a certain drink type.
 * This includes the name and the price of the drink.
 * @author Hristo
 *
 */
public class Drink implements Comparable<Drink> {

	private String name;
	private int price;

	/**
	 * Creates a new drink. Throws a IllegalArgumentException
	 * if the specified name is empty or null.
	 * @param name - the name of the drink
	 * @param price - the price of the drink.
	 */
	public Drink(String name, int price) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("No name specified");
		}
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
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

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Drink))
			return false;
		Drink other = (Drink) obj;
		return name.equals(other.name);
	}
	
	public String toString() {
		return name + " " + Integer.toString(price);
	}

}
