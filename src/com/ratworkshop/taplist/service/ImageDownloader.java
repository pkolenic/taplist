package com.ratworkshop.taplist.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.ratworkshop.taplist.utilities.FlushedInputStream;

public class ImageDownloader extends IntentService {
    private static final String DEBUG_TAG = "ImageDownloader";
    public static final String IMAGE_URL = "com.ratworkshop.taplist.service.ImageDownloader.ImageURL";
    public static final String IMAGE_NAME = "com.ratworkshop.taplist.service.ImageDownloader.ImageName";
    
	public ImageDownloader() {
		super("ImageDownloader");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected()) {
    		Bundle bundle = intent.getExtras();
    		// Download Image
        	InputStream is;
			try {
				String filename = bundle.getString(IMAGE_NAME);
				is = new java.net.URL(bundle.getString(IMAGE_URL)).openStream();
				Bitmap bmp = BitmapFactory.decodeStream(new FlushedInputStream(is));
				
			    String cacheDir = getCacheDir().getPath();
			    File imageFile = new File(cacheDir, filename);
			    FileOutputStream fos = new FileOutputStream(imageFile);   
				
				Log.d(DEBUG_TAG, "Saving to: " + imageFile.getPath());
				bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			} catch (MalformedURLException e) {
				Log.d(DEBUG_TAG, "Malformed URL \n" + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (IOException e) {
				Log.d(DEBUG_TAG, "IOException \n" + e.getLocalizedMessage());
				e.printStackTrace();
			}
        	
    	} else {
    		Log.d(DEBUG_TAG, "Network Not Avaliable to do download");
    	} 

	}

}
