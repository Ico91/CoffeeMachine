package coffeeMachine.flows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;
import modules.xmlModule.exceptions.XMLIOException;
import coffeeMachine.CoffeeMachineState;
import coffeeMachine.Drink;
import coffeeMachine.DrinksContainer;
import coffeeMachine.dto.reports.DrinksReportDTO;
import coffeeMachine.transformers.toDto.OrderedDrinksContainerToDTO;

/**
 * 
 * @author Hristo
 * 
 */
public class DrinksReportFlow implements Flow {

	@Override
	public Flow execute(CoffeeMachineState machine) {

		DrinksContainer orderedDrinks = calculateOrderedDrinks(
				machine.getCurrentDrinks(), machine.getInitialDrinks());

		OrderedDrinksContainerToDTO transformer = new OrderedDrinksContainerToDTO();

		DrinksReportDTO drinksReport = transformer.transform(orderedDrinks);
		save(drinksReport);

		return new DrinkListFlow();
	}

	public DrinksContainer calculateOrderedDrinks(
			DrinksContainer currentDrinks, DrinksContainer initialDrinks) {

		DrinksContainer orderedDrinks = new DrinksContainer();

		for (Drink drink : initialDrinks.getDrinks().keySet()) {
			int difference = initialDrinks.getDrinkQuantity(drink)
					- currentDrinks.getDrinkQuantity(drink);
			Drink orderedDrink = new Drink(drink.getName(), drink.getPrice());
			orderedDrinks.add(orderedDrink, difference);
		}

		return orderedDrinks;
	}

	private void save(DrinksReportDTO drinksReport) {
		XMLIO xml = new XMLIO();
		String filename = generateReportName();
		XMLDocumentMetaData xmlMeta = new XMLDocumentMetaData(
				drinksReport.getClass(), filename, "report.xsd");

		try {
			System.out.println("Report: " + filename + drinksReport.toString() + System.lineSeparator());
			xml.write(xmlMeta, drinksReport);
		} catch(XMLIOException e) {
			System.out.println("Cannot write report to file!");
		}
	}

	private String generateReportName() {

		DateFormat dateFormat = new SimpleDateFormat("HH;mm;ss - dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();

		String fileName = "reports/" + dateFormat.format(cal.getTime()) + ".xml";

		return fileName;
	}
	
}
