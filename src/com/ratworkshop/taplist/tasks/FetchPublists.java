package com.ratworkshop.taplist.tasks;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.database.DBHelper;
import com.ratworkshop.taplist.interfaces.PublistDelegate;

public class FetchPublists extends AsyncTask<String, Void, String> {

	private static final String DEBUG_TAG = "Fetch Publist Task";

	private PublistDelegate delegate;

	public FetchPublists(PublistDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	protected String doInBackground(String... params) {

		// params comes from the execute() call:
		try {
			return getPubList();
		} catch (IOException e) {
			PubContent.parsePubLists(null, ((Activity) delegate));
			return "Unable to retrieve web page. URL may be invalid.";
		} catch (Exception e) {
			PubContent.parsePubLists(null, ((Activity) delegate));
			return "Unable to retrieve web page. URL may be invalid.";
		}

	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		Log.d(DEBUG_TAG, "Fetch PubList is: " + result);
		if (result.equals("Success")) {
			delegate.onPublistUpdated();
	    	DBHelper.savePubList((Activity) delegate);
		} else {
			DBHelper.loadPubList((Activity) delegate);
			delegate.onPublistUpdated();
		}
	}

	private void parsePubLists(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		
		char[] buffer = new char[length];
		reader.read(buffer);
		
		String string = new String(buffer);
		
		reader.close();
		
		Activity activity = (Activity) delegate;
		PubContent.parsePubLists(string, activity);

		// Set the Last Update
		String preferenceFile = activity.getString(R.string.taplist_preference);
		SharedPreferences sharedPref = activity.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong(activity.getString(R.string.LAST_UPDATE), System.currentTimeMillis());
		editor.commit();
	}

	private String getPubList() throws Exception {
		InputStream is = null;

		try {
			URL url = new URL(((Activity) delegate).getString(
					R.string.get_all_pubs_url));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(20000 /* milliseconds */);
			conn.setConnectTimeout(2500 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", "Token token=pizza");
			conn.addRequestProperty("Accept",
					"application/ratworkshop.taplist.v1");

			// Starts the query
			conn.setUseCaches(false);
			conn.connect();
			
			int response = conn.getResponseCode();
			Log.d(DEBUG_TAG, "The response is: " + response);
			is = new BufferedInputStream(conn.getInputStream());

			parsePubLists(is, conn.getContentLength());
			return "Success";

			// Makes sure that the InputStream is closed after the application is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
