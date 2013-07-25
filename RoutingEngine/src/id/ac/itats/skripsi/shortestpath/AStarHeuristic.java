package id.ac.itats.skripsi.shortestpath;

import id.ac.itats.skripsi.orm.Node;


public interface AStarHeuristic {

	public double getNilaiHeuristic(Node source, Node target);
}
