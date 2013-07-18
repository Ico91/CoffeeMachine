package coffeeMachine;


public class Drink implements Comparable<Drink> {
	
	private String name;
	private int price;

	public Drink() {
		name = "";
		price = 0;
	}
	
	public String toString(){
		return name+" "+Integer.toString(price);
	}

	public Drink(String name, int price) {
		if ( name == null || name.isEmpty() ) {
			throw new IllegalArgumentException( "No name specified" );
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

}
