package Logic.Commands;

import Logic.Receiver;

public class CanRedoCommand implements Command {

	private Receiver receiver;
	
	public CanRedoCommand(Receiver receiver) {
		this.receiver=receiver;
	}
	
	@Override
	public void execute() {
		receiver.canRedo();
	}
}
