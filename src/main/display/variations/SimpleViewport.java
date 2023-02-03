package main.display.variations;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import main.math.Vector2int;
import main.display.Display;
import main.display.IDisplay;

public class SimpleViewport extends Display implements IDisplay {
	
	// Fields //
	protected BufferedImage currentBuffered;
	
	// Constructors //
	public SimpleViewport() {
		this.blankImage();
	}
	
	// CLASS METHODS //
	public BufferedImage blankImage() {
		int sizeX = (this.getWidth() <= 0) ? 1 : this.getWidth();
		int sizeY = (this.getHeight() <= 0) ? 1 : this.getHeight();
		return new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void init() { 
		super.init();
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public BufferedImage getBufferedImage() {
		return this.currentBuffered;
	}
	
	public void setBufferedImage(BufferedImage newBufferedImage) {
		this.currentBuffered = newBufferedImage;
	}
	
	@Override
	public void setSize(Vector2int viewportSize) {
		super.setSize(viewportSize);
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
	}

//	@Override
//	public void setFPS(int FPS) {
//		super.setFPS(FPS);
//	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	@Override
	public JFrame getFrame() {
		return super.getFrame();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

//	@Override
//	public int getFPS() {
//		return super.getFPS();
//	}

	@Override
	public boolean isVisible() {
		return super.isVisible();
	}
	
}
