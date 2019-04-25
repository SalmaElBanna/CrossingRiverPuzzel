package Logic;

import java.util.ArrayList;
import java.util.List;

import Logic.Crossers.ICrosser;
import Logic.Levels.ICrossingStrategy;

public class Receiver implements IRiverCrossingControl {
	
	private String errorMessage;
	private ICrossingStrategy gameStrategy;
	private List <ICrosser> leftCrossers=new ArrayList<>();
	private List <ICrosser> rightCrossers=new ArrayList<>();
	
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
		this.gameStrategy=gameStrategy;
		this.leftCrossers=gameStrategy.getInitialCrossers();
	}

	@Override
	public void resetGame() {
		this.leftCrossers=gameStrategy.getInitialCrossers();
		this.rightCrossers.clear();
		// + set the score to 0
	}

	@Override
	public String[] getInstructions() {
		return gameStrategy.getInstructions();
	}

	@Override
	public List<ICrosser> getCrossersOnRightBank() {
		return rightCrossers;
	}

	@Override
	public List<ICrosser> getCrossersOnLeftBank() {
		return leftCrossers;
	}

	@Override
	public boolean isBoatOnLeftBank() {
		// dependent on the score (if even: true, otherwise: false)
		return false;
	}

	@Override
	public int getNumOfSails() {
		//  dependent on the score
		return 0;
	}
	
	/**
	 * crossers: the boat riders
	 */

	@Override
	public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		if(fromLeftToRightBank) {
			
		}
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
