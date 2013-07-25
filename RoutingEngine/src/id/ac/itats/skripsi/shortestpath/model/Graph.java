package id.ac.itats.skripsi.shortestpath.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	private HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	private List<Edge> edges = new LinkedList<Edge>();

	public void addEdge(String edgeId, String fromId, String toId, double weight ) {
		Vertex fromVertex = vertices.get(fromId);
		if (fromVertex == null) {
			fromVertex = new Vertex(fromId);
			vertices.put(fromId, fromVertex);
		}
		Vertex toVertex = vertices.get(toId);
		if (toVertex == null) {
			toVertex = new Vertex(toId);
			vertices.put(toId, toVertex);
		}
		Edge edge = new Edge(toVertex, weight, edgeId);
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
