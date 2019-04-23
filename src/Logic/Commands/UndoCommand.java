package Logic.Commands;

import Logic.Receiver;

public class UndoCommand implements Command { 
	
	private Receiver receiver;
	
	public UndoCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.undo();
	}

}
