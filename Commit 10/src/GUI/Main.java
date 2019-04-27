package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main 
extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("River Crossing Puzzle");
		primaryStage.getIcons().add(new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Icons\\letter-r.png")));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		StartLayout startLayout=new StartLayout(primaryStage,screenSize);
	    MainMenuLayout mainMenuLayout=new MainMenuLayout(primaryStage,screenSize);
	    LevelsLayout levelsLayout=new LevelsLayout(primaryStage,screenSize);
	    
		startLayout.setMainMenuLayout(mainMenuLayout);
		mainMenuLayout.setLevelsLayout(levelsLayout);
		
		primaryStage.setScene(startLayout.getScene());
		primaryStage.show();
		primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		
	}

}
