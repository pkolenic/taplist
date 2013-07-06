package com.ratworkshop.taplist.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.ratworkshop.taplist.database.DBHelper;
import com.ratworkshop.taplist.interfaces.PublistDelegate;

public class LoadPublist extends AsyncTask<String, Void, String> {

	private static final String DEBUG_TAG = "Load Publist Task";
	
	private PublistDelegate delegate;

	public LoadPublist(PublistDelegate delegate) {
		this.delegate = delegate;
	}
	
	@Override
	protected String doInBackground(String... params) {
		DBHelper.loadPubList((Activity) delegate);
		return "DB Loaded";
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		Log.d(DEBUG_TAG, "Fetch PubList is: " + result);
		delegate.onPublistUpdated();
	}
}
