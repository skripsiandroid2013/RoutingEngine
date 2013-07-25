package id.ac.itats.skripsi.orm.generator.builder;

import id.ac.itats.skripsi.orm.Way;
import id.ac.itats.skripsi.orm.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph {

	private HashMap<Long, Node> vertices = new HashMap<>();
	private List<Way> edges = new LinkedList<>();
	
	public void addEdge(String wayID,Long source, String[] sourceLatLon,
			Long target, String[] targetLatLon, double weight) {
		Node sourceNode = vertices.get(source);
		if (sourceNode == null) {
			sourceNode = new Node(source, sourceLatLon[0], sourceLatLon[1]);
			vertices.put(source, sourceNode);
		}
		Node targetNode = vertices.get(target);
		if (targetNode == null) {
			targetNode = new Node(target, targetLatLon[0], targetLatLon[1]);
			vertices.put(target, targetNode);
		}
		Way edge = new Way(wayID,source,target,weight);
		sourceNode.getAdjacentList().add(edge);
		edges.add(edge);
	}

	public int getSize() {
		return vertices.size();
	}

	public Collection<Node> getVertices() {
		return vertices.values();
	}

}
