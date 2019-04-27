package GUI;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import Logic.Receiver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsLayout {
	
	private Stage window;
	private Stage primaryStage;
	private Scene scene;
	
	//Buttons
	private Button resumeButton;
	private Button restartButton;
	private Button instructionsButton;
	private Button exitButton;
	
	//Layouts
	private MainMenuLayout mainMenuLayout;
	private GameLayout gameLayout;
	private LevelsLayout levelsLayout;
	
	//Receiver
	Receiver receiver=Receiver.getInstance();
	
	public SettingsLayout(Stage primaryStage,Dimension screenSize) {
		this.primaryStage=primaryStage;
		try {
			levelsLayout=new LevelsLayout(primaryStage,screenSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void display()
	{
		window=new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Settings");
		try {
			window.getIcons().add(new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Icons\\gear (1).png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		VBox layout=new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(20);

		addButtons(layout);
		setButtonsActions();
		
		scene=new Scene(layout,400,300);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void addButtons(VBox layout) {
		Background buttonBackground =new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY));
		
		//resume Button
		resumeButton=new Button("Resume Game");
		resumeButton.setBackground(buttonBackground);
		resumeButton.setFont(Font.font(16));
		resumeButton.setMinWidth(120);
		resumeButton.setMinHeight(50);
		
		//restart Button
		restartButton=new Button("Restart Game");
		restartButton.setBackground(buttonBackground);
		restartButton.setFont(Font.font(16));
		restartButton.setMinWidth(120);
		restartButton.setMinHeight(50);
		
		//instructions Button
		instructionsButton=new Button("Instructions");
		instructionsButton.setBackground(buttonBackground);
		instructionsButton.setFont(Font.font(16));
		instructionsButton.setMinWidth(120);
		instructionsButton.setMinHeight(50);
		
		//exit Button
		exitButton=new Button("Exit");
		exitButton.setBackground(buttonBackground);
		exitButton.setFont(Font.font(16));
		exitButton.setMinWidth(120);
		exitButton.setMinHeight(50);
		
		layout.getChildren().addAll(resumeButton,restartButton,instructionsButton,exitButton);
		
	}
	
	public void setButtonsActions() {
		
		//Resume Button
		resumeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				window.close();
			}
		});
		
			
		//Instructions Button
		instructionsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				gameLayout.showInstructions();
			}
			
		});
		
		//Exit Button
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.NONE);
				alert.setContentText("Are you sure you want to exit?");
				alert.setTitle("Exit");
				
				ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
				ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);
				
				alert.getButtonTypes().setAll(yesButton,noButton);
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get()==yesButton) {
					mainMenuLayout.setLevelsLayout(levelsLayout);
					primaryStage.setScene(mainMenuLayout.getScene());
					window.close();
				}	
			}
			
		});
	}
	
	//setting Layouts
	public void setMainMenuLayout(MainMenuLayout mainMenuLayout) {
		this.mainMenuLayout = mainMenuLayout;
	}
	
	public void setGameLayout(GameLayout gameLayout) {
		this.gameLayout = gameLayout;
	}


}
