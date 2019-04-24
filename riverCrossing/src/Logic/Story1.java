package Logic;

import java.util.List;

public class Story1 implements ICrossingStrategy {
	
	private List<ICrosser> initialCrossers;
	private String [] instructions;
	
	/** Constructor sets initial Crossers of the game (Farmer,herbivore ex:goat,carnivore ex:fox & plant ex:cabbage)
	 *  and sets instructions
	 */
	public Story1() {
		initialCrossers.add(new Goat());
		initialCrossers.add(new Farmer(50));
		initialCrossers.add(new Cabbage());
		initialCrossers.add(new Fox());
		setInstructions();
	}

	@Override
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
			List<ICrosser> boatRiders) {
		if(!(boatRiders.get(0) instanceof Person) || !(boatRiders.get(1) instanceof Person)) {
			return false;
		}
		
		
			
		return false;
	}


	@Override
	public List<ICrosser> getInitialCrossers() {
		return initialCrossers;
	}
	
	//setting the instructions of the game
	private void setInstructions() {
		int i=0;
		instructions[i++]="The Farmer is the only one who can sail the boat\nHe can only take one passenger in addition to himself.";
		instructions[i++]="You can't leave any two crossers on the same bank if one of them can harm the other.";
	}

	@Override
	public String [] getInstructions() {
		return instructions;
	}

}
