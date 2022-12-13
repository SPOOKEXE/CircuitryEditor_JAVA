package main.display.objects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.enumerations.ImageScaleType;

public class ImageLabel extends GuiObject {

	// Fields //
	Image rawImage;
	Image scaledImage;
	
	String imagePath;
	ImageScaleType scaleType;
	float imageTransparency;
	int absHashCode = -1;
	
	// Constructors //
	public ImageLabel() {
		this.setDefault();
	}

	// Class Methods //
	private void setDefault() {
		this.name = "ImageLabel";
		
		this.rawImage = null;
		this.scaledImage = null;
		
		this.imagePath = null;
		this.scaleType = ImageScaleType.STRETCH;
		this.imageTransparency = 0;
	}
	
	private void loadImage() {
		this.rawImage = null;
		try {
			File imgFile = new File(this.imagePath);
			if (!imgFile.exists()) {
				return;
			}
			this.rawImage = ImageIO.read(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		this.loadImage();
	}
	
	public Image getRawImage() {
		return this.rawImage;
	}
	
	public void setScaledImage(Image scaled) {
		this.scaledImage = scaled;
	}
	
	public Image getScaledImage() {
		return this.scaledImage;
	}
	
	public boolean hasSizeHashChanged(int hash) {
		return this.absHashCode != hash;
	}
	
	public void setSizeHash(int hash) {
		this.absHashCode = hash;
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
