package coffee_machine;

import java.util.List;

import coffee_machine.menu.Executable;
import coffee_machine.menu.MenuController;
import coffee_machine.menu.MenuModel;
import coffee_machine.menu.ParamRequirements;
import coffee_machine.menu.ResultStatus;
import coffee_machine.model.CoffeeMachineState;

public abstract class MenuBasedFlow implements Flow {
	private Flow next;
	protected MenuModel menuModel;
	protected MenuController menuController;

	public MenuBasedFlow() {
	}

	protected abstract void initMenu(CoffeeMachineState cm);

	@Override
	public final Flow execute(CoffeeMachineState machine) {
		initMenu(machine);
		menuController.start();
		return next;
	}

	protected Executable navigationCommand(Flow flow) {
		return new ChangeFlowCommand(flow);
	}
	protected Executable navigationCommandWithOutput(Flow flow, String output) {
		return new ChangeFlowCommand(flow, output);
	}
	public Flow getNext() {
		return next;
	}

	public void setNext(Flow next) {
		this.next = next;
	}

	private class ChangeFlowCommand implements Executable {
		private Flow nextFlow;
		private String output;
		
		private ChangeFlowCommand(Flow nextFlow) {
			this.output = "";
			this.nextFlow = nextFlow;
		}

		private ChangeFlowCommand(Flow nextFlow, String output) {
			this(nextFlow);
			this.output = output;
		}
		@Override
		public ResultStatus execute(List<String> params) {
			next = this.nextFlow;
			return new ResultStatus(output, true);
		}

		@Override
		public ParamRequirements requirements() {
			return new ParamRequirements();
		}

	}
}
