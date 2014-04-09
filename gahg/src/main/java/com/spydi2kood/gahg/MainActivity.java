package com.spydi2kood.gahg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * The main activity of the App. Its view is at res/layout/activity_main.
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private EditText editText;
	private Button button;
	private TextView textView;
	private AQuery aQuery;

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

		//Declare onClick listener for button
		button.setOnClickListener(this);

		//Initialize the asynchronous query object
		aQuery = new AQuery(this);
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
			startActivity(new Intent(this, SettingsActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This function is called when the button is clicked. The same button would be called if any other view with the same onClickListener is clicked.
	 *
	 * @param view
	 */
	@Override
	public void onClick(View view) {
		Log.d(TAG, "onClick");
		//Check for the id of the button
		switch (view.getId()) {
			case R.id.mButton:
				//				Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show();
				//				new Executor().execute();
				serverRequest();
		}
	}

	/**
	 * Function to update the textView with the username and the message from the editText
	 */
	private void execute() {
		//retrieve the text of the editText
		String mText = editText.getText().toString();
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		//get the username from settings
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String mUsername = sharedPreferences.getString("mTextPreference", "");
		Log.d(TAG, "username: " + mUsername);

		//if nothing is empty, set the concatenation of them at textView
		if (!mText.equals("") && !mUsername.equals("")) textView.setText(mUsername.concat(": " + mText));
	}

	/**
	 * This function send the username and message to the specified url
	 */
	public void serverRequest() {
		//initialize url, username and message to be sent
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String url = "http://" + sharedPreferences.getString("serverIP", "") + "/GAHG-Grails/android/postit/";
		String mUsername = sharedPreferences.getString("mTextPreference", "");
		String mText = editText.getText().toString();
		Log.d(TAG, "Sending to url: " + url + " ,mUsername: " + mUsername + " ,mText: " + mText);
		//prepare a Hashmap to hold all the parameters (needed by aQuery)
		HashMap<String, String> params = new HashMap<String, String>();

		//if nothing is empty, put the inside the parameters and send the asynchronous query
		if (!mText.equals("") && !mUsername.equals("")) {
			params.put("username", mUsername);
			params.put("post", mText);
			aQuery.ajax(url, params, JSONObject.class, this, "response");
			Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Request Not Sent. Username and Message cannot be empty", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * This function is called when server request finishes.
	 *
	 * @param url        URL of the response
	 * @param jsonObject This object has the response of the server in JSON format
	 * @param ajaxStatus The status of the response
	 */
	public void response(String url, JSONObject jsonObject, AjaxStatus ajaxStatus) {
		Log.d(TAG, "response");
		if (jsonObject != null) {
			try {
				//retrieve the response
				String response = jsonObject.getString("status");
				Log.d(TAG, "Server Response: " + response);

				//display the response at textView
				textView.setText(response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.d(TAG, "Response is null");
			textView.setText("No response :(");
		}
	}

	/**
	 * AsyncTask to do work on background. This way the UI doesn't freeze.
	 */
	public class Executor extends AsyncTask<Void, Void, String> {

		String mText;
		String mUsername;

		/**
		 * This function runs on UI and is used to initialize the variables needed fot the thread. It starts automatically when you call .execute() on an AsyncTask.
		 * When it finishes it calls doInBackground() automatically.
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d(TAG, "onPreExecute");

			//initialize message and username
			mText = editText.getText().toString();
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			mUsername = sharedPreferences.getString("mTextPreference", "");
			Log.d(TAG, "username: " + mUsername);
		}

		/**
		 * This function runs at the background and it executes automatically after onPreExecuted. It is used to do all the heavy work needed.
		 * When it finishes it calls onPostExecute() automatically.
		 *
		 * @param voids nothing is needed.
		 * @return aString to be shown at textView.
		 */
		@Override
		protected String doInBackground(Void... voids) {
			Log.d(TAG, "doInBackground");

			//Heavy work :P
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//if nothing is empty, return the concatenation of username and message
			if (!mText.equals("") && !mUsername.equals("")) {
				return mUsername.concat(": " + mText);
			} else {
				return "";
			}
		}

		/**
		 * This function runs on UI and executes after doInBackground() automatically. It is used to do the final work before AsyncTask terminates.
		 *
		 * @param aString receives the text to be shown at textView.
		 */
		@Override
		protected void onPostExecute(String aString) {
			super.onPostExecute(aString);
			Log.d(TAG, "onPostExecute");

			//if there is text to be displayed set it to the textView.
			if (!aString.equals("")) textView.setText(aString);
		}
	}
}
