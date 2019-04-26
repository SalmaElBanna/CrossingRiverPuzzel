package GUI;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Screen {
	private GraphicsDevice gc;
	
	public Screen() {
		GraphicsEnvironment environment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		//access to our graphics card
		gc=environment.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm,JFrame window) {
		window.setUndecorated(true);
		window.setResizable(false);
		gc.setFullScreenWindow(window);
		
		if(dm!=null && gc.isDisplayChangeSupported()) {
			try {
				gc.setDisplayMode(dm);
				
			}catch(Exception e) {
				
			}
		}
		
	}
}
