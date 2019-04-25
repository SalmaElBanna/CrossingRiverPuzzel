package GUI;



import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;


public class Sprite {
	
	private ImageView leftImageView;
	private ImageView rightImageView;
	
	private Image leftImage;
    private Image rightImage;
	private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    
    //Constructor using imageViews(example for characters)
    public Sprite(ImageView leftImageView,ImageView rightImageView) {
		this.leftImageView=leftImageView;
		this.rightImageView=rightImageView;
	}
    
    //Constructor using images(example for boats)
    public Sprite(Image image)
    {
     //this.leftImage=leftImage;
     //this.rightImage=rightImage;
     this.image=image;
     this.width=image.getWidth();
     this.height=image.getHeight();
    }
    
    //setting the position
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    
    //setting the size
    public void setWidth(double width) {
        this.width = width;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    //draw the image
     public void render(GraphicsContext gc) {
    	 gc.drawImage(image, positionX, positionY);
     }
     public void renderLeft(GraphicsContext gc)
     {
       gc.drawImage(leftImage, positionX, positionY);
     }
     
     public void renderRight(GraphicsContext gc)
     {
       gc.drawImage(rightImage, positionX, positionY);
     }
     
     /////////////draw the imageView////////////////////
     public void renderLeftImageView(Group group,int x,int y) {
    	 leftImageView.setLayoutX(x);
    	 leftImageView.setLayoutY(y);
		 group.getChildren().add(leftImageView);
     }
     
}
