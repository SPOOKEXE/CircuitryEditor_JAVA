package main.display.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.enumerations.ImageScaleType;

public class ImageLabel extends GuiObject {

	// Fields //
	String imagePath;
	BufferedImage bufferedImage;
	ImageScaleType scaleType;
	float imageTransparency;
	
	// Constructors //
	public ImageLabel() {
		this.setDefault();
	}

	// Class Methods //
	private void setDefault() {
		this.name = "ImageLabel";
		this.imagePath = null;
		this.bufferedImage = null;
		this.scaleType = ImageScaleType.STRETCH;
		this.imageTransparency = 0;
	}
	
	private void updateBufferedImg() {
		this.bufferedImage = null;
		try {
			File imgFile = new File(this.imagePath);
			if (!imgFile.exists()) {
				return;
			}
			this.bufferedImage = ImageIO.read(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		this.updateBufferedImg();
	}
	
	public BufferedImage getBufferedImg() {
		return this.bufferedImage;
	}
	
	public ImageScaleType getImageScaleType() {
		return this.scaleType;
	}
	
	public void setImageScaleType(ImageScaleType scaleType) {
		this.scaleType = scaleType;
	}
	
	public float getImageTransparency() {
		return this.imageTransparency;
	}
	
	public void setImageTransparency(float transparency) {
		this.imageTransparency = transparency;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
