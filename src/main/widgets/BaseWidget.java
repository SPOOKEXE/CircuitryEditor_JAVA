package main.widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.display.variations.SimpleViewport;
import main.enumerations.ImageScaleType;
import main.enumerations.ZIndexSortType;
import main.input.UserInput;
import main.math.Vector2int;
import main.utility.GraphicUtility;
import main.widgets.managers.GuiObjectManager;
import main.widgets.objects.AspectRatioConstraint;
import main.widgets.objects.GuiBase;
import main.widgets.objects.GuiObject;
import main.widgets.objects.ImageLabel;
import main.widgets.objects.Instance;

public class BaseWidget {

	// Fields //
	protected boolean initialized;
	protected boolean started;
	protected SimpleViewport baseCanvas;
	protected GuiBase baseGuiData;
	protected UserInput userInput;
	protected GuiObjectManager guiObjectManager;
	
	// Constructors //
	public BaseWidget() {
		this.initialized = false;
		this.started = false;
		this.baseGuiData = new GuiBase();
		this.baseCanvas = new SimpleViewport();
		this.userInput = new UserInput();
		this.guiObjectManager = new GuiObjectManager(ZIndexSortType.Global);
	}
	
	// Class Methods //
	public ArrayList<GuiObject> getSortedRenders() {
		return this.guiObjectManager.getSorted();
	}
	
	public void setZIndexSortType(ZIndexSortType sortType) {
		if (this.guiObjectManager.getzIndexSortType() != sortType) {
			this.guiObjectManager.clearSorted();
		}
		this.guiObjectManager.setzIndexSortType(sortType);
	}
	
	public void appendGuiObjects(GuiObject...guiObjects) {
		this.guiObjectManager.appendGuiObjects(guiObjects);
	}
	
	public void appendGuiObjects(ArrayList<GuiObject> guiObjects) {
		this.guiObjectManager.appendGuiObjects(guiObjects);
	}

	public void removeGuiObjects(GuiObject...guiObjects) {
		this.guiObjectManager.removeGuiObjects(guiObjects);
	}
	
	public void removeGuiObjects(ArrayList<GuiObject> guiObjects) {
		this.guiObjectManager.removeGuiObjects(guiObjects);
	}
	
	public void setWindowTitle(String title) {
		this.baseCanvas.setTitle(title);
	}
	
	public boolean hasInitialized() {
		return this.initialized;
	}
	
	public boolean hasStarted() {
		return this.started;
	}
	
	public void setWindowSize(Vector2int size) {
		this.baseCanvas.setSize(size);
		this.baseGuiData.setAbsoluteSize(size);
		for (GuiObject obj : this.guiObjectManager.getUnsorted()) {
			obj.updateAbsolutes();
		}
	}
	
	public void setWindowSize(int width, int height) {
		this.setWindowSize(new Vector2int(width, height));
	}
	
	public SimpleViewport getViewportCanvas() {
		return this.baseCanvas;
	}
	
	public UserInput getUserInput() {
		return this.userInput;
	}
	
