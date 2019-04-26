package Logic.Commands;

import Logic.Receiver;

public class ResetGameCommand implements Command {
	
	private Receiver receiver;
	
	public ResetGameCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.resetGame();
	}

}
