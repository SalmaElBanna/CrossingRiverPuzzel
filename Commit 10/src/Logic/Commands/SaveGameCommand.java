package Logic.Commands;

import Logic.Receiver;

public class SaveGameCommand implements Command {

	private Receiver receiver;
	
	public SaveGameCommand(Receiver receiver) {
		this.receiver=receiver;
	}
	@Override
	public void execute() {
		receiver.saveGame();
	}

}
