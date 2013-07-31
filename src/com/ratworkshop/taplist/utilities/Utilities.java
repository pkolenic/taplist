package com.ratworkshop.taplist.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utilities {
	public static boolean isOnline(Context context) {
	    ConnectivityManager connMgr = (ConnectivityManager) 
	            context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();	    
	    return (networkInfo != null && networkInfo.isConnected());
	} 
}