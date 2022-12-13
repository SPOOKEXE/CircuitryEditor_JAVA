package main.display.objects;

import main.math.Color3;
import main.math.UDim2;
import main.math.Vector2;

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

	public void updateAbsoluteSize() {
		if (this.parent == null) {
			UDim2 sizeUD2 = this.getSize();
			this.setAbsoluteSize(new Vector2(sizeUD2.offset_x, sizeUD2.offset_y));
		} else {
			Vector2 prntAbsSize = this.parent.getAbsoluteSize();
			UDim2 sizeUD2 = this.getSize();
			this.setAbsoluteSize(new Vector2(
				(prntAbsSize.x * sizeUD2.scale_x) + sizeUD2.offset_x,
				(prntAbsSize.y * sizeUD2.scale_y) + sizeUD2.offset_y
			));
		}
	}

	public void updateAbsolutePosition() {
		if (this.parent == null) {
			UDim2 posUD2 = this.getPosition();
			this.setAbsolutePosition(new Vector2(posUD2.offset_x, posUD2.offset_y));
		} else {
			Vector2 prntAbsSize = this.parent.getAbsoluteSize();
			Vector2 prntAbsPosition = this.parent.getAbsolutePosition();
			UDim2 posUD2 = this.getPosition();
			Vector2 translated = new Vector2(
				prntAbsPosition.x + (prntAbsSize.x * posUD2.scale_x) + posUD2.offset_x,
				prntAbsPosition.y + (prntAbsSize.y * posUD2.scale_y) + posUD2.offset_y
			);
			this.setAbsolutePosition(translated);
		}
	}
	
	public void setSize(UDim2 size) {
		this.size = size;
		this.updateAbsoluteSize();
	}

	public UDim2 getPosition() {
		return position;
	}

	public void setPosition(UDim2 position) {
		this.position = position;
		this.updateAbsolutePosition();
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
		this.updateAbsoluteSize();
		this.updateAbsolutePosition();
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
