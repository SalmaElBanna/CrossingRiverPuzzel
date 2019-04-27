package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Logic.Controller;
import Logic.Receiver;
import Logic.Score;
import Logic.ScoreLabel;
import Logic.Crossers.Cabbage;
import Logic.Crossers.Carnivore;
import Logic.Crossers.Farmer;
import Logic.Crossers.Goat;
import Logic.Crossers.Herbivore;
import Logic.Crossers.ICrosser;
import Logic.Crossers.Person;
import Logic.Crossers.Plant;
import Logic.Crossers.Wolf;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameLayout {
	
	//related to scene
	private Stage stage;
	private Group group=new Group();
	private Scene scene=new Scene(group);
	private Dimension screenSize;
	
	//Layouts
	private SettingsLayout settingsLayout;
	
	//Images
	private Image backgroundImage;
	private Image leftBoatImage;
	private Image rightBoatImage;
	
	//Icons
	private Image undo;
	private Image redo;
	private Image settings;
	private Image move;
	
	//Buttons
	private Button undoButton;
	private Button redoButton;
	private Button moveButton;
	private Button settingsButton;
	
	//Other
	private Receiver receiver=Receiver.getInstance();
	private Controller controller=new Controller();
	
	//Score
	private Score score;
	private ScoreLabel scoreLabel;

	private Sprite boat;

	
	public GameLayout(Stage stage,Dimension screenSize) throws IOException {
		this.stage=stage;
		this.screenSize=screenSize;
		loadBackgroundImage();
		loadBoatImages();
		prepareScene();
	}
	
	public void prepareScene() {
		//adding the canvas    
	    Canvas canvas = new Canvas(screenSize.getWidth(),screenSize.getHeight()-70);
	    group.getChildren().add(canvas);
	    
	    //getting the graphics context
	    GraphicsContext gc=canvas.getGraphicsContext2D();
	    
        //adding the background
    	gc.drawImage(backgroundImage, 0, 0);  
    	
    	//adding the buttons
        addButtons(group);
        setButtonsActions();

        //adding the boat
        ImageView boatLeft=new ImageView(leftBoatImage);
        ImageView boatRight=new ImageView(rightBoatImage);
        boat=new Sprite(boatLeft,boatRight);
        boat.renderLeftImageView(group);
        
        //Showing the instructions of the game to the user
        showInstructions();
	}
	

	public void showInstructions() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instructions");
        String instructions="Rules:\n";
        String[]array=receiver.getInstructions();
        alert.setHeaderText(array[0]);
        for(int i=1;i<3;i++) {
            instructions+=(i)+".";
        	instructions+=array[i];
        	instructions+="\n";
        }
        alert.setContentText(instructions);
        alert.showAndWait();
	}
	
	public void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Move");
        alert.setContentText(message);
        alert.showAndWait();
	}
	
    public void addButtons(Group group)
    {
    	//loading the icons images
    	try {
			loadIcons();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	/////ImageViews of Icons/////////////
    	ImageView undoIcon=new ImageView(undo);
    	ImageView redoIcon=new ImageView(redo);
    	ImageView settingsIcon= new ImageView(settings);
    	ImageView moveIcon=new ImageView(move);
    	
        //////////Buttons/////////////////////
    	//Undo Button
        undoButton = new Button();
        undoButton.setLayoutX(60);
        undoButton.setLayoutY(10);
        undoButton.setGraphic(undoIcon);
        undoButton.setDisable(true);
        
        //Redo Button
        redoButton = new Button();
        redoButton.setLayoutX(110);
        redoButton.setLayoutY(10);
        redoButton.setGraphic(redoIcon);
        redoButton.setDisable(true);
        
        //Move Button
        moveButton = new Button("Let's Go");
        moveButton.setGraphic(moveIcon);
        moveButton.setLayoutX(1050);
        moveButton.setLayoutY(620);
        
        //Settings Button
        settingsButton = new Button();
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setLayoutX(10);
        settingsButton.setLayoutY(10);
        
        group.getChildren().addAll(undoButton,redoButton,moveButton,settingsButton);
    }
    
    public void setButtonsActions() {
    	
    	//settings button
    	 settingsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				settingsLayout.display();
			}
    		 
    	 });
    	 
    }
	
 
	
	
	//load background image
	public void loadBackgroundImage() throws IOException {
		backgroundImage=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\background.png")
				,screenSize.getWidth(),screenSize.getHeight(),false,false);
	}
	
	
	//load boat image
	public void loadBoatImages() throws IOException {
		leftBoatImage=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\boat-left.png"));
		rightBoatImage=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\boat-right.png"));
	}
	
	//load icons 
	public void loadIcons() throws IOException {
		undo=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Icons\\undo.png"));
		redo=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Icons\\redo.png"));
		settings=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Icons\\gear.png"));
		move=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Icons\\arrow-up.png"));
	}
	
	//getters and setters
	public Scene getScene() {
		return scene;
	}
	
	public void setSettingsLayout(SettingsLayout settingsLayout) {
		this.settingsLayout = settingsLayout;
	}
	///////////////Trial///////////////////////////////
	public Group getGroup() {
		return group;
	}
	
	public Sprite getBoat() {
		return boat;
	}
	
	public Button getMoveButton() {
		return moveButton;
	}

}