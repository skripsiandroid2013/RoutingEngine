package id.ac.itats.skripsi.shortestpath;

import id.ac.itats.skripsi.orm.Node;

public class EuclidianHeuristic implements AStarHeuristic {

	@Override
	public double getNilaiHeuristic(Node source, Node goal) {
		
		double x = Double.parseDouble(source.getLatitude()) - Double.parseDouble(goal.getLatitude());
		double y = Double.parseDouble(source.getLongitude()) - Double.parseDouble(goal.getLongitude());

//		double x = start.getPoint().getX() - finish.getPoint().getX();
//		double y = start.getPoint().getY() - finish.getPoint().getY();

		return Math.sqrt(x * x + y * y);
	}

}
