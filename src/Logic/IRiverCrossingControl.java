package Logic;

import java.util.List;

import Logic.Crossers.ICrosser;
import Logic.Levels.ICrossingStrategy;

public interface IRiverCrossingControl {
	
	public void newGame(ICrossingStrategy gameStrategy);
	
	public void resetGame();
	
	public String [] getInstructions();
	
	public List<ICrosser> getCrossersOnRightBank();
	
	public List<ICrosser> getCrossersOnLeftBank();
	
	public boolean isBoatOnLeftBank();
	
	public int getNumOfSails();
	/**
	 * @param crossers which the user selected to move
	 * @param fromLeftToRightBank boolean to inform the controller   
	 * with the direction of the current game
	 * @return boolean if it is a valid move or not 
	 */
	public boolean canMove(List<ICrosser> crossers,boolean fromLeftToRightBank);
	
	public boolean doMove(List<ICrosser> crossers,boolean fromLeftToRightBank);
	
	public boolean canUndo();
	
	public boolean canRedo();
	
	public void undo();
	
	public void redo();
	
	public void saveGame();
	
	public void loadGame();
	
	public List<List<ICrosser>> solveGame();

}
