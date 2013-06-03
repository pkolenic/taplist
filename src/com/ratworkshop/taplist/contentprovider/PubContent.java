package com.ratworkshop.taplist.contentprovider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.service.ImageDownloader;

/**
 * Process the Pub JSON Data fetched from the Server
 * <p>
 */
public class PubContent {
	private static final String DEBUG_TAG = "PubContent";

    public static Map<String, List<Brew>> TAP_LISTS = new HashMap<String, List<Brew>>();
    public static ArrayList<String> PUB_LIST = new ArrayList<String>();
    public static Map<String, String> PUB_LOGOS = new HashMap<String, String>();
    public static Map<String, Brew> BREW_MAP = new HashMap<String, Brew>();

    private static String addLogo(String pubId, String path) {    	
    	URL url;
    	String filename = null;
		try {
			url = new URL(path);
			String[] parts = url.getFile().split("/");
			filename = parts[parts.length - 1];
			
			// Verify that the extension is a valid image
			parts = filename.split("\\.");
			if (parts.length == 2) {
				String ext = parts[1];
				
				if (ext.equals("jpg") ||
					ext.equals("png") ||
					ext.equals("gif") ||
					ext.equals("bmp")) {
					
					// Add to Logos
			    	PUB_LOGOS.put(pubId, filename);		
				} else {
					Log.d(DEBUG_TAG, filename + " is not a valid image file, wrong file type");	
					filename = null;
				}
			} else {
		    	Log.d(DEBUG_TAG, filename + " is not a valid image file, too many parts");
		    	filename = null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
	    	Log.d(DEBUG_TAG, path + " is malformed");
	    	filename = null;
		}
    	return filename;
    }
    
    private static void addPub(String pubId) {
    	PUB_LIST.add(pubId);
    	TAP_LISTS.put(pubId, new ArrayList<Brew>());
    }
    
    private static void addItem(Brew brew, String pubId) {
        TAP_LISTS.get(pubId).add(brew);
        BREW_MAP.put(brew.id, brew);
    }
    
    public static void parsePubLists(String pubListings, Context context) {
    	if (pubListings == null) {
    		addPub("No Pubs Available");
    		return;
    	}
    	
		// Create the Pubs and TapLists
		addPub("Meridian");
		addPub("Eagle");

		// Set Logos
		String filename = null;
		String url = "http://dc373.4shared.com/img/8FZsjxVO/s7/Monopoly.jpg";
		filename = addLogo("Meridian", url);
		if (filename != null) {
			// Download the file
			Intent intent = new Intent(context, ImageDownloader.class);
			intent.putExtra(ImageDownloader.IMAGE_NAME, filename);
			intent.putExtra(ImageDownloader.IMAGE_URL, url);
			context.startService(intent);	
		}
		
		url = "http://stuartsoperahouse.org/images/Upcoming-Events-Header.png";
		filename = addLogo("Eagle", url);
		if (filename != null) {
			// Download the file
			Intent intent = new Intent(context, ImageDownloader.class);
			intent.putExtra(ImageDownloader.IMAGE_NAME, filename);
			intent.putExtra(ImageDownloader.IMAGE_URL, url);
			context.startService(intent);	
		}

		// Eagle Tap List
		addItem(new Brew("1","Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false, R.drawable.belgian_ale), "Eagle");
		addItem(new Brew("2", "Mendocino Black Hawk Stout", "", 5.6, 3.49, 6.99, 13.99, false, R.drawable.porter_stout), "Eagle");
		addItem(new Brew("3", "Selkirk Abbey Saint Stephen Saison", "", 5.6, 3.69, 6.99, 13.99, false, R.drawable.wheat_beer), "Eagle");
		addItem(new Brew("4", "Crooked Fence Devil's Pick IPA", "", 7.0, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale), "Eagle");
		addItem(new Brew("5", "Green Flash Imperial Red Rye IPA", "", 8.5, 4.59, 10.59, 21.09, false, R.drawable.belgian_ale), "Eagle");
		addItem(new Brew("6", "Snake River Packed Porter", "", 6.6, 3.69, 7.39, 14.79, false, R.drawable.porter_stout), "Eagle");
		addItem(new Brew("7", "Goodlife Mt Rescue Pale Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub), "Eagle");
		addItem(new Brew("8", "Sockeye Winterfest Winter IPA", "", 6.8, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale), "Eagle");
		addItem(new Brew("9", "Payette Mutton Buster Brown Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub), "Eagle");
		addItem(new Brew("10", "Seven Brides Lil's Pils", "", 5.6, 3.00, 5.00, 9.00, true, R.drawable.classic_pilsner), "Eagle");

		// Meridian Tap List
		addItem(new Brew("11", "Pike Kilt Lifter Ruby Ale", "", 6.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub), "Meridian");
		addItem(new Brew("12", "Crooked Fence Pineapple IPA", "", 6.8, 3.49, 6.99, 13.99, true, R.drawable.belgian_ale), "Meridian");
		addItem(new Brew("13", "Grand Teton Lost Continent Double IPA", "", 8.0, 3.69, 8.49, 16.99, false, R.drawable.belgian_ale), "Meridian");
		addItem(new Brew("14", "Hoegaarden Belgian Wit", "", 4.9, 3.49, 6.99, 13.99, true, R.drawable.wheat_beer), "Meridian");
		addItem(new Brew("15", "New Belgium/Red Rock Paardebloem Ale", "", 9.0, 3.99, 9.29, 18.49, false, R.drawable.craft_pub), "Meridian");
		addItem(new Brew("16", "Salmon River Buzz Buzz Coffer Porter", "", 5.6, 3.69, 7.49, 14.99, true, R.drawable.porter_stout), "Meridian");
		addItem(new Brew("17", "North Coast Class of '88 Collaboration Barleywine", "", 10.0, 6.99, 22.39, 44.69, false, R.drawable.classic_pilsner), "Meridian");
		addItem(new Brew("18", "Payette Outlaw IPA", "", 6.2, 3.49, 6.99, 14.99, true, R.drawable.belgian_ale), "Meridian");
		addItem(new Brew("19", "Mendocino Peregrine Pilsner", "", 5.6, 3.00, 5.00, 9.00, false, R.drawable.classic_pilsner), "Meridian");
    }
}
