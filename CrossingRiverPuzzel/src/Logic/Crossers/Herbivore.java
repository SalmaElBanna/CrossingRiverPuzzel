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
public abstract class Herbivore implements ICrosser{
    private String label;
	private final double weight;
	private BufferedImage[] images;
	
	public Herbivore(double weight) {
		this.weight=weight;
	}

	@Override
	public boolean canSail() {
		// animals can't sail
		return false;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public int getEatingRank() {
		// returns eating rank as integer
		return 2;
	}

	@Override
	public BufferedImage[] getImages() {
		return images;
	}

	@Override
	public ICrosser makeCopy() {
		return null;
	}

	@Override
	public void setLabelToBeShown(String label) {
		this.label=label;

	}

	@Override
	public String getLabelToBeShown() {
		return this.label;
	}
}
