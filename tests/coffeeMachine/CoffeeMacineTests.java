package coffeeMachine;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



import org.junit.Before;
import org.junit.Test;

public class CoffeeMacineTests {
	CoffeeMachineDTO mm ; 
	
	@Before 
	public void init(){
		mm= new CoffeeMachineDTO();
	}
	
	/*
	@Test
	public void Read(){
		DrinkDTO[] drinksArr=new DrinkDTO[1];
	       try {
	    	   JAXBContext context = JAXBContext.newInstance(CoffeeMachineDTO.class);
	           Unmarshaller un = context.createUnmarshaller();
	            mm = (CoffeeMachineDTO) un.unmarshal(new File("drinks.xml"));
	           } catch (JAXBException ex) {
	    	  ex.printStackTrace();
	    	  System.out.println("Unsuccess unmarshaling");
	       }
	       
	       DrinkDTO drinkDTO=new DrinkDTO();
	       drinkDTO.setAmount(10);
	       drinkDTO.setName("Coffee");
	       drinkDTO.setPrice(30);
	       CoffeeMachineDTO cmd=new CoffeeMachineDTO();
	       drinksArr[0]=drinkDTO;
	       cmd.setDrinksDTO(drinksArr);
	       System.out.println(cmd.toString());
	       System.out.println(mm.toString());
	       
	       
	       
	   }*/
	
	

}
