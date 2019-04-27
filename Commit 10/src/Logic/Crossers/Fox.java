package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fox extends Carnivore {

	private BufferedImage []images=new BufferedImage[5];
	
	public Fox() {
		super(4);
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setImages(images);
	}
	
	public void loadImages() throws IOException {
		//left image
		//images[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Farmers\\Farmer22-left.png"));
		//right image
		//images[1]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Farmers\\Farmer22-right.png"));
	}
    
}

