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

public class OldGameLayout {
	
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
	
	//Label
	private Label scoreLabel;
	
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

	
	public OldGameLayout(Stage stage,Dimension screenSize) throws IOException {
		this.stage=stage;
		this.screenSize=screenSize;
		loadBackgroundImage();
		loadBoatImages();
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
        
        //adding score label
        scoreLabel=new Label();
        scoreLabel.setText("Score: ");
        scoreLabel.setFont(Font.font(16));
        scoreLabel.setLayoutX(1000);
        scoreLabel.setLayoutY(10);
        group.getChildren().add(scoreLabel);
        
        //adding the boat
        ImageView boatLeft=new ImageView(leftBoatImage);
        ImageView boatRight=new ImageView(rightBoatImage);
        boat=new Sprite(boatLeft,boatRight);
        boat.renderLeftImageView(group);
        
        //add characters
        addCharacters();
		
        
        //Showing the instructions of the game to the user
        showInstructions();
	}
	
	public void addCharacters() {
		//adding the characters
        int i = 0;
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
            else if(i==4) {
            	x+=30;
            	y-=250;
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
    	
    	 //move button
    	 moveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				boolean check=receiver.isBoatOnLeftBank();
				
				if(receiver.canMove(boatRiders, true)) {
					receiver.doMove(boatRiders, true);
					//boat is on the left bank
					if(check==true) {
						
					}
					//boat in on the right bank
					//else {
				     	//boat.removeRightImageView(group);
						//boat.renderLeftImageView(group);
						//boatSprites.get(0).removeRightBoatImageView(group);
						//boatSprites.get(0).renderLeftImageView(group);
					//	if(boatSprites.size()==2) {
						//	boatSprites.get(1).removeRightBoatImageView(group);
						//	boatSprites.get(1).renderLeftImageView(group);
						//} 
					//}
					
					//if boat is on the left bank
					boat.removeLeftImageView(group);
					boat.renderRightImageView(group);
					boatSprites.get(0).removeLeftBoatImageView(group);
					boatSprites.get(1).removeLeftBoatImageView(group);
					boatSprites.get(0).renderRightImageView(group);
					boatSprites.get(1).renderRightImageView(group);
					//////////////////////////////////////////////
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
	public void setEvents1() {
		/////////////////Character 1////////////////////////////////////////////////////////////////////
		//character 1: Goat	(position: left)
		listOfAllImageViews.get(0).get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ICrosser crosser;
				if(boatRiders.size()< 2) {
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
				if(boatRiders.size()< 2) {
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
				if(boatRiders.size()<2) {
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
				if(boatRiders.size()< 2) {
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
				if(boatRiders.size()<2) {
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
				if(boatRiders.size()< 2) {
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
				if(boatRiders.size()<2) {
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
				if(boatRiders.size()< 2) {
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
	

	//setting the events of story2
	public void setEvents2() {
		
   /////////////////Character 1////////////////////////////////////////////////////////////////////
   //character 1: Farmer	(position: left)
   listOfAllImageViews.get(0).get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	@Override
	public void handle(MouseEvent event) {
		ICrosser crosser;
		if(boatRiders.size()< 2) {
			sprites.get(0).removeLeftImageView(group);
			boatSprites.add(sprites.get(0));
			if(boatRiders.size()==0) {
				sprites.get(0).drawCharacter1Left(group);
			}
			else  
				sprites.get(0).drawCharacter2Left(group);

			for(int i=0;i<leftCrossers.size();i++) {
				if(leftCrossers.get(i).getWeight()== 60) {
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
		if(boatRiders.size()< 2) {
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
		if(boatRiders.size()<2) {
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
		if(boatRiders.size()< 2) {
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
		if(boatRiders.size()<2) {
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
		if(boatRiders.size()< 2) {
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
		if(boatRiders.size()<2) {
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
		if(boatRiders.size()< 2) {
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
	
	
	public Scene getScene() {
		return scene;
	}
	
	public void setSettingsLayout(SettingsLayout settingsLayout) {
		this.settingsLayout = settingsLayout;
	}

}
