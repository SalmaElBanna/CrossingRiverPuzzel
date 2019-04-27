package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.image.ImageView;

public class Person implements ICrosser {
	
	private final double weight;
	private String label;
	private BufferedImage [] images;
	
	public Person(double weight) {
		this.weight=weight;
	}

	@Override
	public boolean canSail() {
		// person can sail
		return true;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public int getEatingRank() {
		return -1;
	}

	@Override
	public BufferedImage[] getImages() {
		return images;
	}

	@Override
	public ICrosser makeCopy() {
		// TODO Auto-generated method stub
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


}
