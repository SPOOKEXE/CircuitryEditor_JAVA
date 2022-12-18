package main.widgets.objects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import main.data.Attributes;

public class Instance {

	private final String FAILED_SERIALIZE_ERR = "Could not get field for serializer: {} - {}";
	
	// Fields //
	protected String uid;
	protected String name;
	
	protected Instance parent;
	protected ArrayList<Instance> children;
	
	protected Attributes attributes;
	
	// Constructors //
	public Instance() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.uid = UUID.randomUUID().toString();
		this.name = "Instance";
		
		this.parent = null;
		this.children = new ArrayList<Instance>();

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
			parent.appendChild(this);
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

	@Override
	public String toString() {
		return
			this.getClass().getSimpleName() + " [uid=" + uid + ", name=" + name + ", parent=" +
			parent + ", children=" + children + ", attributes=" + attributes + "]";
	}
	
	public String serialize(List<String> ignoreProperies) {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("*");
		for (Field f : getClass().getDeclaredFields()) {
			if (ignoreProperies != null && ignoreProperies.contains(f.getName())) {
				continue;
			}
			sb.append(f.getName());
		    sb.append("=");
		    try {
		    	Object value = f.get(this);
		    	if (value instanceof Enum) {
		    		@SuppressWarnings("rawtypes")
					String enumString = ((Enum) value).getDeclaringClass().getSimpleName() + "." + value.toString();
		    		sb.append(enumString);
		    		System.out.println(enumString);
		    	} else if (value instanceof Number || value instanceof Boolean) {
		    		sb.append(value.toString());
		    	} else {
		    		sb.append('"' + value.toString() + '"');
		    	}
			} catch (Exception e) {
				System.out.println(FAILED_SERIALIZE_ERR.formatted(this.getClass().getSimpleName(), f.getName()));
			}
		    sb.append(",");
		}
		return sb.toString();
	}
	
	public String serialize() {
		return this.serialize(null);
	}
	
	public void deserialize(String serialized) {
//		Map<String, Object> serializedMap = new HashMap<String, Object>();
		ArrayList<String> classNameGroups = new ArrayList<String>();
		classNameGroups.addAll( Arrays.asList(serialized.split("\\*")) );
		String className = classNameGroups.remove(0);
		System.out.println("Class: " + className);
		String properties = classNameGroups.remove(0);
		for (String propertyNameValue : properties.split(",")) {
//			String[] splits = propertyNameValue.split("=");
//			String propertyName = splits[0];
//			String propertyValue = splits[1];
			System.out.println("- " + propertyNameValue);
		}
	}
	
	public static Instance deserializer(String serialized) {
		Instance base = new Instance();
		base.deserialize(serialized);
		return base;
	}
	
}
