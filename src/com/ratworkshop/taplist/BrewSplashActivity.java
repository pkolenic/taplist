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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.ratworkshop.taplist.contentprovider.PubContent;

public class BrewSplashActivity extends Activity {

	private static final String DEBUG_TAG = "BrewSplash";
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;

		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			new FetchPubLists().execute();
		} else {
			// @TODO need to load from local DB
			PubContent.parsePubLists(null, mContext);
			closeSplash();
		}

	}

	protected void closeSplash() {
		Intent listIntent = new Intent(this, BrewListActivity.class);
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
		Log.d(DEBUG_TAG, string);

		PubContent.parsePubLists(string, mContext);
	}

	private String getPubList() throws IOException {
		InputStream is = null;

		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
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
