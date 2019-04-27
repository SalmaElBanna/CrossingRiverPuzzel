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


public class Score extends java.util.Observable  {
	
    private int score=0;
    private String label;
    
    //increase score
    public void increaseScore(){
    	this.score++;
        setChanged();
        notifyObservers(score);
    }
    
    //decrease score
    public void decreaseScore(){
    	this.score--;
        setChanged();
        notifyObservers(score);
    }
    
    //reset score
    public void resetScore() {
    	this.score=0;
    	setChanged();
    	notifyObservers(score);
    }
    
    //getters and setters
    public String getLabel(){
       label="Score: "+score;
       return label;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
}
