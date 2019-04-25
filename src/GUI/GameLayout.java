package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Logic.Controller;
import Logic.Receiver;
import Logic.Crossers.Carnivore;
import Logic.Crossers.Herbivore;
import Logic.Crossers.ICrosser;
import Logic.Crossers.Person;
import Logic.Crossers.Plant;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameLayout {
	
	//related to scene
	private Stage stage;
	private Group group=new Group();
	private Scene scene=new Scene(group);
	private Dimension screenSize;
	
	//Images
	private Image backgroundImage;
	private Image leftBoatImage;
	private Image rightBoatImage;
	
	//Other
	private Receiver receiver=Receiver.getInstance();
	private Controller controller=new Controller();
	private List<ICrosser> leftCrossers;
	private List<ICrosser> rightCrossers;
	private List<ICrosser> boatRiders=new ArrayList<>();
	
	//Trial
	private List<List<ImageView>> listOfAllImageViews=new ArrayList<>();
	///////////////////////////////////////////
	
	public GameLayout(Stage stage,Dimension screenSize) throws IOException {
		this.stage=stage;
		this.screenSize=screenSize;
		loadBackgroundImage();
		loadBoatImages();
		setEvents();
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
        buttons(group);
        
        //adding the boat
		Sprite boat=new Sprite(leftBoatImage);
		boat.setPositionX(500);
		boat.setPositionY(300);
		boat.render(gc);
		
		//adding the characters
        int i = 0;
        int x =50;
        int y =50;
        Image leftCharacterImage,rightCharacterImage;
        ImageView leftImageView,rightImageView;
		leftCrossers = receiver.getCrossersOnLeftBank();
        for(i=0;i<leftCrossers.size();i++)
        {
            //getting buffered images array
            ICrosser found = leftCrossers.get(i);
            BufferedImage[] images = found.getImages();
   
            //changing from buffered image to javaFx image
            
            //getting the character's left image
            leftCharacterImage = SwingFXUtils.toFXImage(images[0], null);
            leftImageView = new ImageView(leftCharacterImage);
            
            /**getting the character's right image,but first check if right image is available as some
             * characters have only one image example: cabbage
             */
            if(images[1]!=null) {
                rightCharacterImage = SwingFXUtils.toFXImage(images[1], null);
                rightImageView = new ImageView(rightCharacterImage);
            }
            else {
            	rightImageView=leftImageView;
            }

       	     /////////////Trial//////////////////////////
            listOfAllImageViews.add(new ArrayList<ImageView>());
            listOfAllImageViews.get(i).add(leftImageView);
            listOfAllImageViews.get(i).add(rightImageView);
        	///////////////////////////////////////////
            
           //Drawing the character
            Sprite character=new Sprite(leftImageView,rightImageView);
            if(i==3) {
            	x+=120;
            	y+=100;
            }
            else if(i==4) {
            	x+=30;
            	y-=250;
          
            }
            else {
                x+=10;
	            y+=100;
            }
            character.renderLeftImageView(group, x, y);
            
            if(leftCrossers.get(i) instanceof Person) {
            	Person p=(Person)leftCrossers.get(i);
            	p.setImageViews(listOfAllImageViews.get(i));
            }
            else if(leftCrossers.get(i) instanceof Herbivore) {
            	Herbivore h=(Herbivore)leftCrossers.get(i);
            	h.setImageViews(listOfAllImageViews.get(i));
            }
            else if(leftCrossers.get(i) instanceof Carnivore) {
            	Carnivore c=(Carnivore)leftCrossers.get(i);
            	c.setImageViews(listOfAllImageViews.get(i));
            }
            else if(leftCrossers.get(i) instanceof Plant) {
            	Plant p=(Plant)leftCrossers.get(i);
            	p.setImageViews(listOfAllImageViews.get(i));
            }
        }
        
        
        //Showing the instructions of the game to the user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instructions");
        
        String instructions="Rules:\n";
        String[]array=receiver.getInstructions();
        alert.setHeaderText(array[0]);
        for(i=1;i<3;i++) {
            instructions+=(i)+".";
        	instructions+=array[i];
        	instructions+="\n";
        }
        alert.setContentText(instructions);
        alert.showAndWait();

	}	

	
    public void buttons(Group root)
    {
      //buttons
        Button button1 = new Button("Undo");
        button1.setLayoutX(400);
        button1.setLayoutY(50);
        Button button2 = new Button("Redo");
        button2.setLayoutX(500);
        button2.setLayoutY(50);
        Button button3 = new Button("Move");
        button3.setLayoutX(600);
        button3.setLayoutY(50);
        Button button4 = new Button("Reset Game");
        button4.setLayoutX(700);
        button4.setLayoutY(50);
        Button button5 = new Button("Exit");
        button5.setLayoutX(800);
        button5.setLayoutY(50);
        root.getChildren().addAll(button1,button2,button3,button4,button5);
    }
	
	public void setEvents() {
		

		
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
	
	public Scene getScene() {
		return scene;
	}

}
