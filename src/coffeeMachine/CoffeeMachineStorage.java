package coffeeMachine;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import coffeeMachine.exceptions.CoffeeMachineStateException;

import modules.xmlModule.*;

/***
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class CoffeeMachineStorage {
	// TODO: Need changes!!!
	public CoffeeMachineStorage() {

	}

	public CoffeeMachineState load(String xmlFilePath, String xsdSchemePath) {
		XMLDocumentMetaData xmlDocumentMetadata = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, xmlFilePath, xsdSchemePath);
		XMLIO xmlIO = new XMLIO();
		try {
			CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
					.read(xmlDocumentMetadata);
			return new CoffeeMachineState(loadMoneyAmount(coffeeMachineDTO),
					loadDrinksContainer(coffeeMachineDTO));

		} catch (FileNotFoundException e) {
			throw new CoffeeMachineStateException("XML file not found!");
		} catch (JAXBException e) {
			throw new CoffeeMachineStateException(
					"Cannot create marshal object");
		} catch (SAXException e) {
			throw new CoffeeMachineStateException(e.getMessage());
		}
	}

	private MoneyAmount loadMoneyAmount(CoffeeMachineDTO coffeeMachineDTO) {
		MoneyAmount moneyAmount = new MoneyAmount();
		for (coffeeMachine.CoffeeMachineDTO.Money.Coin c : coffeeMachineDTO
				.getMoney().getCoin()) {
			moneyAmount.add(convertToCoin(c.getType()), c.getAmount());
		}

		return moneyAmount;
	}

	private DrinksContainer loadDrinksContainer(
			CoffeeMachineDTO coffeeMachineDTO) {
		DrinksContainer drinksContainer = new DrinksContainer();

		for (coffeeMachine.CoffeeMachineDTO.Drinks.Drink d : coffeeMachineDTO
				.getDrinks().getDrink()) {
			drinksContainer.add(new Drink(d.getName(), d.getPrice()),
					d.getAmount());
		}

		return drinksContainer;
	}
	
	private Coin convertToCoin(String coinType) {
		if (coinType.equals("FIVE"))
			return Coin.FIVE;
		if (coinType.equals("TEN"))
			return Coin.TEN;
		if (coinType.equals("TWENTY"))
			return Coin.TWENTY;
		if (coinType.equals("FIFTY"))
			return Coin.FIFTY;
		if (coinType.equals("LEV"))
			return Coin.LEV;

		throw new CoffeeMachineStateException("Invalid coin type in xml file");
	}
}
