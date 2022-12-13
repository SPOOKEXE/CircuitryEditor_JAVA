package main.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GraphNetwork {

	// Fields //
	protected ArrayList<GraphNode> nodes;
	protected ArrayList<GraphLink> links;
	protected HashMap<GraphNode, ArrayList<GraphNode>> neighbours;
	
	// Constructors //
	public GraphNetwork() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.nodes = new ArrayList<GraphNode>();
		this.links = new ArrayList<GraphLink>();
		this.resetNeighbourMap();
	}
	
	public void resetNeighbourMap() {
		this.neighbours = new HashMap<GraphNode, ArrayList<GraphNode>>();
	}
	
	public void updateNeighbourMap() {
		this.resetNeighbourMap();
		
		// update connection map
		
	}
	
	public HashMap<GraphNode, ArrayList<GraphNode>> getNeighbourMap() {
		return this.neighbours;
	}
	
	// Find Links
	int findLink(GraphLink link) {
		return this.links.indexOf(link);
	}
	
	// Add Links
	void addLink(GraphLink link) {
		this.links.add(link);
		this.updateNeighbourMap();
	}
	
	void addLinks(GraphLink...linkArray) {
		this.links.addAll( Arrays.asList(linkArray) );
		this.updateNeighbourMap();
	}
	
	void addLinks(ArrayList<GraphLink> linkArray) { 
		this.links.addAll(linkArray);
		this.updateNeighbourMap();
	}
	
	// Remove Links
	void removeLinks(GraphLink...linkArray) {
		this.links.removeAll( Arrays.asList(linkArray) );
		this.updateNeighbourMap();
	}
	
	void removeLinks(ArrayList<GraphLink> linkArray) {
		this.links.removeAll( linkArray );
		this.updateNeighbourMap();
	}
	
	void removeLink(GraphLink link) {
		this.links.remove(link);
		this.updateNeighbourMap();
	}
	
	// Find Nodes
	int findNode(GraphNode node) {
		return this.nodes.indexOf(node);
	}
	
	// Add Nodes
	void addNodes(GraphNode...nodeArray) {
		this.nodes.addAll( Arrays.asList(nodeArray) );
		this.updateNeighbourMap();
	}
	
	void addNodes(ArrayList<GraphNode> nodeArray) {
		this.nodes.addAll( nodeArray );
		this.updateNeighbourMap();
	}
	
	void addNodes(GraphNode node) {
		this.nodes.add(node);
		this.updateNeighbourMap();
	}
	
	// Remove Nodes
	void removeNodes(GraphNode...nodeArray) {
		this.nodes.removeAll( Arrays.asList(nodeArray) );
		this.updateNeighbourMap();
	}
	
	void removeNodes(ArrayList<GraphNode> nodeArray) {
		this.nodes.removeAll( nodeArray );
		this.updateNeighbourMap();
	}
	
	void removeNodes(GraphNode node) {
		this.nodes.remove(node);
		this.updateNeighbourMap();
	}
	
}
