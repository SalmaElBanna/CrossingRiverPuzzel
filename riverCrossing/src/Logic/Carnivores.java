package Logic;

import java.awt.image.BufferedImage;

public abstract class Carnivores implements ICrosser{
public boolean canSail;
public double Weight;
public BufferedImage[] Images;
public String Label;
ICrosser Carnivores;
    @Override
    public boolean canSail() {
        canSail=false;
        return canSail;
    }

    @Override
    public double getWeight() {
        return Weight;
    }

    @Override
    public int getEatingRank() {
        return 3;
    }

    @Override
    public BufferedImage[] getImages()
    {
        return Images;
    }

    @Override
    public ICrosser makeCopy() {
        return Carnivores;
    }
    
	@Override
	public void setLabelToBeShown(String label) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public String getLabelToBeShown() {
        return Label;
    }
    
}
