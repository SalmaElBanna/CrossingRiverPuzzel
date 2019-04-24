package Logic;

import java.util.List;

public class Controller implements IRiverCrossingControl {

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
	

}
