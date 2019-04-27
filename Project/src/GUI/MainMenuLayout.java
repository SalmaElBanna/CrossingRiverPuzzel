package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenuLayout {
	
	private Stage stage;
	private Group group=new Group();
	private Scene scene=new Scene(group);
	private Image image;
	private Dimension screenSize;
	
	//Buttons
	private Button newGameButton;
	private Button resumeButton;
	private Button exitButton;
	
	//Constructor
	public MainMenuLayout(Stage stage,Dimension screenSize) throws IOException {
		this.stage=stage;
		this.screenSize=screenSize;
		loadBackgroundImage();
		prepareScene();
		setEvents();
	}
	
	//preparing the scene
	public void prepareScene() {
		Background buttonBackground =new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY));
		Canvas canvas=new Canvas(screenSize.getWidth(),screenSize.getHeight()-70);
		group.getChildren().add(canvas);
		GraphicsContext gc=canvas.getGraphicsContext2D();
		gc.drawImage(image, 0, 0);
		
		//adding buttons
		newGameButton=new Button("New Game");
		newGameButton.setFont(Font.font(20));
		newGameButton.setMinWidth(200);
		newGameButton.setMinHeight(50);
		newGameButton.setBackground(buttonBackground);
		newGameButton.setLayoutX(600);
		newGameButton.setLayoutY(200);
		
	    resumeButton=new Button("Resume Game");
		resumeButton.setFont(Font.font(20));
		resumeButton.setMinWidth(200);
		resumeButton.setMinHeight(50);
		resumeButton.setBackground(buttonBackground);
		resumeButton.setLayoutX(600);
		resumeButton.setLayoutY(270);
		
		exitButton=new Button("Exit");
		exitButton.setFont(Font.font(20));
		exitButton.setMinWidth(200);
		exitButton.setMinHeight(50);
		exitButton.setBackground(buttonBackground);
		exitButton.setLayoutX(600);
		exitButton.setLayoutY(340);
		
		group.getChildren().addAll(newGameButton,resumeButton,exitButton);
		
	}
	
	//setting the buttons actions
	public void setEvents() {
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
					stage.close();
				}
			}
			
		});
	}
	
	//load background image
	public void loadBackgroundImage() throws IOException {
		//BufferedImage bufferedImage = ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\background.png"));
		//this.image=SwingFXUtils.toFXImage(bufferedImage, null);
		image=new Image("background2.png",screenSize.getWidth(),screenSize.getHeight(),false,false);
	}
	
	public Scene getScene() {
		return scene;
	}

}
