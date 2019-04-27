package GUI;



import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;


public class Sprite {
	
	//related to imageViews (characters)
	private ImageView leftImageView;
	private final double positionXleft;
	private final double positionYleft;
	
	private ImageView rightImageView;
	private final double positionXright;
	private final double positionYright;
	
	private ImageView leftBoatImageView;
	//private final double positionXBoatLeft;
	
	private ImageView rightBoatImageView;
	//private final double positionXBoatRight;
	
	private final double positionYBoat;
	/////////////////////////////////////

	
	//related to normal images
	private Image leftImage;
    private Image rightImage;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    
    //Constructor for the boat
    public Sprite(ImageView leftImageView,ImageView rightImageView) {
    	//left image
		this.leftImageView=leftImageView;
		this.positionXleft=500;
		this.positionYleft=300;

		//right image
		this.rightImageView=rightImageView;
		this.positionXright=600;
		this.positionYright=300;
		
		//unused in this case
	    //this.positionXBoatLeft=0;
		this.positionYBoat=0;
		//this.positionXBoatRight=0;
    }
    
    //Constructor using imageViews(example for characters)
    public Sprite(ImageView leftImageView,double leftX,double leftY,ImageView rightImageView,ImageView leftBoatImageView,ImageView rightBoatImageView,double yBoat) {
    	//left image
		this.leftImageView=leftImageView;
		this.positionXleft=leftX;
		this.positionYleft=leftY;

		//right image
		this.rightImageView=rightImageView;
		this.positionXright=leftX+950;
		this.positionYright=leftY;
		
		//left boat image
		this.leftBoatImageView=leftBoatImageView;
		//this.positionXBoatLeft=0;
		this.positionYBoat=yBoat;
		
		//right boat image
		this.rightBoatImageView=rightBoatImageView;
		//this.positionXBoatRight=0;
	}
    
    //Constructor using images(example for boats)
  //  public Sprite(Image leftImage,Image rightImage)
    //{
    // this.leftImage=leftImage;
   //  this.rightImage=rightImage;
    // this.width=leftImage.getWidth();
     //this.height=rightImage.getHeight();
     
     //unused in this case
     //this.positionXleft=0;
     //this.positionYleft=0;
    // this.positionXright=0;
     //this.positionYright=0;
   // }
    
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
     public void renderLeft(GraphicsContext gc)
     {
       gc.drawImage(leftImage, positionX, positionY);
     }
     
     public void renderRight(GraphicsContext gc)
     {
       gc.drawImage(rightImage, positionX, positionY);
     }
     //delete the image
     public void deleteLeft(GraphicsContext gc) {
    	 
     }
     
     /////////////draw the imageView////////////////////
     public void renderLeftImageView(Group group) {
    	leftImageView.setLayoutX(positionXleft);
    	leftImageView.setLayoutY(positionYleft);
		group.getChildren().add(leftImageView);
     }
     
     public void renderRightImageView(Group group) {
    	 rightImageView.setLayoutX(positionXright);
    	 rightImageView.setLayoutY(positionYright);
		 group.getChildren().add(rightImageView);
     }
     ///////////////draw imageView on Boat//////////////
     public void drawCharacter1Left(Group group) {
    	 leftBoatImageView.setLayoutX(520);
    	 leftBoatImageView.setLayoutY(positionYBoat);
    	 group.getChildren().add(leftBoatImageView);
     }
     
     public void drawCharacter2Left(Group group) {
    	 leftBoatImageView.setLayoutX(645);
    	 leftBoatImageView.setLayoutY(positionYBoat);
    	 group.getChildren().add(leftBoatImageView);
     }
     
     public void drawCharacter1Right(Group group) {
    	 rightBoatImageView.setLayoutX(730);
    	 rightBoatImageView.setLayoutY(positionYBoat);
    	 group.getChildren().add(rightBoatImageView);
     }
     public void drawCharacter2Right(Group group) {
      	 rightBoatImageView.setLayoutX(600);
    	 rightBoatImageView.setLayoutY(positionYBoat);
    	 group.getChildren().add(rightBoatImageView);
     }
     
     
     //////////////remove the imageView////////////////
     public void removeLeftImageView(Group group) {
    	 group.getChildren().remove(leftImageView);
     }
     
     public void removeLeftBoatImageView(Group group) {
    	 group.getChildren().remove(leftBoatImageView);
     }
     
     public void removeRightImageView(Group group) {
    	 group.getChildren().remove(rightImageView);
     }
     
     public void removeRightBoatImageView(Group group) {
    	 group.getChildren().remove(rightBoatImageView);
     }
     
     /////////getters for imageViews///////////////
     public ImageView getLeftImageView() {
		return leftImageView;
	}
     
     public ImageView getRightImageView() {
		return rightImageView;
	}
}
