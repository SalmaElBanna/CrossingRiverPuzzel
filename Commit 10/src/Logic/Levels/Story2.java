
package Logic.Levels;

import Logic.Crossers.Herbivore;
import Logic.Crossers.Farmer;
import Logic.Crossers.Goat;
import Logic.Crossers.ICrosser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Story2 implements ICrossingStrategy {
        private List<ICrosser> initialCrossers=new ArrayList<>();
	    private String [] instructions=new String[5];
        private String  errorMessage;

        //Images
        private BufferedImage[] images1=new BufferedImage[5];
        private BufferedImage[] images2=new BufferedImage[5];
        private BufferedImage[] images3=new BufferedImage[5];
   
	/** Constructor sets initial Crossers of the game (Farmer1,Farmer2,Farmer3,Farmer4,goat)
	 *  and sets instructions
	 */
	public Story2() {
		try {
			loadImages1();
			loadImages2();
			loadImages3();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initialCrossers.add(new Farmer(60,images3));
        initialCrossers.add(new Farmer(80,images2));
        initialCrossers.add(new Farmer(40));
        initialCrossers.add(new Goat()); 
        initialCrossers.add(new Farmer(90,images1));
	    setInstructions();
	}

	@Override
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,
			List<ICrosser> boatRiders) {
		
		if(boatRiders.size()==0) {
            errorMessage="A person should steer the boat";
		    return false;
		}
		if(boatRiders.size()==2) {
			if(boatRiders.get(0).getWeight()+boatRiders.get(1).getWeight()>100) {
                errorMessage="The boat can hold a maximum of 100kg";
		     return false;
          }
		}
		
		if(boatRiders.size()==1) {
            if (!(boatRiders.get(0).canSail())){
                errorMessage="A person should steer the boat";
			return false;
            }
		}
		
		return true; 
	}


	@Override
	public List<ICrosser> getInitialCrossers() {
		return initialCrossers;
	}
	
	//setting the instructions of the game
	private void setInstructions() {
		int i=0;
        instructions[i++]="Four Farmers and their animal need to cross a river in a small boat.\nThe weights are 90kg,80kg,60,40kg respectively,and the weight of the animal is 20kg";
		instructions[i++]="The boat cannot bear a load heavier than 100kg.";
		instructions[i++]="All farmers can raft while the animal cannot.";
	}

	@Override
	public String [] getInstructions() {
		return instructions;
	}
	
    public String getErrorMessage() {
        return errorMessage;
    }
    
    //loading images 
    //images of farmer of weight 90
    private void loadImages1() throws IOException {
		//left image
		images1[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer1-left.png"));
		//left boat image
		images1[1]=images1[0];
		//right image
		images1[2]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer1-right.png"));
		//right boat image
		images1[3]=images1[2];
    }
    
    //images of farmer of weight 80
    private void loadImages2() throws IOException {
		//left image
		images2[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer2-left.png"));
		//left boat image
		images2[1]=images2[0];
		//right image
		images2[2]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer2-right.png"));
		//right boat image
		images2[3]=images2[2];
    }
    
    //images of farmer of weight 60
    private void loadImages3() throws IOException {
		//left image
		images3[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer3-left.png"));
		//left boat image
		images3[1]=images3[0];
		//right image
		images3[2]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer3-right.png"));
		//right boat image
		images3[3]=images3[2];
    }
	
}
