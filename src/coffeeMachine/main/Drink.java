package coffeeMachine.main;

public class Drink {
	private String name;
	private int price;
	public Drink(){
		name="";
		price=0;
	}
	public Drink(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
