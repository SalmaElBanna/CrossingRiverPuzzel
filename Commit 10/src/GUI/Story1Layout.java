package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Logic.Controller;
import Logic.Receiver;
import Logic.Score;
import Logic.ScoreLabel;
import Logic.Crossers.Cabbage;
import Logic.Crossers.Farmer;
import Logic.Crossers.Goat;
import Logic.Crossers.ICrosser;
import Logic.Crossers.Wolf;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Story1Layout extends GameLayout{

	//related to scene
	private Stage stage;
	private Group group;
	
	//Buttons
	private Button undoButton;
	private Button redoButton;
	private Button moveButton;
	
	//Other
	private Receiver receiver=Receiver.getInstance();
	private Controller controller=new Controller();
	private List<ICrosser> leftCrossers;
	private List<ICrosser> rightCrossers;
	private List<ICrosser> boatRiders=new ArrayList<>();

	private List<List<ImageView>> listOfAllImageViews=new ArrayList<>();
	
	//Sprites
	private List<Sprite> sprites=new ArrayList<>();
	private List<Sprite> boatSprites=new ArrayList<>();
	private Sprite boat;
	
	//Score
	private Score score;
	private ScoreLabel scoreLabel;
	
	public Story1Layout(Stage stage,Dimension screenSize) throws IOException {
		super(stage,screenSize);
		this.group=super.getGroup();
		this.boat=super.getBoat();
		this.moveButton=super.getMoveButton();
		addCharacters();
		addLabel();
		setMoveButton();
		setEvents();
	}
	
	
	private void addLabel() {
        //adding score label
		this.score=receiver.getScore();
		scoreLabel=new ScoreLabel(score);
		scoreLabel.addLabel(group);
	}
	
	private void addCharacters() {
		//adding the characters
        int i =0;
        int x =50;
        int y =50;
        Image leftCharacterImage,leftCharacterBoatImage,rightCharacterImage,rightCharacterBoatImage;
        ImageView leftImageView,leftBoatImageView,rightImageView,rightBoatImageView;
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
            
            //getting the character's left boat image
            leftCharacterBoatImage = SwingFXUtils.toFXImage(images[1], null);
            leftBoatImageView = new ImageView(leftCharacterBoatImage);       
            
            //getting the character's right image
             rightCharacterImage = SwingFXUtils.toFXImage(images[2], null);
             rightImageView = new ImageView(rightCharacterImage);
             
             //getting the character's right boat image
             rightCharacterBoatImage = SwingFXUtils.toFXImage(images[2], null);
             rightBoatImageView = new ImageView(rightCharacterBoatImage);
             
       	     //////////Adding image views to the arrayList/////////////
            listOfAllImageViews.add(new ArrayList<ImageView>());
            listOfAllImageViews.get(i).add(leftImageView);
            listOfAllImageViews.get(i).add(leftBoatImageView);
            listOfAllImageViews.get(i).add(rightImageView);
            listOfAllImageViews.get(i).add(rightBoatImageView);
        	//////////////////////////////////////////////////////////
            
           //Drawing the character
            if(i==3) {
            	x+=120;
            	y+=100;
            }
            else {
                x+=20;
	            y+=100;
            }
            
            //Adjusting the y-position on boat
            int yBoat=310;
            switch(leftCrossers.get(i).getClass().getSimpleName()) {
            case"Farmer":yBoat=230;break;
            case"Wolf":break;
            case"Cabbage":yBoat=400;break;
            case "Goat":yBoat=370;break;
            }
            
            sprites.add(new Sprite(leftImageView,x,y,rightImageView,leftBoatImageView, rightBoatImageView,yBoat));
            sprites.get(i).renderLeftImageView(group);
            
        }
	}
	
	private void setMoveButton(){
		 //move button
   	 moveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				boolean check=receiver.isBoatOnLeftBank();
				if(receiver.canMove(boatRiders, check)) {
					receiver.doMove(boatRiders, check);
					//boat is on the left bank
					if(check==true) {
						boat.removeLeftImageView(group);
						boat.renderRightImageView(group);
						boatSprites.get(0).removeLeftBoatImageView(group);
						boatSprites.get(0).renderRightImageView(group);
						if(boatSprites.size()==2) {
							boatSprites.get(1).removeLeftBoatImageView(group);
							boatSprites.get(1).renderRightImageView(group);
						}	
					}
					//boat in on the right bank
					else {
				     	boat.removeRightImageView(group);
						boat.renderLeftImageView(group);
						boatSprites.get(0).removeRightBoatImageView(group);
						boatSprites.get(0).renderLeftImageView(group);
						if(boatSprites.size()==2) {
					    boatSprites.get(1).removeRightBoatImageView(group);
						boatSprites.get(1).renderLeftImageView(group);
						} 
					}
					
					boatRiders.clear();
					boatSprites.clear();
					rightCrossers=receiver.getCrossersOnRightBank();
					leftCrossers=receiver.getCrossersOnLeftBank();
				}
				else {
					showErrorMessage(receiver.getErrorMessage());
				}
				
			}
   		 
   	 });
	}
	
	 //setting events of story1
		private void setEvents() {
			/////////////////Character 1////////////////////////////////////////////////////////////////////
			//character 1: Goat	(position: left)
			listOfAllImageViews.get(0).get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()< 2 && receiver.isBoatOnLeftBank()) {
						sprites.get(0).removeLeftImageView(group);
						boatSprites.add(sprites.get(0));
						if(boatRiders.size()==0) {
							sprites.get(0).drawCharacter1Left(group);
						}
						else  
							sprites.get(0).drawCharacter2Left(group);
		
						for(int i=0;i<leftCrossers.size();i++) {
							if(leftCrossers.get(i) instanceof Goat) {
								crosser=leftCrossers.get(i);
								boatRiders.add(crosser);
								leftCrossers.remove(i);
								break;
					         }  
						}
					}
				}
				
			});	
			
			//character 1: Goat (position: left on boat)
			listOfAllImageViews.get(0).get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(0));
					sprites.get(0).removeLeftBoatImageView(group);
					sprites.get(0).renderLeftImageView(group);
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Goat) {
							crosser=boatRiders.get(i);
							leftCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
				}
				
			});
			
			//character 1: Goat	(position: right)
			listOfAllImageViews.get(0).get(2).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()< 2 && !receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(0));
						sprites.get(0).removeRightImageView(group);
						if(boatRiders.size()==0) {
							sprites.get(0).drawCharacter1Right(group);
						}
						else  
							sprites.get(0).drawCharacter2Right(group);
		
						for(int i=0;i<rightCrossers.size();i++) {
							if(rightCrossers.get(i) instanceof Goat) {
								crosser=rightCrossers.get(i);
								boatRiders.add(crosser);
								rightCrossers.remove(i);
								break;
					         }  
						}
					}
				}
				
			});	
			
			//character 1: Goat (position: right on boat)
			listOfAllImageViews.get(0).get(3).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					sprites.get(0).removeRightBoatImageView(group);
					sprites.get(0).renderRightImageView(group);
					boatSprites.remove(sprites.get(0));
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Goat) {
							crosser=boatRiders.get(i);
							rightCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
				}
				
			});
			//////////////////////////////////////////////////////////////////////////////////////////////
			
			///////////////Character 2///////////////////////////////////////////////////////////////////
			//character 2: Wolf (position: left)
			listOfAllImageViews.get(1).get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()<2 && receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(1));
						sprites.get(1).removeLeftImageView(group);
						if(boatRiders.size()==0) 
						   sprites.get(1).drawCharacter1Left(group);
						else
							 sprites.get(1).drawCharacter2Left(group);
						
						for(int i=0;i<leftCrossers.size();i++) {
							if(leftCrossers.get(i) instanceof Wolf) {
								crosser=leftCrossers.get(i);
								boatRiders.add(crosser);
								leftCrossers.remove(i);
								break;
							}
						}
					}
					
				}
				
			});
			
			//character 2: Wolf (position: left on boat)
			listOfAllImageViews.get(1).get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(1));
					sprites.get(1).removeLeftBoatImageView(group);
					sprites.get(1).renderLeftImageView(group);
					
					for(int i=0;i<leftCrossers.size();i++) {
						if(leftCrossers.get(i)instanceof Farmer) {
							sprites.get(2).removeLeftImageView(group);
							sprites.get(2).renderLeftImageView(group);
						}
							
					}
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Wolf) {
							crosser=boatRiders.get(i);
							leftCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
		
				}
				
			});
			
			//character 2: Wolf	(position: right)
			listOfAllImageViews.get(1).get(2).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()< 2 && !receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(1));
						sprites.get(1).removeRightImageView(group);
						if(boatRiders.size()==0) {
							sprites.get(1).drawCharacter1Right(group);
						}
						else  
							sprites.get(1).drawCharacter2Right(group);
		
						for(int i=0;i<rightCrossers.size();i++) {
							if(rightCrossers.get(i) instanceof Wolf) {
								crosser=rightCrossers.get(i);
								boatRiders.add(crosser);
								rightCrossers.remove(i);
								break;
					         }  
						}

					}
				}
				
			});	
			
			//character 2: Wolf (position: right on boat)
			listOfAllImageViews.get(1).get(3).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(1));
					sprites.get(1).removeRightBoatImageView(group);
					sprites.get(1).renderRightImageView(group);
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Wolf) {
							crosser=boatRiders.get(i);
							rightCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
				}
				
			});
	        //////////////////////////////////////////////////////////////////////////////////////////////
			
	        ///////////////Character 3///////////////////////////////////////////////////////////////////
			//character 3: Farmer (position: left)
			listOfAllImageViews.get(2).get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()<2 && receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(2));
						sprites.get(2).removeLeftImageView(group);
						if(boatRiders.size()==0) 
						   sprites.get(2).drawCharacter1Left(group);
						else
							 sprites.get(2).drawCharacter2Left(group);
						
						for(int i=0;i<leftCrossers.size();i++) {
							if(leftCrossers.get(i) instanceof Farmer) {
								crosser=leftCrossers.get(i);
								boatRiders.add(crosser);
								leftCrossers.remove(i);
								break;
							}
						}

					}
					
				}
				
			});
			
			//character 3: Farmer (position: left on boat)
			listOfAllImageViews.get(2).get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(2));
					sprites.get(2).removeLeftBoatImageView(group);
					sprites.get(2).renderLeftImageView(group);
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Farmer) {
							crosser=boatRiders.get(i);
							leftCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
					
				}
				
			});
			
			//character 3: Farmer	(position: right)
			listOfAllImageViews.get(2).get(2).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()< 2 && !receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(2));
						sprites.get(2).removeRightImageView(group);
						if(boatRiders.size()==0) {
							sprites.get(2).drawCharacter1Right(group);
						}
						else  
							sprites.get(2).drawCharacter2Right(group);
		
						for(int i=0;i<rightCrossers.size();i++) {
							if(rightCrossers.get(i) instanceof Farmer) {
								crosser=rightCrossers.get(i);
								boatRiders.add(crosser);
								rightCrossers.remove(i);
								break;
					         }  
						}

					}
				}
				
			});	
			
			//character 3: Farmer (position: right on boat)
			listOfAllImageViews.get(2).get(3).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(2));
					sprites.get(2).removeRightBoatImageView(group);
					sprites.get(2).renderRightImageView(group);
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Farmer) {
							crosser=boatRiders.get(i);
							rightCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
				}
				
			});
			//////////////////////////////////////////////////////////////////////////////////////////////

	         ///////////////Character 4///////////////////////////////////////////////////////////////////
			//character 4: Cabbage (position: left)
			listOfAllImageViews.get(3).get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()<2 && receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(3));
						sprites.get(3).removeLeftImageView(group);
						if(boatRiders.size()==0) 
						   sprites.get(3).drawCharacter1Left(group);
						else
							 sprites.get(3).drawCharacter2Left(group);
						
						for(int i=0;i<leftCrossers.size();i++) {
							if(leftCrossers.get(i) instanceof Cabbage) {
								crosser=leftCrossers.get(i);
								boatRiders.add(crosser);
								leftCrossers.remove(i);
								break;
							}
						}

					}
					
				}
				
			});
			
			//character 4: Cabbage (position: left on boat)
			listOfAllImageViews.get(3).get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(3));
					sprites.get(3).removeLeftBoatImageView(group);
					sprites.get(3).renderLeftImageView(group);
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Cabbage) {
							crosser=boatRiders.get(i);
							leftCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
				}
				
			});
			
			//character 4: Cabbage (position: right)
			listOfAllImageViews.get(3).get(2).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					if(boatRiders.size()< 2 && !receiver.isBoatOnLeftBank()) {
						boatSprites.add(sprites.get(3));
						sprites.get(3).removeRightImageView(group);
						if(boatRiders.size()==0) {
							sprites.get(3).drawCharacter1Right(group);
						}
						else  
							sprites.get(3).drawCharacter2Right(group);
		
						for(int i=0;i<rightCrossers.size();i++) {
							if(rightCrossers.get(i) instanceof Cabbage) {
								crosser=rightCrossers.get(i);
								boatRiders.add(crosser);
								rightCrossers.remove(i);
								break;
					         }  
						}
					}
				}
				
			});	
			
			//character 4: Cabbage (position: right on boat)
			listOfAllImageViews.get(3).get(3).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					ICrosser crosser;
					boatSprites.remove(sprites.get(3));
					sprites.get(3).removeRightBoatImageView(group);
					sprites.get(3).renderRightImageView(group);
					
					for(int i=0;i<boatRiders.size();i++) {
						if(boatRiders.get(i) instanceof Cabbage) {
							crosser=boatRiders.get(i);
							rightCrossers.add(crosser);
							boatRiders.remove(i);
							break;
				         }  
					}
				}
				
			});
	        //////////////////////////////////////////////////////////////////////////////////////////////
			
		}
	
}
