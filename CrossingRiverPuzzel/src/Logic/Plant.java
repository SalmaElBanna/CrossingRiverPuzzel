/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.image.BufferedImage;

/**
 *
 * @author salma
 */
public abstract class Plant implements ICrosser {
    private double weight;
    private String label;
    @Override
    public boolean canSail() {
        return false;
    }

    @Override
    public double getWeight() {
        return this.weight;
        
    }

    @Override
    public int getEatingRank() {
       return 3;
    }

    @Override
    public BufferedImage[] getImages() {
        return null;
       
    }

    @Override
    public ICrosser makeCopy() {
       
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
