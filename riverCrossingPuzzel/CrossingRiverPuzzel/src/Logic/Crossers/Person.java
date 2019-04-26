package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.text.html.ImageView;



public class Person implements ICrosser,MoreFunctionalities {
	
	private final double weight;
	private String label;
	private BufferedImage [] images;
	
	private List<ImageView> imageViews;
	
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

	@Override
	public void setImageViews(List<ImageView> imageViews) {
		this.imageViews=imageViews;
	}
	
	public List<ImageView> getImageViews() {
		return imageViews;
	}

}