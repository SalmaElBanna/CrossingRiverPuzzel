package Logic.Crossers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rabbit extends Herbivore {
	private BufferedImage []images=new BufferedImage[3];
	
	public Rabbit() {
		super(5);
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setImages(images);
	}
	
	public void loadImages() throws IOException {
		//left image
		images[0]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Rabbits\\rabbit.png"));
		//right image
		images[1]= ImageIO.read(new File("D:\\College\\4th term\\Programming II\\Projects\\Project 3\\src\\resources\\Rabbits\\rabbit-right.png"));
	}
    

}
