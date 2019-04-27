package Logic;

import Logic.Commands.Command;

public class Controller {
	
	private Command command;
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void performCommand() {
		command.execute();
	}

}
