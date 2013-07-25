package id.ac.itats.skripsi.shortestpath;

import id.ac.itats.skripsi.orm.Node;
import id.ac.itats.skripsi.shortestpath.model.Vertex;


public interface AStarHeuristic {

	public double calcHeuristic(Node source, Node target);
}
