package main.widgets.objects;

import main.math.Vector2int;

public class GuiBase extends Instance {

	// Fields //
	public Vector2int absoluteSize;
	public Vector2int absolutePosition;
	
	protected boolean showOutline;
	
	// Constructors //
	public GuiBase() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.absoluteSize = new Vector2int();
		this.absolutePosition = new Vector2int();
		this.showOutline = false;
	}
	
	public Vector2int getAbsoluteSize() {
		
		Vector2int scaledSize = this.absoluteSize;
		
		Instance uiScale = this.findFirstChildOfClass(UIScale.class);
		if (uiScale != null) {
			scaledSize = ((UIScale) uiScale).calculateDimensions(scaledSize);
		}
		
		Instance aspectConstraint = this.findFirstChildOfClass(AspectRatioConstraint.class);
		if (aspectConstraint != null) {
			scaledSize = ((AspectRatioConstraint) aspectConstraint).calculateDimensions(scaledSize);
		}
		
		return scaledSize;
	}

	public void setAbsoluteSize(Vector2int absoluteSize) {
		this.absoluteSize = absoluteSize;
	}

	public Vector2int getAbsolutePosition() {
		return absolutePosition;
	}
	
	public void setAbsolutePosition(Vector2int absolutePosition) {
		this.absolutePosition = absolutePosition;
	}
	
	public void setOutlineEnabled(boolean enabled) {
		this.showOutline = enabled;
	}
	
	public boolean isOutlineEnabled() {
		return this.showOutline;
	}

	@Override
	public String toString() {
		String superToStr = super.toString();
		superToStr = superToStr.substring(0, superToStr.length()-1);
		return
			superToStr + ", absoluteSize=" + absoluteSize + ", absolutePosition="
			+ absolutePosition + ", showOutline=" + showOutline + "]";
	}
	
}
