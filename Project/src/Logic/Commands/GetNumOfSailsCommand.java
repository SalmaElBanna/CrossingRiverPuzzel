package Logic.Commands;

import Logic.Receiver;

public class GetNumOfSailsCommand implements Command {
	
	private Receiver receiver;
	
	public GetNumOfSailsCommand(Receiver receiver) {
		this.receiver=receiver;
	}

	@Override
	public void execute() {
		receiver.getNumOfSails();
	}

}
