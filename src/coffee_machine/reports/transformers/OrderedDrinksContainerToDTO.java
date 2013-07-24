package coffee_machine.reports.transformers;

import java.util.Objects;

import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.reports.transformers.dto.DrinksReportDTO;

/**
 * Converts the specified ordered drinks container object into a drinks report
 * transport object, suitable for saving into an XML file.
 * 
 * @author Hristo
 * 
 */
public class OrderedDrinksContainerToDTO {

	/**
	 * Transforms the given orderedDrinks object into a DrinksReportDTO (Data
	 * Transfer Object) which the necessary format for XML operations.
	 * 
	 * @param orderedDrinks
	 *            - the ordered drinks from the machine
	 * @return DrinksReportDTO object
	 */
	public DrinksReportDTO transform(DrinksContainer orderedDrinks) {
		Objects.requireNonNull(orderedDrinks,
				"Specified ordered drinks container is null!");
		DrinksReportDTO drinksReport = new DrinksReportDTO();
		DrinksReportDTO.OrderedDrinks orderedDrinksReport = new DrinksReportDTO.OrderedDrinks();
		int total = 0;
		for (Drink drink : orderedDrinks.getDrinks().keySet()) {
			DrinksReportDTO.OrderedDrinks.Drink orderedDrink = new DrinksReportDTO.OrderedDrinks.Drink();
			orderedDrink.setName(drink.getName());
			orderedDrink.setAmount(orderedDrinks.getDrinks().get(drink)
					.intValue());
			total += orderedDrink.getAmount();
			orderedDrinksReport.getDrink().add(orderedDrink);
		}

		drinksReport.setOrderedDrinks(orderedDrinksReport);
		drinksReport.setTotal(total);

		return drinksReport;
	}
}
