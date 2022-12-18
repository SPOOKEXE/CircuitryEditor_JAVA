package main.widgets.objects;

import main.math.Vector2int;

public class UIScale extends Instance {

	// Fields //
	public float scale;
	
	// Constructors //
	public UIScale() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.scale = 1;
	}
	
	public Vector2int calculateDimensions() {
		if (this.parent == null || !(this.parent instanceof GuiObject)) {
			return new Vector2int();
		}
		return ((GuiObject) this.parent).absoluteSize.mult(this.scale);
	}
	
	public Vector2int calculateDimensions(Vector2int useThis) {
		return useThis.mult(this.scale);
	}
	
	@Override
	public void deserialize(String serialized) {
		super.deserialize(serialized);
	}
	
}
