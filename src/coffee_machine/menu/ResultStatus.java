package coffee_machine.menu;
public class ResultStatus {
	
	private String output;
	private boolean exit;

	public ResultStatus() {
		this.output = "";
		this.exit = false;
	}
	
	public ResultStatus(String output, boolean exit) {
		this.output = output;
		this.exit = exit;
	}
	
	public String getOutput() {
		return output;
	}

	public boolean isExit() {
		return exit;
	}

}
