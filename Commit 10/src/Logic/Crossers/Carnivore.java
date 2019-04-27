package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Carnivore implements ICrosser{
	
	private boolean canSail;
	private int eatingRank;
	private double weight;
	private BufferedImage[] images;
	private String label;
	private ICrosser carnivore;
	
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
	
    
}
