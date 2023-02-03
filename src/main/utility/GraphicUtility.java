package main.utility;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import main.math.Vector2int;

public class GraphicUtility {

	public static void SetBackgroundTransparency(Graphics2D graphics2D, float transparency) {
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-transparency);
		graphics2D.setComposite(alphaComposite);
	}
	
	public static void SetImageTransparency(Graphics2D graphics2D, float transparency) {
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-transparency);
		graphics2D.setComposite(alphaComposite);
	}
	
	public static Image StretchImageToSize(Image img, int w , int h) {
		BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	}
	
	public static Image StretchImageToSize(Image img, Vector2int size) {
		return GraphicUtility.StretchImageToSize(img, size.x, size.y);
	}
	
	public static Image ScaleAndFitImage(Image img, int maxWidth, int maxHeight, boolean useMinimumScale) {
		if (img == null) {
			return null;
		}
		
		float imgWidth = img.getWidth(null);
		float imgHeight = img.getHeight(null);
		
		float transformScaleX = 1;
		if (imgWidth > maxWidth) {
			transformScaleX = maxWidth / imgWidth;
		} else if (imgWidth < maxWidth) {
			transformScaleX = imgWidth / maxWidth;
		}
		
		float transformScaleY = 1;
		if (imgHeight > maxHeight) {
			transformScaleY = maxHeight / imgHeight;
		} else if (imgHeight < maxHeight) {
			transformScaleY = imgHeight / maxHeight;
		}
		
//		System.out.println(imgWidth + " - " + imgHeight);
//		System.out.println(maxWidth + " - " + maxHeight);
//		System.out.println(maxWidth / imgWidth + " - " + imgWidth / maxWidth);
//		System.out.println(maxHeight / imgHeight + " - " + imgHeight / maxHeight);
//		System.out.println(transformScaleX + " - " + transformScaleY);
		
		float scale = 1; // potentially return this for position scale
		if (useMinimumScale) {
			scale = Math.min(transformScaleX, transformScaleY);
		} else {
			scale = Math.max(transformScaleX, transformScaleY);
		}
		
		int scaledWidth = (int) (imgWidth * scale);
		int scaledHeight = (int) (imgHeight * scale);
		if (scaledWidth <= 0 || scaledHeight <= 0) {
			return null;
		}
		
		return img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	}
	
	public static Image ScaleAndFitImage(Image img, Vector2int size, boolean useMinimumScale) {
		return GraphicUtility.ScaleAndFitImage(img, size.x, size.y, useMinimumScale);
	}
	
}