	public boolean Init() {
		if (this.initialized) {
			return false;
		}
		this.initialized = true;
		
		// initialize
		setWindowSize( this.baseCanvas.getWindowSize() );
		
		BaseWidget edd = this;
		this.getViewportCanvas().getFrame().getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension windowSize = edd.getViewportCanvas().getFrame().getSize();
				edd.setWindowSize( new Vector2int(windowSize.width, windowSize.height) );
				edd.Draw();
            }
        });
		
		return true;
	}
	
	public boolean Start() {
		if (this.started) {
			return false;
		}
		this.started = true;
		
		// start
		
		return true;
	}
	
	public boolean Stop() {
		if (!this.started) {
			return false;
		}
		this.started = false;
		
		// stop
		
		return true;
	}
	
	public void Update() {
		// only update if visible
		if (!this.baseCanvas.isVisible()) {
			return;
		}

		// on-update
	}
	
	public void drawObjects(Graphics2D g2d) {
		g2d.translate(0, 30); // control bar
		for (GuiObject rObject : this.guiObjectManager.getSorted()) {

			// TODO: implement anchor point
			// TODO: implement clip descendants + image clipping
			// TODO: implement parent-zIndex GuiObject sorting part (ZIndexSortType.Sibling)
			// TODO: implement 'ui-padding'
			
			Vector2int absPosition = rObject.getAbsolutePosition();
			Vector2int absSize = rObject.getAbsoluteSize();
			int absSizeHash = (absSize.x + "_" + absSize.y).hashCode();
			
			if (rObject.isOutlineEnabled()) {
				g2d.setColor(new Color(255,255,255));
				g2d.drawRect(absPosition.x, absPosition.y, absSize.x, absSize.y);
			}
			
			if (rObject instanceof ImageLabel) {
				ImageLabel imgLabel = (ImageLabel) rObject;

				float imageTransparency = imgLabel.getImageTransparency();
				
				// set transparency - https://zetcode.com/gfx/java2d/transparency/
				if (imageTransparency != 0) {
					GraphicUtility.SetImageTransparency(g2d, imageTransparency);
				}
				
				ImageScaleType scaleType = imgLabel.getImageScaleType();
				
				if (imgLabel.hasSizeHashChanged(absSizeHash)) {
					imgLabel.setSizeHash(absSizeHash);
					
					Image scaled = null;
					
					switch (scaleType) {
						case STRETCH:
							scaled = GraphicUtility.StretchImageToSize(imgLabel.getRawImage(), absSize);
							break;
						case FIT:
							scaled = GraphicUtility.ScaleAndFitImage(imgLabel.getRawImage(), absSize, true);
							break;
						case CROP:
							scaled = GraphicUtility.ScaleAndFitImage(imgLabel.getRawImage(), absSize, false);
							break;
						default:
							System.out.println("Unsupported ImageScaleType Enum: " + scaleType);
					}
					
					imgLabel.setScaledImage( scaled );
				}
				
				Image drawImage = imgLabel.getScaledImage();
				if (drawImage != null) {
					// TODO: get the scaled image to position in the top-middle of the frame (or have an anchor point for it)
					g2d.drawImage(drawImage, absPosition.x, absPosition.y, drawImage.getWidth(null), drawImage.getHeight(null), null);
				}
				
				// reset transparency
				if (imageTransparency != 0) {
					GraphicUtility.SetImageTransparency(g2d, 0);
				}
				
			} else {
				
				float[] RGB = rObject.getBackgroundColor3().getRGB();
				float backgroundTransparency = rObject.getBackgroundTransparency();
				
				if (backgroundTransparency != 0) {
					GraphicUtility.SetBackgroundTransparency(g2d, backgroundTransparency);
				}
				
				g2d.setColor(new Color(RGB[0], RGB[1], RGB[2]));
				g2d.fillRect( (int)absPosition.x, (int)absPosition.y, (int)absSize.x, (int)absSize.y);
				
				if (backgroundTransparency != 0) {
					GraphicUtility.SetBackgroundTransparency(g2d, 0);
				}
			}
			
		}
	}
	
	public void Draw() {
		// only draw if visible
		if (!this.baseCanvas.isVisible()) {
			return;
		}

		// draw current buffered image
		BufferedImage currentImage = this.baseCanvas.getBufferedImage();
		
		Graphics2D frame_g2d = this.baseCanvas.getJFrameGraphics2D();
		if (frame_g2d != null && currentImage != null) {
			frame_g2d.drawImage(currentImage, 0, 0, this.baseCanvas.getFrame());
		}
		
		// create next buffered image
		BufferedImage nextImage = this.baseCanvas.blankImage();
		
		Graphics g = nextImage.getGraphics();
		if (g == null) {
			return;
		}
		Graphics2D g2d = (Graphics2D) g;
		
		// draw GUI objects on top of next buffered image
		this.drawObjects(g2d);

		// set currentBuffered as the new image
		this.baseCanvas.setBufferedImage(nextImage);
	}
	
}
