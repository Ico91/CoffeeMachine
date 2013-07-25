package coffee_machine;

import java.util.List;

import coffee_machine.menu.Command;
import coffee_machine.menu.MenuBuilder;
import coffee_machine.menu.MenuController;
import coffee_machine.menu.MenuModel;
import coffee_machine.menu.ParamRequirements;
import coffee_machine.menu.ResultStatus;
import coffee_machine.model.CoffeeMachineState;

public abstract class MenuBasedFlow implements Flow {
	
	private Flow next;
	
	protected abstract void initMenu( CoffeeMachineState cm, MenuBuilder menuBuilder );

	@Override
	public final Flow execute(CoffeeMachineState machine) {
		
		MenuBuilder menuBuilder = new MenuBuilder();
		initMenu( machine, menuBuilder );
		
		MenuModel menuModel = menuBuilder.build();
		MenuController menuController = new MenuController(menuModel);
		
		menuController.start();
		return next;
	}

	protected Command navigationCommand(Flow flow) {
		return new ChangeFlowCommand(flow);
	}
	protected Command navigationCommandWithOutput(Flow flow, String output) {
		return new ChangeFlowCommand(flow, output);
	}
	public Flow getNext() {
		return next;
	}

	public void setNext(Flow next) {
		this.next = next;
	}

	private class ChangeFlowCommand implements Command {
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
