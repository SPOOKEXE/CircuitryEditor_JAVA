package main.widgets.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import main.enumerations.ZIndexSortType;
import main.widgets.objects.GuiBase;
import main.widgets.objects.GuiObject;

public class GuiObjectManager {

	// Fields //
	protected ArrayList<GuiObject> unsorted;
	protected ArrayList<GuiObject> sorted;
	protected ZIndexSortType zIndexSortType;
	
	// Constructors //
	public GuiObjectManager() {
		this.unsorted = new ArrayList<GuiObject>(20);
	}
	
	public GuiObjectManager(ZIndexSortType zIndexSortType) {
		this.unsorted = new ArrayList<GuiObject>(20);
		this.zIndexSortType = zIndexSortType;
	}
	
	public GuiObjectManager(ArrayList<GuiObject> unsorted) {
		this.unsorted = unsorted;
		this.sortUnsorted();
	}
	
	// Class Methods //
	public ZIndexSortType getzIndexSortType() {
		return zIndexSortType;
	}
	public void setzIndexSortType(ZIndexSortType zIndexSortType) {
		this.zIndexSortType = zIndexSortType;
	}
	
	public void appendGuiObjects(GuiObject...guiObjects) {
		this.unsorted.addAll( Arrays.asList(guiObjects) );
		this.clearSorted();
	}
	
	public void appendGuiObjects(ArrayList<GuiObject> guiObjects) {
		this.unsorted.addAll(guiObjects);
		this.clearSorted();
	}
	
	public void removeGuiObjects(GuiObject...guiObjects) {
		if (this.unsorted.removeAll( Arrays.asList(guiObjects) )) {
			this.clearSorted();
		}
	}
	
	public void removeGuiObjects(ArrayList<GuiObject> guiObjects) {
		if (this.unsorted.removeAll( guiObjects )) {
			this.clearSorted();
		}
	}
	
	public ArrayList<GuiObject> getUnsorted() {
		return this.unsorted;
	}
	
	public void clearSorted() {
		this.sorted = null;
	}
	
	public ArrayList<GuiObject> getSorted() {
		if (this.sorted == null) {
			this.sortUnsorted();
		}
		return this.sorted;
	}
	
	// TODO: implement
	public void updateMouseMoved() {
		
	}
	
	// TODO: implement
	public boolean hasMouseMoved() {
		
		return false;
	}
	
	// TODO: implement
	public boolean hasMouseHoverTargetChanged() {
		
		return false;
	}
	
	// TODO: implement
	public GuiObject getCurrentMouseHoverTarget() {
		
		return null;
	}
	
	private ArrayList<GuiObject> sortUnsorted() {
		this.sorted = new ArrayList<GuiObject>();
		
		// SORTING ALGORITHM (SORT BY PARENT STRUCTURE, SORT BY ZINDEX)
		
		// * Firstly, we want to separate all parent/children hierarchies into their own HashMap.
		// * For every child under X parent, place them in an ArrayList given their zIndex as the HashMap Key.
		// * 
		// * Once all children have been places into this data structure;
		// * HashMap<GuiObject, HashMap<Integer, ArrayList<GuiObject>>>
		// * 
		// * We create a new ArrayList of GuiObjects that ascends in zIndexes (zIndex = 1, zIndex = 3, zIndex = 4, etc..) 
		// *
		// * After that, we sort those ArrayLists based on hierarchy, highest ancestors being first.
		// * 
		// * We then display all GuiObjects in that order since they are zIndex sorted, and hierarchically sorted.

		this.sorted = new ArrayList<GuiObject>();
		
		HashMap<GuiBase, HashMap<Integer, ArrayList<GuiObject>>> sortedParentZIndexBuffer = new HashMap<GuiBase, HashMap<Integer, ArrayList<GuiObject>>>();
		
		for (GuiObject object : this.unsorted) {
			// if parent to nil or its ancestor is not a GuiBase, skip it
			if (object.isDescendantOf(null) || (!(object.getParent() instanceof GuiBase))) {
				continue;
			}
			
			GuiBase objParent = (GuiBase) object.getParent();
			
			// if there is no zIndex sorted HashMap, create it
			HashMap<Integer, ArrayList<GuiObject>> sortedChildren = sortedParentZIndexBuffer.get(objParent);
			if (sortedChildren == null) {
				sortedChildren = new HashMap<Integer, ArrayList<GuiObject>>();
				sortedParentZIndexBuffer.put(objParent, sortedChildren);
			}
			
			// if there is no array list for the zIndex HashMap, create it
			ArrayList<GuiObject> sortedZIndexes = sortedChildren.get( object.getZIndex() );
			if (sortedZIndexes == null) {
				sortedZIndexes = new ArrayList<GuiObject>();
				sortedChildren.put(object.getZIndex(), sortedZIndexes);
			}
			
			// add to ArrayList
			sortedZIndexes.add(object);
		}
		
		// "ArrayList of GuiObjects that ascends in zIndexes"
		// Convert to HashMap<GuiObject, ArrayList<GuiObject>> of sorted zIndex childrens given a parent GuiObject.
		HashMap<GuiBase, ArrayList<GuiObject>> sortedzIndexChildren = new HashMap<GuiBase, ArrayList<GuiObject>>();
		for (GuiBase ancestor : sortedParentZIndexBuffer.keySet()) {
			// https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
			TreeMap<Integer, ArrayList<GuiObject>> sorted = new TreeMap<Integer, ArrayList<GuiObject>>();
			sorted.putAll(sortedParentZIndexBuffer.get(ancestor));
			for (Map.Entry<Integer, ArrayList<GuiObject>> entry : sorted.entrySet()) {
				if ( sortedzIndexChildren.get(ancestor) == null ) {
					sortedzIndexChildren.put(ancestor, new ArrayList<GuiObject>());
				}
				sortedzIndexChildren.get(ancestor).addAll( entry.getValue() );
			}
		}
		
		// if sibling, relation to parent matters (so sort parents based on zIndex and hierarchical
		if (this.zIndexSortType == ZIndexSortType.Sibling) {
			
			// clear memory for the zIndexBuffer HashMap
			sortedParentZIndexBuffer = null;
			
			// TODO: Hierarchical sorting with zIndex
			System.out.println("Hierarchical sorting is not implemented!");
			
			// clear memory for the sortedzIndexChildren
			sortedzIndexChildren = null;
			
			// add to assortedRenderObjects
			
			// clear memory for the hierarchical sorting
			
		} else {
			
			// shared z-index across the board
			for (Entry<GuiBase, ArrayList<GuiObject>> tempSorted : sortedzIndexChildren.entrySet()) {
				this.sorted.addAll(tempSorted.getValue());
			}
			
		}
		
		return this.sorted;
	}
	
}
