package main.graph;

import java.util.ArrayList;

public class GraphNode {

	// Fields //
	ArrayList<GraphLink> links;
	
	// Constructor //
	public GraphNode() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.links = new ArrayList<GraphLink>();
	}
	
}
