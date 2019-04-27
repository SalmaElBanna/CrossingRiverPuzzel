package Logic.Commands;

import java.util.List;

import Logic.Receiver;
import Logic.Crossers.ICrosser;

public class CanMoveCommand implements Command {

	private Receiver receiver;
	private List<ICrosser> crossers;
	private boolean fromLeftToRightBank;

	public CanMoveCommand(Receiver receiver,List<ICrosser> crossers, boolean fromLeftToRightBank) {
		this.receiver=receiver;
		this.crossers=crossers;
		this.fromLeftToRightBank=fromLeftToRightBank;
	}
	
	@Override
	public void execute() {
		receiver.canMove(crossers, fromLeftToRightBank);
	}

}
