package coffee_machine.menu;

import java.util.List;
import java.util.Map;

public class MenuModel {
	private Map<String, Executable> commands;
	private List<String> commandsInformation;
	private Map<String, Executable> hiddenCommands;

	public MenuModel(MenuBuilder menuBuilder) {
		this.commands = menuBuilder.getCommands();
		this.hiddenCommands = menuBuilder.getHiddenCommands();
		this.commandsInformation = menuBuilder.getCommandsInformation();
	}

	public List<String> getCommandsInformation() {
		return this.commandsInformation;
	}

	public Executable getCommand(String command) throws InvalidCommandException {
		Executable executable = this.commands.get(command);
		if (executable == null) {
			executable = this.hiddenCommands.get(command);
		}
		if (executable == null)
			throw new InvalidCommandException("Invalid command");
		return executable;
	}
}
