package id.ac.itats.skripsi.shortestpath.model;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
	public final String name;

	public LinkedList<Edge> adjacencies = new LinkedList<Edge>();
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

}
