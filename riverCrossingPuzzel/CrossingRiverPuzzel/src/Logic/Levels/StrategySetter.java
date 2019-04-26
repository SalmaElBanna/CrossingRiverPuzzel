package Logic.Levels;

import java.util.List;

import Logic.Crossers.ICrosser;

public class StrategySetter {

	private ICrossingStrategy strategy;
	
	public StrategySetter() {
		
	}
	
	//Constructor sets the strategy
	public StrategySetter(ICrossingStrategy strategy) {
		this.strategy=strategy;
	}
	//Getter for the strategy
	public ICrossingStrategy getStrategy() {
		return strategy;
	}
	//Setter for the strategy
	public void setStrategy(ICrossingStrategy strategy) {
		this.strategy = strategy;
	}
	
	public String [] getInstructions() {
		return strategy.getInstructions();
	}
	
	public List<ICrosser> getInitialCrossers(){
		return strategy.getInitialCrossers();
	}
	
	public boolean isValid(List<ICrosser> rightBankCrossers,List<ICrosser> leftBankCrossers,List<ICrosser> boatRiders) {
		return (strategy.isValid(rightBankCrossers, leftBankCrossers, boatRiders));
	}
	
}
