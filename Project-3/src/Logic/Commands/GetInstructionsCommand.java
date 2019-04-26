package Logic.Commands;

import Logic.Receiver;

public class GetInstructionsCommand implements Command {

	private Receiver receiver;
	
	public GetInstructionsCommand(Receiver receiver) {
		this.receiver=receiver;
	}
	@Override
	public void execute() {
		receiver.getInstructions();
	}

	
}
