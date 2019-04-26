package Logic.Commands;

import Logic.Receiver;

public class IsBoatOnLeftBankCommand implements Command {
	
	private Receiver receiver;
	
	public IsBoatOnLeftBankCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.isBoatOnLeftBank();
	}

}
