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
public abstract class Carnivore implements ICrosser,MoreFunctionalities {
    private boolean canSail;
	private int eatingRank;
	private double weight;
	private BufferedImage[] images;
	private String label;
	private ICrosser carnivore;
	
	private List<ImageView> imageViews;
	
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