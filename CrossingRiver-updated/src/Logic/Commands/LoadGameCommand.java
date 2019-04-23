package Logic.Commands;

import Logic.Receiver;

public class LoadGameCommand implements Command {
	
	private Receiver receiver;
	
	public LoadGameCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.loadGame();
	}

}
