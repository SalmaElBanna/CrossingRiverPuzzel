/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.text.html.ImageView;

/**
 *
 * @author salma
 */
public abstract class Herbivore implements ICrosser,MoreFunctionalities{
    private String label;
	private final double weight;
	private BufferedImage[] images;
	private List<ImageView> imageViews;
	
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
	
	//////Extra Functions than that in Interface///////////////
	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
	
	@Override
	public void setImageViews(List<ImageView> imageViews) {
		this.imageViews=imageViews;
	}
	
	public List<ImageView> getImageViews() {
		return imageViews;
	}
}
