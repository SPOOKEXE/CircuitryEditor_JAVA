package main.widgets.objects;

import java.util.ArrayList;

public class Instance {

	// Fields //
	public Instance parent;
	protected ArrayList<Instance> children;
	
	// Constructors //
	public Instance() {
		this.parent = null;
		this.children = new ArrayList<Instance>();
	}
	
	// Class Methods //
	public void setChildren(ArrayList<Instance> children) {
		this.children = children;
	}
	
	public void appendChildren(ArrayList<Instance> children) {
		if (this.children == null) {
			this.children = new ArrayList<Instance>();
		}
		this.children.addAll(children);
	}
	
	public void removeChild(Instance child) {
		if (this.children != null) {
			while (this.children.remove(child)) { } // remove all
		}
	}
	
	public void removeChildren(ArrayList<Instance> children) {
		if (this.children != null) {
			while (this.children.removeAll(children)) { } // remove all
		}
	}
	
	public void setParent(Instance parent) {
		if (this.parent != null) {
			this.parent.removeChild(parent);
		}
		this.parent = parent;
	}
	
	public Instance getParent() {
		return this.parent;
	}
	
}
