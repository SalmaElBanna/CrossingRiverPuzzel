package Logic.Commands;

import Logic.Receiver;

public class GetCrossersOnLeftBankCommand implements Command {
	
	private Receiver receiver;
	
	public GetCrossersOnLeftBankCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.getCrossersOnRightBank();
	}

}
