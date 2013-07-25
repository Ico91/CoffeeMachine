package coffee_machine.menu;
import java.util.List;

public interface Command {
	public ResultStatus execute(List<String> params);
	public ParamRequirements requirements();
}
