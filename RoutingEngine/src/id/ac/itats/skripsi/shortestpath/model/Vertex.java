package id.ac.itats.skripsi.shortestpath.model;

import id.ac.itats.skripsi.orm.Node;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
	public final Node node;

	public LinkedList<Edge> adjacencies = new LinkedList<Edge>();
	public double minDistance ;
	public double costG = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public boolean onOpenList;
	public boolean onClosedList;

	

	public Vertex(Node argName) {
		node = argName;
		onOpenList = false;
		onClosedList = false;
	}

	
	@Override
	public String toString() {
		return node.toString();
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

}
