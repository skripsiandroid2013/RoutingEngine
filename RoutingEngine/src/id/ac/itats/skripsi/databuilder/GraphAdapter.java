package id.ac.itats.skripsi.databuilder;

import id.ac.itats.skripsi.orm.DaoMaster;
import id.ac.itats.skripsi.orm.DaoMaster.DevOpenHelper;
import id.ac.itats.skripsi.orm.DaoSession;
import id.ac.itats.skripsi.orm.Node;
import id.ac.itats.skripsi.orm.NodeDao;
import id.ac.itats.skripsi.orm.Way;
import id.ac.itats.skripsi.orm.WayDao;
import id.ac.itats.skripsi.shortestpath.model.Graph;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

// test fetch arga
public class GraphAdapter {
	protected static final String TAG = "GraphAdapter";

	private final Context mContext;
	private SQLiteDatabase mDb;
	private DataBaseHelper mDbHelper;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private WayDao wayDao;
	private NodeDao nodeDao;
	private Graph graph;

	private Cursor cursor;

	public GraphAdapter(Context context) {
		this.mContext = context;
		// prepareDB();
		graph = new Graph();
		mDbHelper = new DataBaseHelper(mContext);
	}

	@SuppressWarnings("unused")
	// untuk generate schema dari greendao entity
	private void prepareDB() {
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext,
				"GraphDB-1", null);
		mDb = helper.getReadableDatabase();
		daoMaster = new DaoMaster(mDb);
	}

	public GraphAdapter createDatabase() throws SQLException {
		try {
			mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
			throw new Error("Unable to create database");

		}
		return this;

	}

	public GraphAdapter open() throws SQLException {
		try {
			mDbHelper.openDataBase();
			mDbHelper.close();
			mDb = mDbHelper.getReadableDatabase();
			daoMaster = new DaoMaster(mDb);
			daoSession = daoMaster.newSession();
			wayDao = daoSession.getWayDao();
			nodeDao = daoSession.getNodeDao();

		} catch (SQLException mSQLException) {
			Log.e(TAG, "open >>" + mSQLException.toString());
			throw mSQLException;
		}
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public Graph getGraph() {
		return graph;
	}

	// XXX GRAPHQUERY
	public Way getEdge(String wayID) {
		return wayDao.queryBuilder().where(WayDao.Properties.WayID.eq(wayID))
				.list().get(0);
	}

	public Node getNode(String nodeID) {
		return nodeDao.queryBuilder()
				.where(NodeDao.Properties.NodeID.eq(nodeID)).list().get(0);
	}

	public Node getNode(Long nodeID) {
		return nodeDao.queryBuilder()
				.where(NodeDao.Properties.NodeID.eq(nodeID)).list().get(0);
	}

	//FIXME cursor window full
	// XXX 49.45247s
	public Graph buildGraph() {
		List<Way> result = wayDao.queryBuilder().orderAsc(WayDao.Properties.Id)
				.list();
		for (Way way : result) {
			this.graph.addEdge(way.getWayID(),way.getSourceNode(),way.getTargetNode(), way.getWeight());
		}

		return this.graph;
	}

	// XXX better 41.885956s
	public Graph buildGraph2() {

		String sql = "SELECT * from WAY ORDER BY WAY_ID";
		cursor = mDb.rawQuery(sql, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Way way = cursorToWay(cursor);

			this.graph.addEdge(way.getWayID(), way.getSourceNode(),
					way.getTargetNode(), way.getWeight());

			cursor.moveToNext();
		}
		cursor.close();

		return this.graph;

	}
	
	//XXX 51.73883 Second (bind node to vertex, use for astar heuristic calculation)
	public Graph buildGraphDeep(){
		String wayId = WayDao.Properties.Id.columnName;
	//	int rowCount = mDb.rawQuery("SELECT "+ wayId +" FROM WAY", null).getCount();
		int rowCount = 53702;
		
		int i = 0;
		int j = 1000;
		do {
		List<Way> ways = wayDao.queryDeep("WHERE T." + wayId + " BETWEEN ? AND ?  ", new String[]{""+i , ""+j});
		for(Way way : ways) {
			this.graph.addEdge(way.getWayID(),way.getSourceNode(),
					way.getTargetNode(), way.getWeight());
		}		
		i = j+1;
		j += 1000;
		
		} while (j<=rowCount);
	
		List<Way> ways = wayDao.queryDeep("WHERE T." + wayId + ">?", ""+(j - 1000));
		for(Way way : ways) {
			this.graph.addEdge(way.getWayID(),way.getSourceNode(),
					way.getTargetNode(), way.getWeight());
		}
		
//		Log.i("Graph-Edge", ""+graph.getEdges().size());
//		Log.i("Graph-vertex", ""+graph.getVertices().size());
		
		return graph;
	}

	//
	// public String buildNode() {
	// HashMap<Long, Node> vertices = graph.getVertices();
	//
	// String sql = "SELECT * from Node ORDER BY NODE_ID";
	// cursor = mDb.rawQuery(sql, null);
	// cursor.moveToFirst();
	// while (!cursor.isAfterLast()) {
	// Node node = cursorToNode(cursor);
	// vertices.put(node.getNodeID(), node);
	//
	// cursor.moveToNext();
	// }
	// cursor.close();
	// return "" + graph.getVertices().size();
	//
	// }
	//
	private Way cursorToWay(Cursor cursor) {
		Way way = new Way();
		way.setWayID(cursor.getString(1));
		way.setFk_sourceNode(cursor.getLong(2));
		way.setFk_targetNode(cursor.getLong(3));
		way.setWeight(cursor.getDouble(4));
		return way;
	}
	//
	// private Node cursorToNode(Cursor cursor) {
	// Node node = new Node();
	// node.setNodeID(cursor.getLong(0));
	// node.setLatitude(cursor.getString(1));
	// node.setLongitude(cursor.getString(2));
	// return node;
	// }
	// XXX End better

}
