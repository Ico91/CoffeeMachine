package coffee_machine.menu;
import java.util.List;
class Eval {
	public Eval() {
	}
	
	public ResultStatus runCommand(Command command, List<String> params) {
		return command.execute(params);
	}
}
