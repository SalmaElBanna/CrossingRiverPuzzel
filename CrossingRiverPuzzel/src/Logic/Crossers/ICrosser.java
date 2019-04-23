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
public interface ICrosser {

public boolean canSail();

public double getWeight();

public int getEatingRank();

public BufferedImage[] getImages();

public ICrosser makeCopy();
public void setLabelToBeShown(String label);

public String getLabelToBeShown();
}
