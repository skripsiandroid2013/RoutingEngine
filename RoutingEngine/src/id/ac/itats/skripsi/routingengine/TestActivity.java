package id.ac.itats.skripsi.routingengine;

import id.ac.itats.skripsi.databuilder.GraphAdapter;
import id.ac.itats.skripsi.shortestpath.AStar;
import id.ac.itats.skripsi.shortestpath.model.Graph;
import id.ac.itats.skripsi.shortestpath.model.Vertex;
import id.ac.itats.skripsi.util.StopWatch;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class TestActivity extends Activity {
	protected static final String TAG = "TestActivity";
	private GraphAdapter graphAdapter;
	private Graph graph;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		graphAdapter = new GraphAdapter(this);

		buildGraph();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ormtest, menu);
		return true;
	}

	// XXX TESTTEST
	private void buildGraph() {

		new AsyncTask<Void, Void, Graph>() {
			StopWatch sw = new StopWatch();

			@Override
			protected Graph doInBackground(Void... params) {
				graphAdapter.createDatabase();
				graphAdapter.open();
				sw.start();
				graph = graphAdapter.buildGraphDeep();
				sw.stop().getSeconds();
				graphAdapter.close();

				return graph;
			}

			@Override
			protected void onPostExecute(Graph result) {

				Log.i(TAG, "Graph ready! "+ sw);
				//XXX test graph
//				int i = 1;
//				for(Edge e : result.getEdges()){
//					System.out.println("("+ i + ") " +e);
//				 	i++;
//				}
				
				//XXX dijkstra
//				sw.start();
//				Dijkstra.computePaths(result.toVertex("1721121228"));
//				List<Vertex> path = Dijkstra.getShortestPathTo(result
//						.toVertex("1722835557"));
//				sw.stop().getSeconds();
//				Log.i(TAG,path + "\nDijkstra runtime " + sw);
				
				//XXX AStar
				sw.start();
				AStar aStar = new AStar();
				aStar.computePaths(result.toVertex("1721121228"), result
						.toVertex("1722835557"));
				List<Vertex> path = aStar.getShortestPath();
				Log.i(TAG,path + "\nAStar runtime " + sw);
				
			}
		}.execute();

	}

}
