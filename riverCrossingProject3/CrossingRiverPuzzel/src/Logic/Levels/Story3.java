/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Levels;

import Logic.Crossers.Child;
import Logic.Crossers.Herbivore;
import Logic.Crossers.ICrosser;
import Logic.Crossers.Mother;
import java.util.List;

/**
 *
 * @author salma
 */
public class Story3 implements ICrossingStrategy{
    	private List<ICrosser> initialCrossers;
	private String [] instructions;
	private String errorMessage;
        private Child son_1=new Child(35);
        private Child son_2=new Child(40);
        private Child daughter_1=new Child(45);
        private Child daughter_2=new Child(30);
        private Mother mother_1=new Mother(60);
         private Mother mother_2=new Mother(70);
        
        
        
        public Story3(){
            initialCrossers.add(son_1);
            initialCrossers.add(son_2);
            initialCrossers.add(daughter_1);
            initialCrossers.add(daughter_2);
            initialCrossers.add(mother_1);
            initialCrossers.add(mother_2);
            mother_1.setChild(son_1,daughter_1);
            mother_2.setChild(son_2,daughter_2);
            son_1.setMother(mother_1);
            son_2.setMother(mother_2);
            daughter_1.setMother(mother_1);
            daughter_2.setMother(mother_2);
            setInstructions();
        }
        
        @Override
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
			List<ICrosser> boatRiders) {
            	if(boatRiders.get(0)==son_1&&boatRiders.get(1)==mother_2) {
                    errorMessage="This child is alone with another mother";
			return false;
                }
                if(boatRiders.get(0)==son_2&&boatRiders.get(1)==mother_1) {
                    errorMessage="This child is alone with another mother";
			return false;
                }
                if((boatRiders.get(0)!=son_1)&&(boatRiders.get(1)!=mother_1)){
                    errorMessage="Only the first mother and her son can steer the boat";
                return false;
                }
                if (leftBankCrossers.size()==2 && leftBankCrossers.get(0)==daughter_1 && leftBankCrossers.get(1)==mother_2){
                   errorMessage="This child is alone with another mother";
                    return false;
                }
                if (leftBankCrossers.size()==2 && leftBankCrossers.get(0)==daughter_2 && leftBankCrossers.get(1)==mother_1){
                   errorMessage="This child is alone with another mother";
                    return false;
                }
                 if (rightBankCrossers.size()==2 && rightBankCrossers.get(0)==daughter_1 && rightBankCrossers.get(1)==mother_2){
                   errorMessage="This child is alone with another mother";
                    return false;
                }
                  if (rightBankCrossers.size()==2 && rightBankCrossers.get(0)==daughter_2 && rightBankCrossers.get(1)==mother_1){
                   errorMessage="This child is alone with another mother";
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
		instructions[i++]="Two mothers,each accompanied by her son and daughter,want to cross a river.";
                
		instructions[i++]="No child can be with another mother on a bank or on the boat without his mother.";
		instructions[i++]="A  child can be left alone or with other childern, but not with a different mother.";
		instructions[i++]="The Boat can hold maximum of two people.The first mother and her son are the only ones who can row";
	}

	@Override
	public String [] getInstructions() {
		return instructions;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
