package id.ac.itats.skripsi.orm.dbgenerator;

import id.ac.itats.skripsi.orm.Node;
import id.ac.itats.skripsi.orm.Way;
import id.ac.itats.skripsi.orm.generator.builder.Graph;
import id.ac.itats.skripsi.orm.generator.builder.GraphBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBGenerator {

	public static void main(String[] args) throws Exception {
		Connection c = null;
		Statement stmt = null;

		GraphBuilder builder = new GraphBuilder("data/osm/surabaya_new.osm");
		Graph graph = builder.getGraph();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:data/schema/GraphDB-1");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			// INSERT VERTEX
			for (Node node : graph.getVertices()) {

				String sqlInsertNode = "INSERT INTO NODE (NODE_ID, LATITUDE, LONGITUDE) "
						+ "VALUES ("
						+ node.getNodeID()
						+ ", "
						+ node.getLatitude()
						+ ", "
						+ node.getLongitude()
						+ ");";
				stmt.executeUpdate(sqlInsertNode);

				// INSERT EDGE

				for (Way way : node.getSourceAdjacentList()) {

					String sqlInsertEdge = "INSERT INTO WAY (WAY_ID, FK_SOURCE_NODE, FK_TARGET_NODE, WEIGHT)"
							+ " VALUES ("
							+ way.getWayID()
							+ ","
							+ way.getFk_sourceNode()
							+ ","
							+ way.getFk_targetNode()
							+ "," + way.getWeight() + " );";
					stmt.executeUpdate(sqlInsertEdge);

				}

			}

			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}
}
