package modules.menuModule;
import java.util.List;
import java.util.Map;


public class MenuModel {
	private Map<String, Executable> commands;
	private List<String> commandsInformation;
	
	public MenuModel(MenuBuilder menuBuilder) {
		this.commands = menuBuilder.getCommands();
		this.commandsInformation = menuBuilder.getCommandsInformation();
	}
	
	public List<String> getCommandsInformation() {
		return this.commandsInformation;
	}
	
	public Executable getCommand(String command) throws InvalidCommandException {
		Executable executable = this.commands.get(command);
		if(executable == null)
			throw new InvalidCommandException("Invalid command");
		return executable;
	}
}
