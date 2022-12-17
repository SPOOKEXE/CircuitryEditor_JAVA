package main.widgets;

import java.util.HashMap;

import main.enumerations.ImageScaleType;
import main.math.Color3;
import main.math.UDim2;
import main.math.Vector2;
import main.signal.SignalListener;
import main.widgets.events.GuiEvents;
import main.widgets.objects.AspectRatioConstraint;
import main.widgets.objects.ImageLabel;

public class EditorWidget extends BaseWidget {

	// Static Private Methods //

	// Fields //

	// Constructors //
	public EditorWidget() {
		this.setDefault();
	}

	// Class Methods //
	private void setDefault() {
		
		// Test Frames
//		Frame testFrame1 = new Frame();
//		testFrame1.setName("Test Frame 1");
//		testFrame1.setBackgroundColor3(Color3.fromRGB(255, 0, 0));
//		testFrame1.setBackgroundTransparency(0.3F);
//		testFrame1.setSize(new UDim2(0.5, 0, 0.5, 0));
//		testFrame1.setPosition(new UDim2(0.35, 0, 0.35, 0));
//		testFrame1.setZIndex(5);
//		testFrame1.setParent(this.baseGuiData);
//
//		Frame testFrame2 = new Frame();
//		testFrame2.setName("Test Frame 2");
//		testFrame2.setBackgroundColor3(Color3.fromRGB(0, 0, 255));
//		testFrame2.setSize(new UDim2(0.25, 0, 0.25, 0));
//		testFrame2.setPosition(new UDim2(0.1, 0, 0.1, 0));
//		testFrame2.setZIndex(3);
//		testFrame2.setParent(this.baseGuiData);
//
//		Frame testFrame3 = new Frame();
//		testFrame3.setName("Test Frame 3");
//		testFrame3.setBackgroundColor3(Color3.fromRGB(0, 255, 0));
//		testFrame3.setBackgroundTransparency(0.3F);
//		testFrame3.setSize(new UDim2(0.25, 0, 0.5, 0));
//		testFrame3.setPosition(new UDim2(0.5, 0, 0.25, 0));
//		testFrame3.setZIndex(4);
//		testFrame3.setParent(testFrame2);
//		
//		Frame testFrame4 = new Frame();
//		testFrame4.setName("Test Frame 4");
//		testFrame4.setBackgroundColor3(Color3.fromRGB(0, 255, 0));
//		testFrame4.setSize(new UDim2(0.25, 0, 0.5, 0));
//		testFrame4.setPosition(new UDim2(0.25, 0, 0.25, 0));
//		testFrame4.setZIndex(5);
//		testFrame4.setParent(testFrame2);
//		
//		this.appendGuiObjects( testFrame1, testFrame2, testFrame3, testFrame4);
		
		ImageLabel testImage1 = new ImageLabel();
		testImage1.setName("Test Image 1");
		testImage1.setBackgroundColor3(Color3.fromRGB(200, 200, 200));
		testImage1.setSize(new UDim2(0.1, 0, 0.1, 0));
		testImage1.setPosition(new UDim2(0.45, 0, 0.45, 0));
		testImage1.setZIndex(8);
		testImage1.setBackgroundTransparency(0.5F);
		testImage1.setImageScaleType(ImageScaleType.FIT);
		testImage1.setOutlineEnabled(true);
		testImage1.setImagePath("D:\\vcbcvxbvxcbcvxb.png");
		testImage1.setImageTransparency(0.3F);
		testImage1.setParent(this.baseGuiData);
		AspectRatioConstraint constraint = new AspectRatioConstraint();
		constraint.aspectRatio = 1;
		constraint.setParent(testImage1);
		
//		ImageLabel testImage2 = new ImageLabel();
//		testImage2.setName("Test Image 2");
//		testImage2.setAnchorPoint(new Vector2(1F, 1F));
//		testImage2.setBackgroundColor3(Color3.fromRGB(200, 200, 200));
//		testImage2.setSize(new UDim2(0.1, 0, 0.1, 0));
//		testImage2.setPosition(new UDim2(0.45, 0, 0.45, 0));
//		testImage2.setZIndex(9);
//		testImage2.setBackgroundTransparency(0.5F);
//		testImage2.setImageScaleType(ImageScaleType.FIT);
//		testImage2.setOutlineEnabled(true);
//		testImage2.setImagePath("D:\\vcbcvxbvxcbcvxb.png");
//		testImage2.setImageTransparency(0.3F);
//		testImage2.setParent(this.baseGuiData);
//		AspectRatioConstraint constraint2 = new AspectRatioConstraint();
//		constraint2.aspectRatio = 1;
//		constraint2.setParent(testImage2);
		
		this.appendGuiObjects(testImage1);//, testImage2);
		
		GuiEvents imageLabelEvents = testImage1.getGuiEvents();
		
		imageLabelEvents.onMouseEnter(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				System.out.println("Mouse has entered the test image frame!");
				System.out.println("Args: " + args);
			}
		});
		
		imageLabelEvents.onMouseLeave(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				System.out.println("Mouse has left the test image frame!");
				System.out.println("Args: " + args);
			}
		});
		
		imageLabelEvents.onMouse1Down(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				System.out.println("Mouse has left clicked the test image frame!");
				System.out.println(args);
				
				if (this.data.get("flip") == null) {
					this.data.put("flip", true);
				}
				
				boolean flip = (boolean) this.data.get("flip");
				testImage1.setAnchorPoint(flip ? new Vector2(1F, 1F) : new Vector2());
				this.data.put("flip", !flip);
			}
		});
		
	}

	@Override
	public boolean Init() {
		if (!super.Init()) {
			return false;
		}

		// on initialize
		this.setWindowTitle("Circuitry Editor Widget");

		return true;
	}

	@Override
	public boolean Start() {
		return super.Start();
	}

	@Override
	public boolean Stop() {
		return super.Stop();
	}

	@Override
	public void Update() {
		super.Update();
	}
	
	@Override
	public void Draw() {
		super.Draw();

	}

}
