package Logic;

import java.awt.image.BufferedImage;

public class Person implements ICrosser {
	
	private final double weight;
	private String label;
	
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
		this.label=label;

	}

	@Override
	public String getLabelToBeShown() {
		// TODO Auto-generated method stub
		return null;
	}

}
