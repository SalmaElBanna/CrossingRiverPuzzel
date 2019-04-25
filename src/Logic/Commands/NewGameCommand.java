package Logic.Commands;

import Logic.Receiver;
import Logic.Levels.ICrossingStrategy;

public class NewGameCommand implements Command {
	private Receiver receiver;
	private ICrossingStrategy gameStrategy;
	
	public NewGameCommand(Receiver receiver,ICrossingStrategy gameStrategy) {
		this.receiver=receiver;
		this.gameStrategy=gameStrategy;
	}
	@Override
	public void execute() {
		receiver.newGame(gameStrategy);
	}

	
}
