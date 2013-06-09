package com.ratworkshop.taplist.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

public class FontDownloader extends IntentService {
    private static final String DEBUG_TAG = "FontDownloader";
    public static final String FONT_URL = "com.ratworkshop.taplist.service.ImageDownloader.FontURL";
    
	public FontDownloader() {
		super("FontDownloader");
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
				String filename = getFontName(bundle.getString(FONT_URL)); 
				if (filename == null) {
					// no point downloading something that doesn't exist
					Log.d(DEBUG_TAG, "Unable to download font at: " + bundle.getString(FONT_URL));
					return;
				}
			    String cacheDir = getCacheDir().getPath();
			    File fontDir = new File(cacheDir, "fonts");
			    fontDir.mkdir();
			    
			    File fontFile = new File(cacheDir, filename);
			    if (!fontFile.exists()) {
			    	Log.d(DEBUG_TAG, "Saving to: " + fontFile.getPath());
			    	
			    	is = new java.net.URL(bundle.getString(FONT_URL)).openStream();
			    	DataInputStream dis = new DataInputStream(is);
			        byte[] buffer = new byte[1024];
			        int length;

			    	FileOutputStream fos = new FileOutputStream(fontFile);   
			        while ((length = dis.read(buffer))>0) {
			            fos.write(buffer, 0, length);
			         }
			    	fos.flush();
			    	fos.close();
			    }
			} catch (MalformedURLException e) {
				Log.d(DEBUG_TAG, "Malformed URL \n" + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (IOException e) {
				Log.d(DEBUG_TAG, "IOException \n" + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (SecurityException se) {
			    Log.d(DEBUG_TAG, "security error");
			    se.printStackTrace();
		    }
        	
    	} else {
    		Log.d(DEBUG_TAG, "Network Not Avaliable to do download");
    	} 
	}

	private String getFontName(String path) {
    	URL url;
    	String fontname = null;
    	String filename = null;
		try {
			url = new URL(path);
			String[] parts = url.getFile().split("/");
			filename = parts[parts.length - 1];
			
			// Verify that the extension is a valid image
			parts = filename.split("\\.");
			if (parts.length == 2) {
				String ext = parts[1];
				
				if (ext.equals("ttf") ||
					ext.equals("otf")) {
					
					fontname = String.format("fonts/%s", filename);	
				} else {
					Log.d(DEBUG_TAG, filename + " is not a valid font file, wrong file type");	
					fontname = null;
				}
			} else {
		    	Log.d(DEBUG_TAG, filename + " is not a valid font file, too many parts");
		    	fontname = null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
	    	Log.d(DEBUG_TAG, path + " is malformed");
	    	fontname = null;
		}
		return fontname;
	}
}
