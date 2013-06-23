package com.ratworkshop.taplist;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.ratworkshop.taplist.content.PubContent;
import com.urbanairship.push.PushManager;

public class BrewSplashActivity extends Activity {

//	private static final int HOUR = 1000 * 60 * 60;
	private static final int HOUR = 10;		// 10 Seconds should be good for testing
	private static final String DEBUG_TAG = "BrewSplash";
	private static final String LAST_UPDATE = "com.ratworkshop.taplist.lastupdate";
	private Context mContext;
	private String pubId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		mContext = this;

		pubId = getIntent().getStringExtra(getString(R.string.ARG_PUB_ID));
		
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		long now = System.currentTimeMillis();
		long then = sharedPref.getLong(LAST_UPDATE, now);
		
		// If its been longer than an hour or now equals then (the app hasn't been used)
		if (now - then > HOUR || now == then || PubContent.isEmpty()) {
			PubContent.clearContent();
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				new FetchPubLists().execute();
			} else {
				// TODO need to load from local DB
				PubContent.parsePubLists(null, mContext);
				closeSplash();
			}	
		} else {
			closeSplash();
		}
	}

	protected void closeSplash() {
		PushManager.enablePush();
		String apid = PushManager.shared().getAPID();
		Log.d(DEBUG_TAG, "###=### App APID: " + apid);
		
		Intent listIntent = new Intent(this, BrewListActivity.class);
		if (pubId != null) {
			listIntent.putExtra(getString(R.string.ARG_PUB_ID), pubId);
		}
			
		startActivity(listIntent);
	}

	/* Image Download Methods and private Class */
	private class FetchPubLists extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			// params comes from the execute() call:
			try {
				return getPubList();
			} catch (IOException e) {
				PubContent.parsePubLists(null, mContext);
				return "Unable to retrieve web page. URL may be invalid.";
			} catch (Exception e) {
				PubContent.parsePubLists(null, mContext);
				return "Unable to retrieve web page. URL may be invalid.";
			}

		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Log.d(DEBUG_TAG, "Fetch PubList is: " + result);
			closeSplash();
		}

	}

	private void parsePubLists(InputStream stream) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[stream.available()];
		reader.read(buffer);
		String string = new String(buffer);

		PubContent.parsePubLists(string, mContext);
		
		// Set the Last Update
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong(LAST_UPDATE, System.currentTimeMillis());
		editor.commit();
	}

	private String getPubList() throws Exception {
		InputStream is = null;

		try {
			URL url = new URL(getString(R.string.get_all_pubs_url));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", "Token token=pizza");
			conn.addRequestProperty("Accept", "application/ratworkshop.taplist.v1");
			// Starts the query
			conn.connect();
			int response = conn.getResponseCode();
			Log.d(DEBUG_TAG, "The response is: " + response);
			is = conn.getInputStream();

			parsePubLists(is);
			return "Success";

			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
