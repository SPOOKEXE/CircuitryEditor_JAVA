package main.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Attributes {

	// Fields //
	protected HashMap<Object, Object> _Attributes;
	
	// Constructors //
	public Attributes() {
		this._Attributes = new HashMap<Object, Object>();
	}
	
	// Class Methods //
	public void SetAttribute(Object key, Object value) {
		this._Attributes.put(key, value);
	}
	
	public Object GetAttribute(Object key) {
		return this._Attributes.get(key);
	}
	
	public Set<Object> getKeys() {
		return this._Attributes.keySet();
	}
	
	public Collection<Object> getValues() {
		return this._Attributes.values();
	}
	
	public HashMap<Object, Object> getMap() {
		return this._Attributes;
	}

	@Override
	public String toString() {
		return "Attributes-[" + _Attributes + "]";
	}
}
