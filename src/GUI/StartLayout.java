package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartLayout implements EventHandler<ActionEvent> {
	private Stage stage;
	private Group group=new Group();
	private Scene scene=new Scene(group);
	private Image image;
	private Dimension screenSize;
	
	private MainMenuLayout mainMenuLayout;
	
	private Button button;
	
	public StartLayout(Stage stage,Dimension screenSize) throws IOException{
		this.stage=stage;
		this.screenSize=screenSize;
		prepareScene();
		
	}

	public void prepareScene() throws IOException {
		Canvas canvas=new Canvas(screenSize.getWidth(),screenSize.getHeight()-70);
		group.getChildren().add(canvas);
		GraphicsContext gc=canvas.getGraphicsContext2D();
		loadBackgroundImage();
		gc.drawImage(image, 0, 0);
		
		//adding label
		Label label=new Label("River Crossing Puzzle");
		label.setFont(Font.font(36));
		label.setTextFill(Color.BLACK);
		label.setLayoutX(530);
		label.setLayoutY(200);
		group.getChildren().add(label);
		
		//adding play button
	    button=new Button("Play");
		button.setFont(Font.font(20));
		button.setMinWidth(150);
		button.setMinHeight(50);
		button.setLayoutX(600);
		button.setLayoutY(500);
		button.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
		group.getChildren().add(button);
		button.setOnAction(this);

	}
	
	public void loadBackgroundImage() throws IOException {
		image=new Image(new FileInputStream("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\background.png")
				,screenSize.getWidth(),screenSize.getHeight(),false,false);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setMainMenuLayout(MainMenuLayout mainMenuLayout) {
		this.mainMenuLayout = mainMenuLayout;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==button)
			stage.setScene(mainMenuLayout.getScene());
	}

}
