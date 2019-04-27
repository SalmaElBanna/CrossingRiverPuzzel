
package Logic;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ScoreLabel implements Observer{
	
    private Score model;
    private Label scoreLabel;
    
    public ScoreLabel(Score model) {
        this.model=model;
        model.addObserver( (java.util.Observer) this);
    }
    
    public void addLabel(Group group) {
        scoreLabel=new Label();
        scoreLabel.setText(model.getLabel());
        scoreLabel.setFont(Font.font(16));
        scoreLabel.setLayoutX(1000);
        scoreLabel.setLayoutY(10);
        group.getChildren().add(scoreLabel);
    }

	@Override
	public void update(Observable score, Object arg) {
		 scoreLabel.setText(model.getLabel());
	}
    
    
    
  
}
