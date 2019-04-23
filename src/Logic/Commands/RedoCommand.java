package Logic.Commands;

import Logic.Receiver;

public class RedoCommand implements Command {
	
	private Receiver receiver;
	
	public RedoCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.redo();
	}

}
