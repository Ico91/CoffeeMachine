package coffee_machine.menu;

import java.util.List;
import java.util.Map;

public class MenuModel {
	private Map<String, Command> commands;
	private List<String> commandsInformation;
	private Map<String, Command> hiddenCommands;

	public MenuModel(MenuBuilder menuBuilder) {
		this.commands = menuBuilder.getCommands();
		this.hiddenCommands = menuBuilder.getHiddenCommands();
		this.commandsInformation = menuBuilder.getCommandsInformation();
	}

	public List<String> getCommandsInformation() {
		return this.commandsInformation;
	}

	public Command getCommand(String command) throws InvalidCommandException {
		Command executable = this.commands.get(command);
		if (executable == null) {
			executable = this.hiddenCommands.get(command);
		}
		if (executable == null)
			throw new InvalidCommandException("Invalid command");
		return executable;
	}
}
