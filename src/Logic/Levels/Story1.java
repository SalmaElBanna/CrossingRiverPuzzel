package Logic.Levels;

import java.util.ArrayList;
import java.util.List;

import Logic.Crossers.Cabbage;
import Logic.Crossers.Farmer;
import Logic.Crossers.Fox;
import Logic.Crossers.Goat;
import Logic.Crossers.ICrosser;
import Logic.Crossers.Wolf;

public class Story1 implements ICrossingStrategy{
	
	private List<ICrosser> initialCrossers=new ArrayList<>();
	private String [] instructions=new String[5];
	private String errorMessage;
	
	/** Constructor sets initial Crossers of the game (Farmer,herbivore ex:goat,carnivore ex:fox & plant ex:cabbage)
	 *  and sets instructions
	 */
	public Story1() {
		initialCrossers.add(new Goat());
		initialCrossers.add(new Wolf());
		initialCrossers.add(new Farmer(70));
		initialCrossers.add(new Cabbage());
		setInstructions();
	}

	@Override
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
			List<ICrosser> boatRiders) {
		int errorNumber=0;
		
		//check whether one of the 2 boat riders can sail the boat
		if(boatRiders.size()==2) {
			if(!(boatRiders.get(0).canSail()) || !(boatRiders.get(1).canSail())) {
				errorNumber=1;
			}
		}
		//check whether the one boat rider can sail the boat
		else if(boatRiders.size()==1)
			if(!boatRiders.get(0).canSail()) {
				errorNumber=1;
			}
		if(errorNumber==1) {
			errorMessage="The Farmer should raft the boat.";
			return false;
		}
		//check whether rightBankCrossers can harm each other
		if(rightBankCrossers.size()!=4) {
			//to check if they were 3 whether the ones in index 0 and 2 can harm each other or not
			if(Math.abs(rightBankCrossers.get(0).getEatingRank()-rightBankCrossers.get(rightBankCrossers.size()-1).getEatingRank())==1) {
				errorNumber=2;
			}
			for(int i=0;i<rightBankCrossers.size()-1;i++) {
				if(Math.abs(rightBankCrossers.get(i).getEatingRank() - rightBankCrossers.get(i+1).getEatingRank())==1)
					errorNumber=2;
			}
		}
		if(errorNumber==2) {
			if((rightBankCrossers.get(0) instanceof Fox && rightBankCrossers.get(rightBankCrossers.size()-1) instanceof Goat) || 
					(rightBankCrossers.get(0) instanceof Goat && rightBankCrossers.get(rightBankCrossers.size()-1) instanceof Fox))
				errorMessage="Fox will eat the goat.";
			else if((rightBankCrossers.get(0) instanceof Cabbage && rightBankCrossers.get(rightBankCrossers.size()-1) instanceof Goat) || 
					(rightBankCrossers.get(0) instanceof Goat && rightBankCrossers.get(rightBankCrossers.size()-1) instanceof Cabbage))
				errorMessage="Goat will eat the cabbage.";
			return false;
		}
		//check whether leftBankCrossers can harm each other
		if(leftBankCrossers.size()!=4) {
			//to check if they were 3 whether the one in index 0 and 2 can harm each other or not
			if(Math.abs(leftBankCrossers.get(0).getEatingRank()-leftBankCrossers.get(leftBankCrossers.size()-1).getEatingRank())==1) {
				errorNumber=3;
			}
			for(int i=0;i<leftBankCrossers.size()-1;i++) {
				if(Math.abs(leftBankCrossers.get(i).getEatingRank() - leftBankCrossers.get(i+1).getEatingRank())==1) 
					errorNumber=3;
			}
		}
		if(errorNumber==3) {
			if((leftBankCrossers.get(0) instanceof Fox && leftBankCrossers.get(leftBankCrossers.size()-1) instanceof Goat) || 
					(leftBankCrossers.get(0) instanceof Goat && leftBankCrossers.get(leftBankCrossers.size()-1) instanceof Fox))
				errorMessage="Fox will eat the goat.";
			else if((leftBankCrossers.get(0) instanceof Cabbage && leftBankCrossers.get(leftBankCrossers.size()-1) instanceof Goat) || 
					(leftBankCrossers.get(0) instanceof Goat && leftBankCrossers.get(leftBankCrossers.size()-1) instanceof Cabbage))
				errorMessage="Goat will eat the cabbage.";
			return false;
		}
		//This is an invalid move already as they both can't sail 
		if(Math.abs(boatRiders.get(0).getEatingRank() - boatRiders.get(1).getEatingRank())==1) 
				return false;
		
		return true;
	}


	@Override
	public List<ICrosser> getInitialCrossers() {
		return initialCrossers;
	}
	
	//setting the instructions of the game
	private void setInstructions() {
		int i=0;
		//the first instruction is the story itself
		instructions[i++]="A farmer wants to cross a river and take with him a carnivorous, a herbivorous and a plant.";
		//rules
		instructions[i++]="The Farmer is the only one who can sail the boat.\n   He can only take one passenger in addition to himself.";
		instructions[i++]="You can't leave any two crossers on the same bank if one of them can harm the other.";
		
	}

	@Override
	public String [] getInstructions() {
		return instructions;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}


}
