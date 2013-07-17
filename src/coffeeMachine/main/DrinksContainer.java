package coffeeMachine.main;

import java.util.Map;

public class DrinksContainer {
	private Map<Drink, Integer> drinks;

	public DrinksContainer(Map<Drink, Integer> drinks) {
		this.drinks = drinks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drinks == null) ? 0 : drinks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DrinksContainer))
			return false;
		DrinksContainer other = (DrinksContainer) obj;
		if (drinks == null) {
			if (other.drinks != null)
				return false;
		} else if (!drinks.equals(other.drinks))
			return false;
		return true;
	}

	public Map<Drink, Integer> getDrinks() {
		return drinks;
	}

	public void setDrinks(Map<Drink, Integer> drinks) {
		this.drinks = drinks;
	}
	
	
}
