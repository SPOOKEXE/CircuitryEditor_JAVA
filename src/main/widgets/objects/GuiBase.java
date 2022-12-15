package main.widgets.objects;

import java.util.UUID;

import main.data.Attributes;
import main.math.Vector2int;
import main.widgets.events.GuiEvents;

public class GuiBase extends GuiEvents {

	// Fields //
	protected String uid;
	
	protected String name;
	protected GuiBase parent;
	
	protected Vector2int absoluteSize;
	protected Vector2int absolutePosition;
	
	protected boolean showOutline;
	protected Attributes attributes;
	
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
	
	public String getUID() {
		return this.uid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setParent(GuiBase parent) {
		this.parent = parent;
	}
	
	public GuiBase getParent() {
		return this.parent;
	}
	
	public Vector2int getAbsoluteSize() {
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
	
	public boolean isAncestorOf(GuiBase target) {
		return target.isDescendantOf(this);
	}
	
	public boolean isDescendantOf(GuiBase target) {
		// if this parent is the target, return true
		if (this == target || this.parent == target) {
			return true;
		}
		
		// If this parent is not null but the target is
		if (this.parent != null && target == null) {
			return false; // return false
		// If this parent is null, but the target is not
		} else if (this.parent == null && target != null) {
			return false; // return false
		}
		
		// check if the parent is a descendant of the target
		return this.parent.isDescendantOf(target);
	}

	@Override
	public String toString() {
		return
			this.getClass().getSimpleName()
			+ " [uuid=" + this.uid + ", name=" + name + ", parent=" + (parent==null ? "null" : parent.getUID()) + ", absoluteSize=" + absoluteSize + ", absolutePosition="
			+ absolutePosition + ", attributes=" + attributes + "]";
	}
	
}
