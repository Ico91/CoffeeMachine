package coffeeMachine;

public class OrderedDrinksContainerToDTO {
	
	public DrinksReport transform(DrinksContainer orderedDrinks)
	{
		DrinksReport drinksReport = new DrinksReport();
		DrinksReport.OrderedDrinks orderedDrinksReport = new DrinksReport.OrderedDrinks();
		int total = 0;
		for(Drink drink : orderedDrinks.getDrinks().keySet())
		{
			DrinksReport.OrderedDrinks.Drink orderedDrink = new DrinksReport.OrderedDrinks.Drink();
			orderedDrink.setName(drink.getName());
			orderedDrink.setAmount(orderedDrinks.getDrinks().get(drink).intValue());
			total += orderedDrink.getAmount();
			orderedDrinksReport.getDrink().add(orderedDrink);
		}
		
		drinksReport.setOrderedDrinks(orderedDrinksReport);
		drinksReport.setTotal(total);
		
		return drinksReport;
	}
}
