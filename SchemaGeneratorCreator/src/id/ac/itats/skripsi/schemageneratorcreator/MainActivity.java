package id.ac.itats.skripsi.schemageneratorcreator;

import id.ac.itats.skripsi.orm.DaoMaster;
import id.ac.itats.skripsi.orm.DaoMaster.DevOpenHelper;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "GraphDB-1",
				null);
		db = helper.getWritableDatabase();
		new DaoMaster(db);
	
		
		Log.i("Schema Creator", "Success!");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
