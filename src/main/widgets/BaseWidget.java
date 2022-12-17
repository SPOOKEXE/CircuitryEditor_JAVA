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
import java.util.HashMap;

import main.display.variations.SimpleViewport;
import main.enumerations.ImageScaleType;
import main.enumerations.ZIndexSortType;
import main.input.Keyboard;
import main.input.Mouse;
import main.input.UserInput;
import main.math.Intersects;
import main.math.Vector2;
import main.math.Vector2int;
import main.signal.SignalListener;
import main.utility.GraphicUtility;
import main.widgets.events.GuiEvents;
import main.widgets.managers.GuiObjectManager;
import main.widgets.objects.GuiBase;
import main.widgets.objects.GuiObject;
import main.widgets.objects.ImageLabel;

public class BaseWidget {

	// Fields //
	protected boolean initialized;
	protected boolean started;
	
	protected SimpleViewport baseCanvas;
	protected GuiBase baseGuiData;
	protected UserInput userInput;
	protected GuiObjectManager guiObjectManager;
	protected GuiEvents widgetEvents;
	
	// Constructors //
	public BaseWidget() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.initialized = false;
		this.started = false;
		
		this.baseGuiData = new GuiBase();
		this.baseCanvas = new SimpleViewport();
		this.userInput = new UserInput();
		this.guiObjectManager = new GuiObjectManager(ZIndexSortType.Global);
		
		this.userInput.setupListeners(this.baseCanvas.getFrame()); // setup listeners
		this.widgetEvents = new GuiEvents();
	}
	
	public ArrayList<GuiObject> getSortedRenders() {
		return this.guiObjectManager.getSorted();
	}
	
	public ArrayList<GuiObject> getUnsortedRenders() {
		return this.guiObjectManager.getUnsorted();
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
	
	public ArrayList<GuiObject> getGuiObjectsAtMouseXY(Vector2int mouseXY) {
		// TODO: getGuiObjectsAtMouseXY >> check anchor point
		ArrayList<GuiObject> objects = new ArrayList<GuiObject>();
		for (GuiObject guiObj : this.getSortedRenders()) {
			Vector2int absP = guiObj.getAbsolutePosition();
			Vector2int absS = guiObj.getAbsoluteSize();
			if (Intersects.PointInSquare( mouseXY, absP, absP.add( absS ))) {
				objects.add(guiObj);
			}
		}
		return objects;
	}
	
	public ArrayList<GuiObject> getGuiObjectsAtMouseXY(int mouseX, int mouseY) {
		return getGuiObjectsAtMouseXY(new Vector2int(mouseX, mouseY));
	}
	
	public ArrayList<GuiObject> getGuiObjectsAtActiveMouse() {
		Mouse mouse = this.getUserInput().getMouse();
		return this.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
	}
	
	public boolean Init() {
		if (this.initialized) {
			return false;
		}
		this.initialized = true;
		
		// initialize
		setWindowSize( this.baseCanvas.getWindowSize() );
		
		BaseWidget self = this;
		
		this.getViewportCanvas().getFrame().getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension windowSize = self.getViewportCanvas().getFrame().getSize();
				self.setWindowSize( new Vector2int(windowSize.width, windowSize.height) );
				self.Draw();
            }
        });
		
		Mouse mouseObj = this.getUserInput().getMouse();
		Keyboard keyboard = this.getUserInput().getKeyboard();
		
		mouseObj.onMouseMove(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				ArrayList<GuiObject> uiObjs = getGuiObjectsAtActiveMouse();
				
				// handle mouse enter, move, and leave of GuiObjects
				GuiObject hovered = uiObjs.size() != 0 ? uiObjs.get( uiObjs.size() - 1 ) : null;
				if (hovered != null) {
					self.widgetEvents.onMouseLeave.Fire(args);
					Object mouseHoveredObject = this.data.get("Hovered");
					if (mouseHoveredObject != null) {
						((GuiObject)mouseHoveredObject).getGuiEvents().onMouseMove.Fire(args);
					}
					if (mouseHoveredObject != hovered) {
						if (mouseHoveredObject != null) {
							((GuiObject)mouseHoveredObject).getGuiEvents().onMouseLeave.Fire(args);
						}
						hovered.getGuiEvents().onMouseEnter.Fire(args);
						this.data.put("Hovered", hovered);
					}
					hovered.getGuiEvents().onMouseMove.Fire(args);
					return;
				}
				
				Object mouseHoveredObject = this.data.get("Hovered");
				if (mouseHoveredObject != null) {
					((GuiObject)mouseHoveredObject).getGuiEvents().onMouseLeave.Fire(args);
					self.widgetEvents.onMouseEnter.Fire(args);
				}
				
				this.data.put("Hovered", null);
				self.widgetEvents.onMouseMove.Fire(args);
			}
		});
		
		mouseObj.onMouse1Down(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouse1Down.Fire(args);
					return;
				}
				self.widgetEvents.onMouse1Down.Fire();
			}
		});
		
		mouseObj.onMouse1Up(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouse1Up.Fire(args);
					return;
				}
				self.widgetEvents.onMouse1Up.Fire();
			}
		});
		
		mouseObj.onMouse2Down(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouse2Down.Fire(args);
					return;
				}
				self.widgetEvents.onMouse2Down.Fire();
			}
		});
		
		mouseObj.onMouse2Up(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouse2Up.Fire(args);
					return;
				}
				self.widgetEvents.onMouse2Up.Fire();
			}
		});
		
		mouseObj.onMouseScrollDown(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouseScrollDown.Fire(args);
					return;
				}
