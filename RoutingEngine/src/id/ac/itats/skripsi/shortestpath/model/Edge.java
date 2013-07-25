package id.ac.itats.skripsi.shortestpath.model;

public class Edge {
	public final Vertex target;
	public final double weight;
	public final String id;

	public Edge(Vertex argTarget, double argWeight, String argId) {
		target = argTarget;
		weight = argWeight;
		id = argId;
	}
}
