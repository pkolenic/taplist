package com.ratworkshop.taplist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.interfaces.PublistDelegate;
import com.ratworkshop.taplist.tasks.FetchPublists;
import com.ratworkshop.taplist.tasks.LoadPublist;
import com.ratworkshop.taplist.utilities.Constants;
import com.urbanairship.push.PushManager;

public class BrewSplashActivity extends Activity implements PublistDelegate {
	private static final String DEBUG_TAG = "BrewSplash";
	private String pubId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);

		pubId = getIntent().getStringExtra(getString(R.string.ARG_PUB_ID));
		
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.taplist_preference), Context.MODE_PRIVATE);
		long now = System.currentTimeMillis();
		long then = sharedPref.getLong(getString(R.string.LAST_UPDATE), now);
		
		// If its been longer than an hour or now equals then (the app hasn't been used)
		if (now - then > Constants.HOUR || now == then || PubContent.isEmpty()) {
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				new FetchPublists(this).execute();
			} else {
				new LoadPublist(this).execute();
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
	
	@Override
	public void onPublistUpdated() {
		closeSplash();
	}
}
