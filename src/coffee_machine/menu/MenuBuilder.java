package coffee_machine.menu;
import java.util.*;

public class MenuBuilder {
	private Map<String, Command> commands;
	private Map<String, Command> hiddenCommands;
	private List<String> commandsInformation;

	public MenuBuilder() {
		this.commands = new TreeMap<String, Command>();
		this.hiddenCommands = new TreeMap<String, Command>();
		this.commandsInformation = new ArrayList<String>();
	}

	public MenuBuilder command(String command, String commandInformation,
			Command executable) {
		this.commands.put(command, executable);
		this.commandsInformation.add(command + " - " + commandInformation);
		return this;
	}
	
	public MenuBuilder hiddenCommand(String hiddenCommand,
			Command executable) {
		this.hiddenCommands.put(hiddenCommand, executable);
		return this;
	}
	
	public MenuModel build() {
		return new MenuModel(this);
	}
	
	public Map<String, Command> getCommands() {
		return commands;
	}

	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

	public List<String> getCommandsInformation() {
		return commandsInformation;
	}

	public void setCommandsInformation(List<String> commandsInformation) {
		this.commandsInformation = commandsInformation;
	}

	public Map<String, Command> getHiddenCommands() {
		return hiddenCommands;
	}
}
