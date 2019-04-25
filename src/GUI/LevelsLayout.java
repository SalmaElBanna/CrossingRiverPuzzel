package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Logic.Controller;
import Logic.Receiver;
import Logic.Commands.NewGameCommand;
import Logic.Levels.Story1;
import Logic.Levels.Story2;
import Logic.Levels.StrategySetter;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LevelsLayout {
	
	private Stage stage;
	private Group group=new Group();
	private Scene scene=new Scene(group);
	private Dimension screenSize;
	
	//Images
	private Image backgroundImage;
	private Image story1Image;
	private Image story2Image;
	
	//ImageViews
	private ImageView image1;
	private ImageView image2;
	
	//Layouts
	private GameLayout gameLayout;
	
	//Constructor 
	public LevelsLayout(Stage stage,Dimension screenSize) throws IOException {
		this.stage=stage;
		this.screenSize=screenSize;
		loadBackgroundImage();
		loadStoryImages();
		prepareScene();
		setEvents();
	}
	
	//preparing the scene
	public void prepareScene() {
		//adding the canvas
		Canvas canvas=new Canvas(screenSize.getWidth(),screenSize.getHeight()-70);
		group.getChildren().add(canvas);
		
		//getting the graphics context
		GraphicsContext gc=canvas.getGraphicsContext2D();
		
		/////////////Drawing////////////////
		gc.drawImage(backgroundImage, 0, 0);
		
		//drawing image of story 1
		image1=new ImageView(story1Image);
		image1.setLayoutX(200);
		image1.setLayoutY(200);
		group.getChildren().add(image1);
		
		//drawing image of story 2
		image2=new ImageView(story2Image);
		image2.setLayoutX(600);
		image2.setLayoutY(200);
		group.getChildren().add(image2);
		
		//adding the label
		Label label=new Label("Select a story");
		label.setFont(Font.font(35));
		label.setTextFill(Color.BLACK);
		label.setLayoutX(620);
		label.setLayoutY(70);
		group.getChildren().add(label);
	}
	
	
	//setting the events
	public void setEvents() {
		Receiver receiver=Receiver.getInstance();
		StrategySetter setter=new StrategySetter();
		Controller controller=new Controller();
		
		//story 1
		image1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
         	@Override
			public void handle(MouseEvent event) {
         		setter.setStrategy(new Story1());
        		controller.setCommand(new NewGameCommand(receiver,setter.getStrategy()));
         		controller.performCommand();
         		gameLayout.prepareScene();
         		stage.setScene(gameLayout.getScene());
			}
			
		});
		
		//story 2
		image2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
         	@Override
			public void handle(MouseEvent event) {
         		setter.setStrategy(new Story2());
        	    controller.setCommand(new NewGameCommand(receiver,setter.getStrategy()));
         		controller.performCommand();
         		gameLayout.prepareScene();
				stage.setScene(gameLayout.getScene());
			}
			
		});
	}
	
	//load storyImages
	public void loadStoryImages() throws IOException{
		BufferedImage bufferedImage1 = ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\story1.png"));
		story1Image=SwingFXUtils.toFXImage(bufferedImage1, null);
		
		BufferedImage bufferedImage2 = ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\story2.png"));
		story2Image=SwingFXUtils.toFXImage(bufferedImage2, null);
	}
	
	//load background image
	public void loadBackgroundImage() throws IOException {
		backgroundImage=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\background.png")
				,screenSize.getWidth(),screenSize.getHeight(),false,false);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setGameLayout(GameLayout gameLayout) {
		this.gameLayout = gameLayout;
	}

}
