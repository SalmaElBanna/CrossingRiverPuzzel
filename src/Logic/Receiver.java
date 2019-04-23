package Logic;

import java.util.List;

import Logic.Crossers.ICrosser;
import Logic.Levels.ICrossingStrategy;

public class Receiver implements IRiverCrossingControl {
	private String errorMessage;
	private static Receiver receiver;
	
	//make constructor private
	private Receiver() {
		
	}
	
	public static Receiver getInstance() {
		if(receiver==null)
			receiver=new Receiver();
		return receiver;
	}
	

	@Override
	public void newGame(ICrossingStrategy gameStrategy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICrosser> getCrossersOnRightBank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICrosser> getCrossersOnLeftBank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBoatOnLeftBank() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumOfSails() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<List<ICrosser>> solveGame() {
		// TODO Auto-generated method stub
		return null;
	}
	
	////////////Extra methods than that in IRiverCrossingControlInterface /////////////
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	

}
