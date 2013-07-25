package id.ac.itats.skripsi.shortestpath;

import id.ac.itats.skripsi.databuilder.GraphAdapter;
import id.ac.itats.skripsi.shortestpath.model.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {

	private GraphAdapter graphAdapter;
	private AStarHeuristic heuristic;
	private Vertex target;

	public AStar(GraphAdapter graphAdapter, AStarHeuristic heuristic) {
		this.graphAdapter = graphAdapter;
		this.heuristic = heuristic;
	}

	public void computePaths(Vertex source, Vertex target) {
		this.target = target;

//		PriorityQueue<Vertex> openList = new PriorityQueue<Vertex>();
//		PriorityQueue<Vertex> closedList = new PriorityQueue<Vertex>();
//
//		source.costG = 0.;
//		source.minDistance = source.costG
//				+ heuristic.calcHeuristic(
//						graphAdapter.getNode(source.toString()),
//						graphAdapter.getNode(target.toString()));
//
//		openList.add(source);
//		source.onOpenList = true;
//
//		while (!openList.isEmpty()) {
//			Vertex current = openList.poll();
//			closedList.add(current);
//			current.onClosedList = true;
//
//			// currentNeighborhood
//			for (Edge e : current.adjacencies) {
//				Vertex next = e.target;
//
//				double tentativeG = current.costG + e.weight;
//				;
//				if (next.onClosedList && tentativeG >= next.costG) {
//					continue;
//				}
//				if (!next.onOpenList || tentativeG < next.costG) {
//					next.previous = current;
//					next.costG = tentativeG;
//					next.minDistance = next.costG
//							+ heuristic.calcHeuristic(
//									graphAdapter.getNode(next.toString()),
//									graphAdapter.getNode(target.toString()));
//
//					if (!next.onOpenList) {
//						openList.add(next);
//					}
//				}
//
//			}
//		}
	}

	public List<Vertex> getShortestPath() {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);

		return path;
	}

}
