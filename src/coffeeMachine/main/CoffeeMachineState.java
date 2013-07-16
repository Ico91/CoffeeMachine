package coffeeMachine.main;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

=======
/**
 * Holds the current state of the coffee machine. Includes the lists of drinks
 * and coins which are currently available in the machine.
 * 
 * @author Hristo
 *
 */
>>>>>>> be9cab466479a64cd970f4c69cdb0f75f76c5635
public class CoffeeMachineState {
	private MoneyAmount coins;
	private DrinksContainer drinks;
	
	public CoffeeMachineState(MoneyAmount coins, DrinksContainer drinks) {
		this.coins = coins;
		this.drinks = drinks;
	}

	public MoneyAmount getCoins() {
		return coins;
	}

	public void setCoins(MoneyAmount coins) {
		this.coins = coins;
	}

	public DrinksContainer getDrinks() {
		return drinks;
	}

	public void setDrinks(DrinksContainer drinks) {
		this.drinks = drinks;
	}
	
	public List<Drink> getFiltratedDrinks(){  // by Andrey
		List<Drink> drinksList=new ArrayList<Drink>();
		for(Map.Entry<Drink, Integer> entry: drinks.getDrinks().entrySet()){
			if(entry.getValue()!=0){
				drinksList.add(entry.getKey());
			}
		
		}
		return drinksList;
	}
	
}
