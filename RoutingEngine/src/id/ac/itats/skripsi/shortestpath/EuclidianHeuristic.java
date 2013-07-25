package id.ac.itats.skripsi.shortestpath;

import id.ac.itats.skripsi.orm.Node;

public class EuclidianHeuristic implements AStarHeuristic {

	@Override
	public double calcHeuristic(Node source, Node goal) {
		
		double x = Double.parseDouble(source.getLatitude()) - Double.parseDouble(goal.getLatitude());
		double y = Double.parseDouble(source.getLongitude()) - Double.parseDouble(goal.getLongitude());

		return Math.sqrt(x * x + y * y);
		
	}

}
