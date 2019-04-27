package Logic.Commands;

import Logic.Receiver;

public class CanUndoCommand implements Command {
	
	private Receiver receiver;
	
	public CanUndoCommand(Receiver receiver) {
		this.receiver=receiver;
	}
	
	@Override
	public void execute() {
		receiver.canUndo();
	}

}
