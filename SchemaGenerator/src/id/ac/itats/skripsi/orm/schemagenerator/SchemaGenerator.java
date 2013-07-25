package id.ac.itats.skripsi.orm.schemagenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class SchemaGenerator {
	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(1, "id.ac.itats.skripsi.orm");

		addGraph(schema);
		new DaoGenerator().generateAll(schema, "../SchemaGeneratorEmpat/src-gen");
	}

	private static void addGraph(Schema schema) {
		Entity node = schema.addEntity("Node");
		node.setTableName("NODE");
		node.addLongProperty("nodeID").primaryKey().index();
		node.addStringProperty("latitude");
		node.addStringProperty("longitude");

		Entity way = schema.addEntity("Way");
		way.setTableName("WAY");
		way.addIdProperty().autoincrement();
		way.addStringProperty("wayID");
		Property sourceNode = way.addLongProperty("sourceNode").getProperty();
		way.addToOne(node, sourceNode);
		way.addLongProperty("targetNode").index();
		way.addDoubleProperty("weight");

		
		ToMany adjacencies = node.addToMany(way, sourceNode);
		adjacencies.setName("adjacencies");
	}

}
