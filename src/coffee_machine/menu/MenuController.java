package coffee_machine.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
	private InputReader inputReader;
	private Eval eval;
	private Printer printer;
	private MenuModel menuModel;

	public MenuController(MenuModel menuModel) {
		this.inputReader = new InputReader();
		this.eval = new Eval();
		this.printer = new Printer();

		this.setMenuModel(menuModel);
	}

	public void start() {
		while (true) {
			List<String> params = new ArrayList<String>();
			for (String commandInfo : menuModel.getCommandsInformation()) {
				this.printer.print(commandInfo);
			}

			Executable executable;
			try {
				executable = this.menuModel.getCommand(this.inputReader
						.readInput());
				for (String paramRequirements : executable.requirements()
						.getReqirements()) {

					this.printer.print(paramRequirements);
					params.add(this.inputReader.readInput());
				}

				ResultStatus resultStatus = executable.execute(params);
				this.printer.print(resultStatus.getOutput());

				if (resultStatus.isExit()) {
					break;
				}
			} catch (InvalidCommandException e) {
				System.out.println("Invalid choice command" + System.lineSeparator());
			}
			
		}
	}

	public InputReader getInputReader() {
		return inputReader;
	}

	public void setInputReader(InputReader inputReader) {
		this.inputReader = inputReader;
	}

	public Eval getEval() {
		return eval;
	}

	public void setEval(Eval eval) {
		this.eval = eval;
	}

	public Printer getPrinter() {
		return printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}
