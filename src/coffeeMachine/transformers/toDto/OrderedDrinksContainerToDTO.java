package coffeeMachine.transformers.toDto;

import coffeeMachine.Drink;
import coffeeMachine.DrinksContainer;
import coffeeMachine.dto.reports.DrinksReportDTO;

public class OrderedDrinksContainerToDTO {
	
	public DrinksReportDTO transform(DrinksContainer orderedDrinks)
	{
		DrinksReportDTO drinksReport = new DrinksReportDTO();
		DrinksReportDTO.OrderedDrinks orderedDrinksReport = new DrinksReportDTO.OrderedDrinks();
		int total = 0;
		for(Drink drink : orderedDrinks.getDrinks().keySet())
		{
			DrinksReportDTO.OrderedDrinks.Drink orderedDrink = new DrinksReportDTO.OrderedDrinks.Drink();
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
