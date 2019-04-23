package Logic.Commands;

import Logic.Receiver;

public class GetCrossersOnRightBankCommand implements Command{
	private Receiver receiver;
	
	public GetCrossersOnRightBankCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.getCrossersOnRightBank();
	}

}
