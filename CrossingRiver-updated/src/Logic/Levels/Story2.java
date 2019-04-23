/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Levels;

import Logic.Crossers.Herbivore;
import Logic.Crossers.Goat;
import Logic.Crossers.Farmer;
import Logic.Crossers.ICrosser;
import java.util.List;

/**
 *
 * @author salma
 */
public class Story2 implements ICrossingStrategy {
        private List<ICrosser> initialCrossers;
	private String [] instructions;
        private String  errorMessage;

   
	/** Constructor sets initial Crossers of the game (Farmer1,Farmer2,Farmer3,Farmer4,goat)
	 *  and sets instructions
	 */
	public Story2() {
            initialCrossers.add(new Farmer(90));
            initialCrossers.add(new Farmer(80));
            initialCrossers.add(new Farmer(60));
            initialCrossers.add(new Farmer(40));
	    initialCrossers.add(new Goat());
          
		setInstructions();
	}

	@Override
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
			List<ICrosser> boatRiders) {
		if(boatRiders.get(0).getWeight()+boatRiders.get(1).getWeight()>100) {
                    errorMessage="The boat can hold a maximum of 100kg";
			return false;
              }
                if (boatRiders.get(0) instanceof Herbivore && boatRiders.get(1)==null){
                 errorMessage="A person should steer the boat";
			return false;
                }
            
                return true; 
	}


	@Override
	public List<ICrosser> getInitialCrossers() {
		return initialCrossers;
	}
	
	//setting the instructions of the game
	private void setInstructions() {
		int i=0;
                instructions[i++]="Four Farmers and their animal need to cross a river in a small boat.\nThe weights are 90kg,80kg,60,40kg respectively,and the weight of the animal is 20kg";
		instructions[i++]="The boat cannot bear a load heavier than 100kg.";
		instructions[i++]="All farmers can raft while the animal cannot.";
	}

	@Override
	public String [] getInstructions() {
		return instructions;
	}
         public String getErrorMessage() {
        return errorMessage;
    }
	
}
