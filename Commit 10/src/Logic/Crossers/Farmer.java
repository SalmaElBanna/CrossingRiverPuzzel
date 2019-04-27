package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Farmer extends Person {
	private BufferedImage []images=new BufferedImage[5];
	
	public Farmer(double weight,BufferedImage []images) {
		super(weight);
		this.images=images;
		super.setImages(images);
	}
	
	public Farmer(double weight) {
		super(weight);
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setImages(images);
	}
	
	public void loadImages() throws IOException {
		//left image
		images[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer-left.png"));
		//left boat image
		images[1]=images[0];
		//right image
		images[2]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\Farmer-right.png"));
		//right boat image
		images[3]=images[2];
	}

}
