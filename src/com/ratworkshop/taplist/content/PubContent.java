package com.ratworkshop.taplist.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.models.Pub;
import com.ratworkshop.taplist.service.FontDownloader;
import com.ratworkshop.taplist.utilities.TaplistTypeface;

/**
 * Process the Pub JSON Data fetched from the Server
 * <p>
 */
public class PubContent {
	
    public static Map<String, Pub> PUB_MAP = new HashMap<String, Pub>();
    public static List<Pub> PUB_LIST = new ArrayList<Pub>();
    
    public static void clearContent() {
    	PUB_MAP = new HashMap<String, Pub>();
    	PUB_LIST = new ArrayList<Pub>();
    }
    
    public static boolean isEmpty() {
    	return PUB_LIST.size() == 0;
    }
    
    public static void parsePubLists(String pubListings, Context context) {
    	if (pubListings == null) {
    		Pub pub = new Pub("No Pubs Available");
    		PUB_MAP.put("0", pub);
    		return;
    	}
    	
    	// Download Custom Fonts
		Intent intent = new Intent(context, FontDownloader.class);
		intent.putExtra(FontDownloader.FONT_URL, "https://s3.amazonaws.com/ratworkshop_taplist/fonts/spaceranger.ttf");
		context.startService(intent);	
    	
    	
		// Create the Pubs and TapLists
		Pub pub = new Pub("1", "Brewforia - Meridian",
				 "#607d32", TaplistTypeface.create("DEFAULT", "BOLD", false, context), 
				 "#607d32", TaplistTypeface.create("DEFAULT", "BOLD", false, context),
				 "#e9dcc8", TaplistTypeface.create("DEFAULT", "NORMAL", false, context), 
				 "#e9dcc8", TaplistTypeface.create("DEFAULT", "BOLD", false, context));
		pub.setLogo("https://s3.amazonaws.com/ratworkshop_taplist/logos/1/pub-1-logo-135156678.png", context);
		pub.setPubAddress( "101 Overland Rd", "Meridian", "ID", "83706");
		pub.setPubTitle("The Beer Garden", "#ffe8db", "MONOSPACE", "BOLD", false, 16.0f);
		pub.setPubSubtitle("Custom Beer", "#ffe8db", "MONOSPACE", "BOLD", false, 36.0f);
		pub.setPubSubheader("#e9dcc8", "DEFAULT", "NORMAL", false, 12.0f);
		pub.setHeader_color("#000000");
		pub.setSubheader_color("#000000");
		pub.setTaplist_background_color("#000000");
		
    	PUB_MAP.put(pub.getId(), pub);
    	PUB_LIST.add(pub);
    	
		// Meridian Tap List
    	pub.addBrew(new Brew("11", "Pike Kilt Lifter Ruby Ale", "", 6.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub, "ALE"));
    	pub.addBrew(new Brew("12", "Crooked Fence Pineapple IPA", "", 6.8, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale, "IPA"));
    	pub.addBrew(new Brew("13", "Grand Teton Lost Continent Double IPA", "", 8.0, 3.69, 8.49, 16.99, false, R.drawable.belgian_ale, "IPA"));
    	pub.addBrew(new Brew("14", "Hoegaarden Belgian Wit", "", 4.9, 3.49, 6.99, 13.99, true, R.drawable.wheat_beer, "WHEAT"));
    	pub.addBrew(new Brew("15", "New Belgium/Red Rock Paardebloem Ale", "", 9.0, 3.99, 9.29, 18.49, false, R.drawable.craft_pub, "ALE"));
		pub.addBrew(new Brew("16", "Salmon River Buzz Buzz Coffer Porter", "", 5.6, 3.69, 7.49, 14.99, false, R.drawable.porter_stout, "PORTER"));
		pub.addBrew(new Brew("17", "North Coast Class of '88 Collaboration Barleywine", "", 10.0, 6.99, 22.39, 44.69, false, R.drawable.classic_pilsner, "PILSNER"));
		pub.addBrew(new Brew("18", "Payette Outlaw IPA", "", 6.2, 3.49, 6.99, 14.99, false, R.drawable.belgian_ale, "IPA"));
		pub.addBrew(new Brew("19", "Mendocino Peregrine Pilsner", "", 5.6, 3.00, 5.00, 9.00, false, R.drawable.classic_pilsner, "PILSNER"));
    	
    	
		pub = new Pub("2", "Brewforia - Eagle",
				 "#607d32", TaplistTypeface.create("DEFAULT", "BOLD", false, context), 
				 "#607d32", TaplistTypeface.create("DEFAULT", "BOLD", false, context),
				 "#e9dcc8", TaplistTypeface.create("DEFAULT", "NORMAL", false, context), 
				 "#e9dcc8", TaplistTypeface.create("DEFAULT", "BOLD", false, context));
		pub.setLogo("", context);
		pub.setPubAddress("101 State St.", "Eagle", "ID", "83703");
		pub.setPubTitle("Beer Market", "#ffe8db", "fonts/Hieronfreymious boschian.ttf", "", true, 20.f);
		pub.setPubSubtitle("[taplist]", "#ffe8db", "spaceranger.ttf", "", true, 36.0f);
		pub.setPubSubheader("#e9dcc8", "DEFAULT", "NORMAL", false, 12.0f);
		pub.setHeader_color("#000000");
		pub.setSubheader_color("#000000");
		pub.setTaplist_background_color("#000000");
		
    	PUB_MAP.put(pub.getId(), pub);
    	PUB_LIST.add(pub);

		// Eagle Tap List
		pub.addBrew(new Brew("1","Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false, R.drawable.belgian_ale, "IPA"));
		pub.addBrew(new Brew("2", "Mendocino Black Hawk Stout", "", 5.6, 3.49, 6.99, 13.99, false, R.drawable.porter_stout, "STOUT"));
		pub.addBrew(new Brew("3", "Selkirk Abbey Saint Stephen Saison", "", 5.6, 3.69, 6.99, 13.99, false, R.drawable.wheat_beer, "WHEAT"));
		pub.addBrew(new Brew("4", "Crooked Fence Devil's Pick IPA", "", 7.0, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale, "IPA"));
		pub.addBrew(new Brew("5", "Green Flash Imperial Red Rye IPA", "", 8.5, 4.59, 10.59, 21.09, false, R.drawable.belgian_ale, "IPA"));
		pub.addBrew(new Brew("6", "Snake River Packed Porter", "", 6.6, 3.69, 7.39, 14.79, false, R.drawable.porter_stout, "PORTER"));
		pub.addBrew(new Brew("7", "Goodlife Mt Rescue Pale Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub, "ALE"));
		pub.addBrew(new Brew("8", "Sockeye Winterfest Winter IPA", "", 6.8, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale, "IPA"));
		pub.addBrew(new Brew("9", "Payette Mutton Buster Brown Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub, "ALE"));
		pub.addBrew(new Brew("10", "Seven Brides Lil's Pils", "", 5.6, 3.00, 5.00, 9.00, true, R.drawable.classic_pilsner, "PILSNER"));
		
		pub = new Pub("3", "Test",
				 "#607d32", TaplistTypeface.create("DEFAULT", "BOLD", false, context), 
				 "#99e521", TaplistTypeface.create("MONOSPACE", "BOLD", false, context),
				 "#e9dcc8", TaplistTypeface.create("DEFAULT", "NORMAL", false, context),
				 "#99e521", TaplistTypeface.create("DEFAULT", "BOLD", false, context));
		pub.setLogo("", context);
		pub.setPubAddress("10 Meridian Rd", "Meridian", "ID", "83713");
		pub.setPubTitle("Beer Market", "#FFFFFF", "SAN_SERIF", "ITALIC", false, 16.0f);
		pub.setPubSubtitle("[taplist]", "#000000", "DEFAULT_BOLD", "NORMAL", false, 36.0f);
		pub.setPubSubheader("#FF00FF", "MONOSPACE", "BOLD_ITALIC", false, 12.0f);
		pub.setHeader_color("#FF0000");
		pub.setSubheader_color("#00FF00");
		pub.setTaplist_background_color("#360707");

    	PUB_MAP.put(pub.getId(), pub);
    	PUB_LIST.add(pub);

		// Eagle Tap List
		pub.addBrew(new Brew("1","Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false, R.drawable.belgian_ale, "IPA"));
		pub.addBrew(new Brew("2", "Snake River Packed Porter", "", 6.6, 3.69, 7.39, 14.79, true, R.drawable.porter_stout, "PORTER"));
    }
}
