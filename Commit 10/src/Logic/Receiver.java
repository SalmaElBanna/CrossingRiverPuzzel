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
	
	//score
	private Score score;
	
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
		score=new Score();
	}

	@Override
	public void resetGame() {
		this.leftCrossers=gameStrategy.getInitialCrossers();
		this.rightCrossers.clear();
		score.resetScore();
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
		if(score.getScore()%2==0)
			return true;
		return false;
	}

	@Override
	public int getNumOfSails() {
		//  dependent on the score
		return score.getScore();
	}
	
	/**
	 * crossers: the boat riders
	 */

	@Override
	public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		 List <ICrosser> leftBankCrossers=new ArrayList<>();
		 List <ICrosser> rightBankCrossers=new ArrayList<>();
		 int i;
		 
		 //copying real left and right crossers to temporary lists for validation
		 for(i=0;i<leftCrossers.size();i++) {
			 leftBankCrossers.add(leftCrossers.get(i));
		 }
		 
		 for(i=0;i<rightCrossers.size();i++) {
			 rightBankCrossers.add(rightCrossers.get(i));
		 }

	    //adding the boat riders to the temporary lists for validation
		if(fromLeftToRightBank) {
			for(i=0;i<crossers.size();i++) {
				rightBankCrossers.add(crossers.get(i));
				leftBankCrossers.remove(crossers.get(i));
			}
		}
		else {
			for(i=0;i<crossers.size();i++) {
				leftBankCrossers.add(crossers.get(i));
				rightBankCrossers.remove(crossers.get(i));
			}
		}
		//checking if valid move
		return gameStrategy.isValid(rightBankCrossers, leftBankCrossers, crossers);
	}

	@Override
	public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		score.increaseScore();
		if(fromLeftToRightBank) {
			for(int i=0;i<crossers.size();i++) {
				rightCrossers.add(crossers.get(i));
				leftCrossers.remove(crossers.get(i));
			}
		}
		else
			for(int i=0;i<crossers.size();i++) {
				leftCrossers.add(crossers.get(i));
				rightCrossers.remove(crossers.get(i));
			}
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
		score.decreaseScore();
		
	}

	@Override
	public void redo() {
		score.increaseScore();
		
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
	
	public ICrossingStrategy getGameStrategy() {
		return gameStrategy;
	}
	
	public Score getScore() {
		return score;
	}
	
}
