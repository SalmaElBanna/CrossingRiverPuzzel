package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Herbivore implements ICrosser,MoreFunctionalities {
	
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
