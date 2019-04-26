/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Logic.Crossers.ICrosser;
import Logic.Levels.ICrossingStrategy;
import Logic.Levels.Story1;
import Logic.Levels.StrategySetter;

/**
 *
 * @author salma
 */
public class Score extends java.util.Observable  {
    int score;
    int minNumOfSails;
    int count;
    private String label;
    public Score(String label,Receiver receiver) {
       this.label=label; 
       count=receiver.getNumOfSails();
       if( receiver.getGameStrategy().getClass().getSimpleName()=="Story1"){
           minNumOfSails=7;
         }
       
    }
    public void increaseScore(){
     setChanged();
     notifyObservers(score);
    }
    public void decreaseScore(){
    setChanged();
    notifyObservers(score);
    }
}
