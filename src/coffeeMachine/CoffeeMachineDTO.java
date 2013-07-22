package coffeeMachine;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class CoffeeMachineDTO  {
	@XmlElement
	ArrayList<DrinkDTO> drinksDTO;
	
	CoffeeMachineDTO(){
		drinksDTO=new ArrayList<DrinkDTO>();
	}
	
	
	public void setDrinksDTO(ArrayList<DrinkDTO> drinkDTO){
		this.drinksDTO=drinkDTO;
	}
	
	@Override
	public String toString(){
		String str=" ";
		for(int i=0;i<drinksDTO.size();i++){
			str+=drinksDTO.get(i).toString();
		}
		return str;
	}
	public ArrayList<DrinkDTO> getdrinksDTO(){
		return drinksDTO;
	}
	
	public void UnmarshallingDrinks(){
		try {	
			 
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(CoffeeMachineDTO.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CoffeeMachineDTO cfdOut = (CoffeeMachineDTO) jaxbUnmarshaller.unmarshal(file);
			this.setDrinksDTO(cfdOut.getdrinksDTO());
			//System.out.println(cfdOut);
	 
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	 
		}
	
	public DrinksContainer getDrinksContainer(){
		DrinksContainer drinksContainer=new DrinksContainer();
		
		
		for(int i=0;i<drinksDTO.size();i++){
			drinksContainer.add(new Drink(drinksDTO.get(i).getName(),drinksDTO.get(i).getPrice()), drinksDTO.get(i).getAmount());
		}
		return drinksContainer;
		}
		

	
	
}
