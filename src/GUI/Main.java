package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main 
extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("River Crossing Puzzle");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		StartLayout startLayout=new StartLayout(primaryStage,screenSize);
	    MainMenuLayout mainMenuLayout=new MainMenuLayout(primaryStage,screenSize);
	    
		startLayout.setMainMenuLayout(mainMenuLayout);
		
		primaryStage.setScene(startLayout.getScene());
		primaryStage.show();
		primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		
	}

}
