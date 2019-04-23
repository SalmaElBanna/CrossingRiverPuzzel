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
public abstract class Carnivore implements ICrosser {
    	public boolean canSail;
	public int eatingRank;
	public double weight;
	public BufferedImage[] images;
	public String label;
	ICrosser carnivore;
	
	public Carnivore(double weight) {
		this.weight=weight;
	}

    @Override
    public boolean canSail() {
        canSail=false;
        return canSail;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getEatingRank() {
        eatingRank=3;
        return eatingRank;
    }

    @Override
    public BufferedImage[] getImages()
    {
        return images;
    }

    @Override
    public ICrosser makeCopy() {
        return carnivore;
    }
    
	@Override
	public void setLabelToBeShown(String label) {
		this.label=label;
	}

    @Override
    public String getLabelToBeShown() {
        return label;
    }
    
}