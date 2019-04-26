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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salma
 */
public class Score extends java.util.Observable  {
    private int score=0;
    private int count;
    private String label;
    private List <ICrosser> leftCrossers=new ArrayList<>();
	private List <ICrosser> rightCrossers=new ArrayList<>();
    public Score(String label,Receiver receiver) {
       this.label=label; 
       count=receiver.getNumOfSails();
       increaseScore(receiver);
    }
    public void increaseScore(Receiver receiver){
     if(receiver.doMove(leftCrossers, true)|| receiver.doMove(rightCrossers, true)){
         setScore(score++);
     }
     score=getScore();
     setChanged();
     notifyObservers(score);
    }
    public void decreaseScore(){
    score=getScore();
    setChanged();
    notifyObservers(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
