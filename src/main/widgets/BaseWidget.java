package main.widgets;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import main.display.objects.GuiBase;
import main.display.objects.GuiObject;
import main.display.objects.ImageLabel;
import main.display.variations.SimpleViewport;
import main.enumerations.ImageScaleType;
import main.enumerations.ZIndexSortType;
import main.input.UserInput;
import main.math.Vector2;

public class BaseWidget {

	// Fields //
	protected boolean initialized;
	protected boolean started;
	protected SimpleViewport baseCanvas;
	protected GuiBase baseGuiData;
	protected UserInput userInput;
	protected ArrayList<GuiObject> renderObjects;
	protected ArrayList<GuiObject> assortedRenderObjects;
	
	protected ZIndexSortType zIndexSortType;
	
	// Constructors //
	public BaseWidget() {
		this.initialized = false;
		this.started = false;
		this.baseGuiData = new GuiBase();
		this.baseCanvas = new SimpleViewport();
		this.userInput = new UserInput();
		this.renderObjects = new ArrayList<GuiObject>();
		this.assortedRenderObjects = null;
		this.zIndexSortType = ZIndexSortType.Global;
	}
	
	// Class Methods //
	private void updateAssortedRenders() {
		// SORTING ALGORITHM (SORT BY PARENT STRUCTURE, SORT BY ZINDEX)
	
		// * Firstly, we want to separate all parent/children hierarchies into their own HashMap.
		// * For every child under X parent, place them in an ArrayList given their zIndex as the HashMap Key.
		// * 
		// * Once all children have been places into this data structure;
		// * HashMap<GuiObject, HashMap<Integer, ArrayList<GuiObject>>>
		// * 
		// * We create a new ArrayList of GuiObjects that ascends in zIndexes (zIndex = 1, zIndex = 3, zIndex = 4, etc..) 
		// *
		// * After that, we sort those ArrayLists based on hierarchy, highest ancestors being first.
		// * 
		// * We then display all GuiObjects in that order since they are zIndex sorted, and heirarchically sorted.

		this.assortedRenderObjects = new ArrayList<GuiObject>();
		
		HashMap<GuiBase, HashMap<Integer, ArrayList<GuiObject>>> sortedParentZIndexBuffer = new HashMap<GuiBase, HashMap<Integer, ArrayList<GuiObject>>>();
		
		for (GuiObject object : this.renderObjects) {
			// if parent to nil or its ancestor is not a GuiBase, skip it
			if (object.isDescendantOf(null) || (!(object.getParent() instanceof GuiBase))) {
				continue;
			}
			 
			GuiBase objParent = object.getParent();
			
			// if there is no zIndex sorted HashMap, create it
			HashMap<Integer, ArrayList<GuiObject>> sortedChildren = sortedParentZIndexBuffer.get(objParent);
			if (sortedChildren == null) {
				sortedChildren = new HashMap<Integer, ArrayList<GuiObject>>();
				sortedParentZIndexBuffer.put(objParent, sortedChildren);
			}
			
			// if there is no array list for the zIndex HashMap, create it
			ArrayList<GuiObject> sortedZIndexes = sortedChildren.get( object.getZIndex() );
			if (sortedZIndexes == null) {
				sortedZIndexes = new ArrayList<GuiObject>();
				sortedChildren.put(object.getZIndex(), sortedZIndexes);
			}
			
			// add to ArrayList
			sortedZIndexes.add(object);
		}
		
		// "ArrayList of GuiObjects that ascends in zIndexes"
		// Convert to HashMap<GuiObject, ArrayList<GuiObject>> of sorted zIndex childrens given a parent GuiObject.
		HashMap<GuiBase, ArrayList<GuiObject>> sortedzIndexChildren = new HashMap<GuiBase, ArrayList<GuiObject>>();
		for (GuiBase ancestor : sortedParentZIndexBuffer.keySet()) {
			// https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
			TreeMap<Integer, ArrayList<GuiObject>> sorted = new TreeMap<Integer, ArrayList<GuiObject>>();
			sorted.putAll(sortedParentZIndexBuffer.get(ancestor));
			for (Map.Entry<Integer, ArrayList<GuiObject>> entry : sorted.entrySet()) {
				if ( sortedzIndexChildren.get(ancestor) == null ) {
					sortedzIndexChildren.put(ancestor, new ArrayList<GuiObject>());
				}
				sortedzIndexChildren.get(ancestor).addAll( entry.getValue() );
			}
		}
		
		// if sibling, relation to parent matters (so sort parents based on zIndex and heirarchical
		if (this.zIndexSortType == ZIndexSortType.Sibling) {
			
			// clear memory for the zIndexBuffer HashMap
			sortedParentZIndexBuffer = null;
			
			// TODO: Hierarchical sorting
			System.out.println("Hierarchical sorting is not implemented!");
			
			// clear memory for the sortedzIndexChildren
			sortedzIndexChildren = null;
			
			// add to assortedRenderObjects
			
			// clear memory for the hierarchical sorting
			
		} else {
			
			// shared zindex across the board
			for (Entry<GuiBase, ArrayList<GuiObject>> tempSorted : sortedzIndexChildren.entrySet()) {
				this.assortedRenderObjects.addAll(tempSorted.getValue());
			}
			
		}
	}
	
	
	public ArrayList<GuiObject> getAssortedRenders() {
		if (this.assortedRenderObjects == null) {
			this.updateAssortedRenders();
			// this.assortedRenderObjects = this.renderObjects;
		}
		
		return this.assortedRenderObjects;
	}
	
