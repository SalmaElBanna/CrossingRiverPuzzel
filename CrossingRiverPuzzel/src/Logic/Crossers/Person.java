/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Crossers;

import java.awt.image.BufferedImage;

/**
 *
 * @author salma
 */
public class Person implements ICrosser {
     private String label;
	private final double weight;
	
	
            public Person(double weight) {
		this.weight=weight;
	}
    
    
    
    @Override
	public boolean canSail() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEatingRank() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedImage[] getImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICrosser makeCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLabelToBeShown(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLabelToBeShown() {
		// TODO Auto-generated method stub
		return null;
	}
}
