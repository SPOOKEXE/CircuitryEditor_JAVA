package main.widgets;

import main.math.Color3;
import main.math.UDim2;
import main.math.Vector2;
import main.math.Vector2int;
import main.widgets.objects.Frame;

public class EditorWidget extends BaseWidget {

	// Static Private Methods //

	// Fields //

	// Constructors //
	public EditorWidget() {
		this.setDefault();
	}

	// Class Methods //
	private void setDefault() {
		Frame containerFrame = new Frame();
		containerFrame.setName("Background");
		containerFrame.setBackgroundColor3(Color3.fromRGB(48, 48, 48));
		containerFrame.setSize(new UDim2(1, 0, 1, 0));
		containerFrame.setPosition(new UDim2(0, 0, 0, 0));
		containerFrame.setZIndex(1);
		containerFrame.setParent(this.baseGuiData);
		
		Frame actionBar = new Frame();
		actionBar.setName("ActionBar");
		actionBar.setBackgroundColor3(Color3.fromRGB(80,80,80));
		actionBar.setSize(new UDim2(1, 0, 0.15, 0));
		actionBar.setPosition(new UDim2(0, 0, 0, 0));
		actionBar.setZIndex(2);
		actionBar.setParent(containerFrame);
		
		Frame componentList = new Frame();
		componentList.setName("ComponentsList");
		componentList.setAnchorPoint( new Vector2(1, 0) );
		componentList.setBackgroundColor3(Color3.fromRGB(120,80,80));
		componentList.setSize(new UDim2(0.2, 0, 0.85, 0));
		componentList.setPosition(new UDim2(1, 0, 0.15, 0));
		componentList.setZIndex(3);
		componentList.setParent(containerFrame);
		
		this.appendGuiObjects(containerFrame, actionBar, componentList);
	}
	
	@Override
	public boolean Init() {
		if (!super.Init()) {
			return false;
		}
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
