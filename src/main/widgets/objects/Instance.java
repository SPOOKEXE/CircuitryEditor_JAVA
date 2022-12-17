package main.widgets.objects;

import java.util.ArrayList;

import main.data.Attributes;

public class Instance {

	// Fields //
	protected String uid;
	protected String name;
	
	protected Instance parent;
	protected ArrayList<Instance> children;
	
	protected Attributes attributes;
	
	// Constructors //
	public Instance() {
		this.parent = null;
		this.children = new ArrayList<Instance>();
	}
	
	// Class Methods //
	public String getUID() {
		return this.uid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void appendChild(Instance child) {
		this.children.add(child);
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
		this.parent = parent;
		if (parent != null) {
			parent.appendChild(parent);
		}
	}
	
	public Instance getParent() {
		return this.parent;
	}
	
	public ArrayList<Instance> getChildren() {
		return this.children;
	}
	
	private ArrayList<Instance> getDescendants(ArrayList<Instance> descendantsArray) {
		for (Instance child : this.children) {
			child.getDescendants(descendantsArray);
		}
		return descendantsArray;
	}
	
	public ArrayList<Instance> getDescendants() {
		return this.getDescendants(new ArrayList<Instance>());
	}
	
	public Instance findFirstChild(String name) {
		for (Instance child : this.children) {
			if (child.getName() == name) {
				return child;
			}
		}
		return null;
	}
	
	public Instance findFirstChildOfClass(Object tclass) {
		for (Instance child : this.children) {
			if (child.getClass() == tclass) {
				return child;
			}
		}
		return null;
	}
	
	public Instance findFirstChildOfClassName(String name) {
		for (Instance child : this.children) {
			if (child.getClass().getSimpleName() == name) {
				return child;
			}
		}
		return null;
	}
	
	public Instance findFirstAncestorOfClass(Object tclass) {
		Instance parent = this.parent;
		while (parent != null) {
			if (parent.getClass() == tclass) {
				return parent;
			}
			parent = parent.parent;
		}
		return null;
	}
	
	public Instance findFirstAncestorOfClassName(String className) {
		Instance parent = this.parent;
		while (parent != null) {
			if (parent.getClass().getSimpleName() == className) {
				return parent;
			}
			parent = parent.parent;
		}
		return null;
	}
	
	public Instance findFirstDescendant(String name) {
		for (Instance child : this.children) {
			if (child.getName() == name) {
				return child;
			}
			Instance descendant = child.findFirstDescendant(name);
			if (descendant != null) {
				return descendant;
			}
		}
		return null;
	}
	
	public Instance findFirstDescendantOfClass(Object tclass) {
		// scan the children first
		for (Instance child : this.children) {
			if (child.getClass() == tclass) {
				return child;
			}
		}
		
		// scan the nth child descendants
		for (Instance child : this.children) {
			Instance descendant = child.findFirstDescendantOfClass(tclass);
			if (descendant != null) {
				return descendant;
			}
		}
		
		return null;
	}
	
	public Instance findFirstDescendantOfClassName(String className) {
		// scan the children first
		for (Instance child : this.children) {
			if (child.getClass().getSimpleName() == className) {
				return child;
			}
			
		}
		
		// scan the nth child descendants
		for (Instance child : this.children) {
			Instance descendant = child.findFirstDescendantOfClass(className);
			if (descendant != null) {
				return descendant;
			}
		}
		
		return null;
	}
	
	public void clearAllChildren() {
		for (Instance child : this.children) {
			child.setParent(null);
		}
		this.children = new ArrayList<>();
	}
	
	public boolean isAncestorOf(Instance target) {
		return target.isDescendantOf(this);
	}
	
	public boolean isDescendantOf(Instance target) {
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
	
}
