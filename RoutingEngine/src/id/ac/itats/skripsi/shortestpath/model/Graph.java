package id.ac.itats.skripsi.shortestpath.model;

import id.ac.itats.skripsi.orm.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	private HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	private List<Edge> edges = new LinkedList<Edge>();

	public void addEdge(String edgeId, Node sourceNode, Node targetNode, double weight ) {
		Vertex fromVertex = vertices.get(sourceNode.toString());
		if (fromVertex == null) {
			fromVertex = new Vertex(sourceNode);
			vertices.put(sourceNode.toString(), fromVertex);
		}
		Vertex toVertex = vertices.get(targetNode.toString());
		if (toVertex == null) {
			toVertex = new Vertex(targetNode);
			vertices.put(targetNode.toString(), toVertex);
		}
		Edge edge = new Edge(edgeId, toVertex, weight);
		edges.add(edge);
		fromVertex.adjacencies.add(edge);
		
	}

	public int getSize() {
		return this.vertices.size();
	}

	public Vertex toVertex(String id) {
		return vertices.get(id);
	}

	public HashMap<String, Vertex> getVertices() {
		return vertices;
	}

	public Collection<Vertex> getVerticesValue() {
		return vertices.values();
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
}