//				System.out.println("mouse scroll down");
				self.widgetEvents.onMouseScrollDown.Fire(args);
			}
		});
		
		mouseObj.onMouseScrollUp(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouseScrollUp.Fire(args);
					return;
				}
//				System.out.println("mouse scroll up");
				self.widgetEvents.onMouseScrollUp.Fire(args);
			}
		});
		
		mouseObj.onMouseScrolled(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onMouseScrolled.Fire(args);
					return;
				}
//				System.out.println("mouse scrolled : " + args);
				self.widgetEvents.onMouseScrolled.Fire(args);
			}
		});
		
		keyboard.onInputBegin(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onInputBegin.Fire(args);
					return;
				}
				// System.out.println("released pressed: " + args.get("KeyChar"));
				self.widgetEvents.onInputBegin.Fire(args);
			}
		});
		
		keyboard.onInputEnded(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				Mouse mouse = self.getUserInput().getMouse();
				ArrayList<GuiObject> uiObjs = self.getGuiObjectsAtMouseXY( mouse.getMouseX(), mouse.getMouseY() );
				if (uiObjs != null && uiObjs.size() > 0) {
					uiObjs.get( uiObjs.size() - 1 ).getGuiEvents().onInputEnded.Fire(args);
					return;
				}
				// System.out.println("released key: " + args.get("KeyChar"));
				self.widgetEvents.onInputEnded.Fire(args);
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

			// TODO: implement clip descendants + image clipping
			// TODO: implement parent-zIndex GuiObject sorting part (ZIndexSortType.Sibling)
			// TODO: implement 'ui-padding'
			// TODO: implement 'ui-scale'
			
			Vector2int absPosition = rObject.getAbsolutePosition();
			Vector2int absSize = rObject.getAbsoluteSize();
			int absSizeHash = (absSize.x + "_" + absSize.y).hashCode();
			
			Vector2 anchorPoint = rObject.getAnchorPoint();
			
			Vector2int anchorPointVisual = null;
			if (rObject.isAnchorPointVisible()) {
				anchorPointVisual = new Vector2int(absPosition.x, absPosition.y);	
			}
			
			absPosition = absPosition.sub(new Vector2int( (int) (anchorPoint.x * absSize.x), (int) (anchorPoint.y * absSize.y) ));
			
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
					if (scaleType == ImageScaleType.STRETCH) {
						scaled = GraphicUtility.StretchImageToSize(imgLabel.getRawImage(), absSize);
					} else if (scaleType == ImageScaleType.FIT) {
						scaled = GraphicUtility.ScaleAndFitImage(imgLabel.getRawImage(), absSize, true);
					} else if (scaleType == ImageScaleType.CROP) {
						scaled = GraphicUtility.ScaleAndFitImage(imgLabel.getRawImage(), absSize, false);
					} else {
						System.out.println("Unsupported ImageScaleType Enum: " + scaleType);
					}
					
					imgLabel.setScaledImage( scaled );
				}
				
				Image drawImage = imgLabel.getScaledImage();
				if (drawImage != null) {
					int wdth = (int) ((absSize.x / 2) - (drawImage.getWidth(null) / 2));
					g2d.drawImage(drawImage, absPosition.x + wdth, absPosition.y, drawImage.getWidth(null), drawImage.getHeight(null), null);
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
			
			if (anchorPointVisual != null) {
				g2d.setColor(new Color(255, 255, 255));
				g2d.drawRect( anchorPointVisual.x - 12, anchorPointVisual.y - 12, 24, 24 );
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