	public void setZIndexSortType(ZIndexSortType sortType) {
		if (this.zIndexSortType != sortType) {
			this.assortedRenderObjects = null;
		}
		this.zIndexSortType = sortType;
	}
	
	public void appendGuiObjects(GuiObject...guiObjects) {
		this.renderObjects.addAll( Arrays.asList(guiObjects) );
		this.assortedRenderObjects = null;
	}
	
	public void appendGuiObjects(ArrayList<GuiObject> guiObjects) {
		this.renderObjects.addAll(guiObjects);
		this.assortedRenderObjects = null;
	}
	
	public void removeGuiObject(GuiObject object) {
		if (this.renderObjects.remove(object)) {
			this.assortedRenderObjects = null;
		}
	}
	
	public void removeGuiObjects(GuiObject...guiObjects) {
		if (this.renderObjects.removeAll( Arrays.asList(guiObjects) )) {
			this.assortedRenderObjects = null;
		}
	}
	
	public void removeGuiObjects(ArrayList<GuiObject> guiObjects) {
		if (this.renderObjects.removeAll( guiObjects )) {
			this.assortedRenderObjects = null;
		}
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
	
	public void setWindowSize(Vector2 size) {
		this.baseCanvas.setSize(size);
		this.baseGuiData.setAbsoluteSize(size);
		for (GuiObject obj : this.renderObjects) {
			obj.updateAbsoluteSize();
		}
	}
	
	public void setWindowSize(int width, int height) {
		this.setWindowSize(new Vector2(width, height));
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
		
		// init
		this.baseCanvas.setSize( baseCanvas.getWindowSize() );
		
		BaseWidget edd = this;
		this.getViewportCanvas().getFrame().getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				edd.setWindowSize( edd.getViewportCanvas().getWindowSize() );
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
	
	private static void setBackgroundTransparency(Graphics2D graphics2D, float transparency) {
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-transparency);
		graphics2D.setComposite(alphaComposite);
	}
	
	private static void setImageTransparency(Graphics2D graphics2D, float transparency) {
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1-transparency);
		graphics2D.setComposite(alphaComposite);
	}
	
	public void drawObjects(Graphics2D g2d) {
		
		g2d.translate(0, 30); // control bar
		
		for (GuiObject rObject : this.getAssortedRenders()) {
			
			Vector2 absPosition = rObject.getAbsolutePosition();
			Vector2 absSize = rObject.getAbsoluteSize();
			
			// TODO: implement anchor point
			// TODO: implement clip descendants
			// TODO: implement aspect ratios
			
			// TODO: solve this problem, some reason doesn't auto update at start
			rObject.updateAbsolutePosition();
			rObject.updateAbsoluteSize();
			// //
			
			if (rObject.isOutlineEnabled()) {
				g2d.setColor(new Color(255,255,255));
				g2d.drawRect((int)absPosition.x, (int)absPosition.y, (int)absSize.x, (int)absSize.y);
			}
			
			if (rObject instanceof ImageLabel) {
				ImageLabel imgLabel = (ImageLabel) rObject;

				Image drawImage = imgLabel.getBufferedImg();
				if (drawImage == null) {
					continue;
				}
				
				float imageTransparency = imgLabel.getImageTransparency();
				
				// set transparency - https://zetcode.com/gfx/java2d/transparency/
				if (imageTransparency != 0) {
					BaseWidget.setImageTransparency(g2d, imageTransparency);
				}
				
				// TODO: make images scale with image label properly
				
				ImageScaleType scaleType = imgLabel.getImageScaleType();
				if (scaleType == ImageScaleType.STRETCH) {
					// stretch image to fit object
					Image scaled = drawImage.getScaledInstance((int)absSize.x, (int)absSize.y, 0);
					g2d.drawImage(scaled, (int)(absPosition.x), (int)(absPosition.y), (int)absSize.x, (int)absSize.y, null);
				}/* else if (scaleType == ImageScaleType.CROP) {
					// TODO: ScaleType:CROP
					// scale up to fit all dimensions
					float scaleFactor = Math.min(drawImage.getWidth(null)/absSize.x, drawImage.getHeight(null)/absSize.x);
					drawImage = drawImage.getScaledInstance((int)(absSize.x*scaleFactor), (int)(absSize.y*scaleFactor), 1);
				} else if (scaleType == ImageScaleType.FIT) {
					// TODO: ScaleType:FIT
					// scale down to fit all dimensions
					float scaleFactor = Math.max(drawImage.getWidth(null)/absSize.x, drawImage.getHeight(null)/absSize.x);
					// scale up to fit all dimensions
					drawImage = drawImage.getScaledInstance((int)(absSize.x*scaleFactor), (int)(absSize.y*scaleFactor), 1);
				}*/

				
				// reset transparency
				if (imageTransparency != 0) {
					BaseWidget.setImageTransparency(g2d, 0);
				}
				
			} else {
				
				float[] RGB = rObject.getBackgroundColor3().getRGB();
				float backgroundTransparency = rObject.getBackgroundTransparency();
				
				if (backgroundTransparency != 0) {
					BaseWidget.setBackgroundTransparency(g2d, backgroundTransparency);
				}
				
				g2d.setColor(new Color(RGB[0], RGB[1], RGB[2]));
				g2d.fillRect( (int)absPosition.x, (int)absPosition.y, (int)absSize.x, (int)absSize.y);
				
				if (backgroundTransparency != 0) {
					BaseWidget.setBackgroundTransparency(g2d, 0);
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
