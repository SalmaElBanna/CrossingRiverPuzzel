package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Goat extends Herbivore {
	
	private BufferedImage [] images=new BufferedImage[5];
	
	public Goat() {
		super(20);
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setImages(images);
	}
	
	public void loadImages() throws IOException {
		//left image
		images[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\goat-left2.png"));
		//left boat image
		images[1]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\goat-left2.png"));
		//right image
		images[2]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\goat-right2.png"));
		//right boat image
		images[3]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Characters\\\\goat-right2.png"));
	}
	
}
