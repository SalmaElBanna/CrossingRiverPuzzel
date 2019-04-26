/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author salma
 */
public class ScoreLabel implements Observer{
Score label;

    public ScoreLabel(Score label) {
        this.label=label;
        label.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }
    
}
