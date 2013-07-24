package coffee_machine.menu;
import java.util.*;

public class MenuBuilder {
	private Map<String, Executable> commands;
	private Map<String, Executable> hiddenCommands;
	private List<String> commandsInformation;

	public MenuBuilder() {
		this.commands = new TreeMap<String, Executable>();
		this.hiddenCommands = new TreeMap<String, Executable>();
		this.commandsInformation = new ArrayList<String>();
	}

	public MenuBuilder command(String command, String commandInformation,
			Executable executable) {
		this.commands.put(command, executable);
		this.commandsInformation.add(command + " - " + commandInformation);
		return this;
	}
	
	public MenuBuilder hiddenCommand(String hiddenCommand,
			Executable executable) {
		this.hiddenCommands.put(hiddenCommand, executable);
		return this;
	}
	
	public MenuModel build() {
		return new MenuModel(this);
	}
	
	public Map<String, Executable> getCommands() {
		return commands;
	}

	public void setCommands(Map<String, Executable> commands) {
		this.commands = commands;
	}

	public List<String> getCommandsInformation() {
		return commandsInformation;
	}

	public void setCommandsInformation(List<String> commandsInformation) {
		this.commandsInformation = commandsInformation;
	}

	public Map<String, Executable> getHiddenCommands() {
		return hiddenCommands;
	}
}
