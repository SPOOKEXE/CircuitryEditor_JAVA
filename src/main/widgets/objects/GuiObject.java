package main.widgets.objects;

import main.math.Color3;
import main.math.UDim2;
import main.math.Vector2;
import main.math.Vector2int;
import main.signal.SignalListener;
import main.widgets.events.GuiEvents;

public class GuiObject extends GuiBase {

	// Fields //
	protected float backgroundTransparency;
	protected Color3 backgroundColor3;
	protected Vector2 anchorPoint;
	
	protected UDim2 size;
	protected UDim2 position;
	
	protected int rotation;
	protected int zIndex;
	protected boolean clipDescendants;
	
	// Constructors //
	public GuiObject() {
		this.setDefault();
	}

	// Class Methods //
	private void setDefault() {
		this.name = "GuiObject";
		this.backgroundTransparency = 0;
		this.backgroundColor3 = new Color3();
		this.anchorPoint = new Vector2(0.5F, 0.5F);
		
		this.size = new UDim2();
		this.position = new UDim2();
		
		this.rotation = 0;
		this.zIndex = 1;
		this.clipDescendants = true;
	}
	
	public float getBackgroundTransparency() {
		return this.backgroundTransparency;
	}

	public void setBackgroundTransparency(float backgroundTransparency) {
		this.backgroundTransparency = backgroundTransparency;
	}

	public Color3 getBackgroundColor3() {
		return backgroundColor3;
	}

	public void setBackgroundColor3(Color3 backgroundColor3) {
		this.backgroundColor3 = backgroundColor3;
	}
	
	public UDim2 getSize() {
		return size;
	}

	public void updateAbsolutes() {
		UDim2 thisSizeUDim2 = this.size;
		UDim2 thisPositionUDim2 = this.position;
		
		// no parent, use offset values
		if (this.parent == null) {
			this.setAbsolutePosition(new Vector2int(thisPositionUDim2.offset_x, thisPositionUDim2.offset_y));
			this.setAbsoluteSize(new Vector2int(thisSizeUDim2.offset_x, thisSizeUDim2.offset_y));
			return;
		}
		
		// parent, use scale & offset
		Vector2int parentPosition = this.parent.getAbsolutePosition();
		Vector2int parentSize = this.parent.getAbsoluteSize();
		
		this.setAbsoluteSize(new Vector2int(
			(parentSize.x * thisSizeUDim2.scale_x) + thisSizeUDim2.offset_x,
			(parentSize.y * thisSizeUDim2.scale_y) + thisSizeUDim2.offset_y
		));
		
		this.setAbsolutePosition(new Vector2int(
			parentPosition.x + (parentSize.x * thisPositionUDim2.scale_x) + thisPositionUDim2.offset_x,
			parentPosition.y + (parentSize.y * thisPositionUDim2.scale_y) + thisPositionUDim2.offset_y
		));
	}
	
	public void setSize(UDim2 size) {
		this.size = size;
	}

	public UDim2 getPosition() {
		return position;
	}

	public void setPosition(UDim2 position) {
		this.position = position;
	}
	
	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getZIndex() {
		return zIndex;
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public boolean isClippingDescendants() {
		return clipDescendants;
	}

	public void setClippingDescendants(boolean clipDescendants) {
		this.clipDescendants = clipDescendants;
	}

	@Override
	public void setParent(GuiBase parent) {
		super.setParent(parent);
		this.updateAbsolutes();
	}
	
	@Override
	public String toString() {
		String superToStr = super.toString();
		superToStr = superToStr.substring(0, superToStr.length()-1);
		return superToStr + ", backgroundTransparency=" + backgroundTransparency + ", backgroundColor3=" + backgroundColor3
			+ ", size=" + size + ", position=" + position + ", rotation=" + rotation + ", zIndex=" + zIndex
			+ ", clipDescendants=" + clipDescendants + "]";
	}

}
