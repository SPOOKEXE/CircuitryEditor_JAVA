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
	public void appendChild(Instance child) {
		this.children.addAll(children);
	}
	
	public void appendChildren(ArrayList<Instance> children) {
		this.children.addAll(children);
	}
	
	public void removeChild(Instance child) {
		while (this.children.remove(child)) { } // remove all
	}
	
	public void removeChildren(ArrayList<Instance> children) {
		while (this.children.removeAll(children)) { } // remove all
	}
	
	public void setParent(Instance parent) {
		if (this.parent != null) {
			this.parent.removeChild(parent);
		}
		if (parent != null) {
			parent.appendChild(parent);
		}
		this.parent = parent;
	}
	
	public Instance getParent() {
		return this.parent;
	}
	
}
