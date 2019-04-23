package Logic.Commands;

import Logic.Receiver;

public class SolveGameCommand implements Command {
	
	private Receiver receiver;
	
	public SolveGameCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.solveGame();
	}

}
