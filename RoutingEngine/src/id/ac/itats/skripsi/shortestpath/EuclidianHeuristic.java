package id.ac.itats.skripsi.shortestpath;

import id.ac.itats.skripsi.orm.Node;
import id.ac.itats.skripsi.shortestpath.model.Vertex;

public class EuclidianHeuristic implements AStarHeuristic {

	@Override
	public double calcHeuristic(Vertex source, Vertex target) {
		
		Node start = source.node;
		Node goal  = target.node;
		double x = Double.valueOf(start.getLatitude()) - Double.valueOf(goal.getLatitude());
		double y = Double.valueOf(start.getLongitude()) - Double.valueOf(goal.getLongitude());

		return Math.sqrt(x * x + y * y);
	}

}
