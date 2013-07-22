package coffeeMachine;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
	public class DrinkDTO {
		private String name;
		private int price;
		private int amount;
		
		public DrinkDTO(){}
		
		@XmlAttribute
		public void setName(String name){
			this.name=name;
		}
		
		@XmlAttribute
		public void setPrice(int price){
			this.price=price;
		}
		
		@XmlAttribute
		public void setAmount(int amount){
			this.amount=amount;
		}
		
		public DrinkDTO getDrinkDTO(){
			return this;
		}
		
		public String getName(){
			return this.name;
		}
		public int getAmount(){
			return this.amount;
		}
		public int getPrice(){
			return this.price;
		}
		
		@Override
		public String toString(){
			return "name: "+name+" price: "+price+" amount: "+amount;
		}
	}
	