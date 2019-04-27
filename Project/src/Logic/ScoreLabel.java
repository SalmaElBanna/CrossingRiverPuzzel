/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Observable;



/**
 *
 * @author salma
 */
public class ScoreLabel implements Observer{
Score model;

    public ScoreLabel(Score model) {
        this.model=model;
        model.addObserver((java.util.Observer) this);
    }

    @Override
    public void update(Score score, String label) {
        label=score.getLabel();
    }
  
}
