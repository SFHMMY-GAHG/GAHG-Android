package com.spydi2kood.gahg;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The main activity of the App. Its view is at res/layout/activity_main.
 */
public class MainActivity extends ActionBarActivity {

	private EditText editText;
	private Button button;
	private TextView textView;
	/**
	 * First function to be called when opening the app.
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Find all views from layout. Function findViewById() must be called after setContentView().
		editText = (EditText) findViewById(R.id.mEditText);
		button = (Button) findViewById(R.id.mButton);
		textView = (TextView) findViewById(R.id.mTextView);
	}

	/**
	 * Function creating the options menu. The menu view is at res/menu/main.
	 *
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * This function is called whenever an option is selected from the options menu.
	 *
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
