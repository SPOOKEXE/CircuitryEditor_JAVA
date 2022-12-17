package main.widgets.objects;

import java.util.UUID;

import main.data.Attributes;
import main.math.Vector2int;

public class GuiBase extends Instance {

	// Fields //
	protected Vector2int absoluteSize;
	protected Vector2int absolutePosition;
	
	protected boolean showOutline;
	
	// Constructors //
	public GuiBase() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.uid = UUID.randomUUID().toString();
		
		this.name = "GuiObject";
		this.parent = null;
		
		this.absoluteSize = new Vector2int();
		this.absolutePosition = new Vector2int();
		
		this.showOutline = false;
		this.attributes = new Attributes();
	}
	
	public Vector2int getAbsoluteSize() {
		// TODO: implement aspect ratios
		Instance aspectConstraint = this.findFirstChildOfClassName("AspectRatioConstraint");
		if (aspectConstraint != null) {
			System.out.println(((AspectRatioConstraint)aspectConstraint).aspectRatio);
			return ((AspectRatioConstraint) aspectConstraint).calculateDimensions();
		}
		return this.absoluteSize;
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
		return
			this.getClass().getSimpleName()
			+ " [uuid=" + this.uid + ", name=" + name + ", parent=" + (parent==null ? "null" : parent.getUID()) + ", absoluteSize=" + absoluteSize + ", absolutePosition="
			+ absolutePosition + ", attributes=" + attributes + "]";
	}
	
}
