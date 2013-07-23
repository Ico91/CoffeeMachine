package coffeeMachine;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.JAXBException;

import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;

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

		DrinksReport drinksReport = transformer.transform(orderedDrinks);

		System.out.print(drinksReport.toString());
		
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

	private void save(DrinksReport drinksReport) {
		XMLIO xml = new XMLIO();
		XMLDocumentMetaData xmlMeta = new XMLDocumentMetaData(
				drinksReport.getClass(), generateReportName(), "report.xsd");

		try {
			xml.write(xmlMeta, drinksReport);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String generateReportName() {

		DateFormat dateFormat = new SimpleDateFormat("HH;mm;ss - dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();

		String fileName = dateFormat.format(cal.getTime()) + ".xml";

		return fileName;
	}
}
