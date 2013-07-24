package coffee_machine.menu;
import java.util.ArrayList;
import java.util.List;


public class ParamRequirements {
	private List<String> requirements;
	
	public ParamRequirements() {
		this.requirements = new ArrayList<String>();
	}
	
	public void addRequirements(String param) {
		this.requirements.add(param);
	}
	
	public List<String> getReqirements() {
		return this.requirements;
	}
}
