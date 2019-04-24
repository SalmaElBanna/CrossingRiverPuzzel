package Logic;

import java.util.List;

public interface ICrossingStrategy {
	
	/**
	 * @param rightBankCrossers the crossers on the right bank
	 * @param leftBankCrossers the crossers on the left bank
	 * @param boatRiders the boat riders
	 * @return whether this move is valid or not according to the rules
	 */
	
	public boolean isValid(List<ICrosser> rightBankCrossers,List<ICrosser> leftBankCrossers,List<ICrosser> boatRiders);
	
	/** @return the crossers initially on the left bank
	 * there are no crossers initially on the right bank
	 */
	public List<ICrosser> getInitialCrossers();
	
	/**
	 * @return the rules and instructions of the
	 * current story to be shown to the player.
	 */
	
	public String [] getInstructions();

}
