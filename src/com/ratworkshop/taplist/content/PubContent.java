package com.ratworkshop.taplist.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.models.Pub;

/**
 * Process the Pub JSON Data fetched from the Server
 * <p>
 */
public class PubContent {
	
    public static Map<String, Pub> PUB_MAP = new HashMap<String, Pub>();
    public static List<String> PUB_LIST = new ArrayList<String>();
    
    public static void parsePubLists(String pubListings, Context context) {
    	if (pubListings == null) {
    		Pub pub = new Pub("No Pubs Available");
    		PUB_MAP.put(pub.getName(), pub);
    		return;
    	}
    	
		// Create the Pubs and TapLists
		Pub pub = new Pub("1", "Meridian", "10 Meridian Rd.", "Meridian",
				"ID", "83706",
				"http://dc373.4shared.com/img/8FZsjxVO/s7/Monopoly.jpg",
				"The Beer Garden", "Custom Beer", "#000000", "#ffe8db",
				"#ffe8db", "#000000", "#e9dcc8");
    	pub.downloadLogo(context);
    	PUB_MAP.put(pub.getName(), pub);
    	PUB_LIST.add(pub.getName());
    	
		// Meridian Tap List
    	pub.addBrew(new Brew("11", "Pike Kilt Lifter Ruby Ale", "", 6.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub));
    	pub.addBrew(new Brew("12", "Crooked Fence Pineapple IPA", "", 6.8, 3.49, 6.99, 13.99, true, R.drawable.belgian_ale));
    	pub.addBrew(new Brew("13", "Grand Teton Lost Continent Double IPA", "", 8.0, 3.69, 8.49, 16.99, false, R.drawable.belgian_ale));
    	pub.addBrew(new Brew("14", "Hoegaarden Belgian Wit", "", 4.9, 3.49, 6.99, 13.99, true, R.drawable.wheat_beer));
    	pub.addBrew(new Brew("15", "New Belgium/Red Rock Paardebloem Ale", "", 9.0, 3.99, 9.29, 18.49, false, R.drawable.craft_pub));
		pub.addBrew(new Brew("16", "Salmon River Buzz Buzz Coffer Porter", "", 5.6, 3.69, 7.49, 14.99, true, R.drawable.porter_stout));
		pub.addBrew(new Brew("17", "North Coast Class of '88 Collaboration Barleywine", "", 10.0, 6.99, 22.39, 44.69, false, R.drawable.classic_pilsner));
		pub.addBrew(new Brew("18", "Payette Outlaw IPA", "", 6.2, 3.49, 6.99, 14.99, true, R.drawable.belgian_ale));
		pub.addBrew(new Brew("19", "Mendocino Peregrine Pilsner", "", 5.6, 3.00, 5.00, 9.00, false, R.drawable.classic_pilsner));
    	
    	
		pub = new Pub("2", "Brewforia - Eagle", "101 State St.", "Eagle",
				"ID", "83703", "", "Beer Market", "[taplist]", "#000000",
				"#ffe8db", "#ffe8db", "#000000", "#e9dcc8");
    	pub.downloadLogo(context);
    	PUB_MAP.put(pub.getName(), pub);
    	PUB_LIST.add(pub.getName());

		// Eagle Tap List
		pub.addBrew(new Brew("1","Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false, R.drawable.belgian_ale));
		pub.addBrew(new Brew("2", "Mendocino Black Hawk Stout", "", 5.6, 3.49, 6.99, 13.99, false, R.drawable.porter_stout));
		pub.addBrew(new Brew("3", "Selkirk Abbey Saint Stephen Saison", "", 5.6, 3.69, 6.99, 13.99, false, R.drawable.wheat_beer));
		pub.addBrew(new Brew("4", "Crooked Fence Devil's Pick IPA", "", 7.0, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale));
		pub.addBrew(new Brew("5", "Green Flash Imperial Red Rye IPA", "", 8.5, 4.59, 10.59, 21.09, false, R.drawable.belgian_ale));
		pub.addBrew(new Brew("6", "Snake River Packed Porter", "", 6.6, 3.69, 7.39, 14.79, false, R.drawable.porter_stout));
		pub.addBrew(new Brew("7", "Goodlife Mt Rescue Pale Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub));
		pub.addBrew(new Brew("8", "Sockeye Winterfest Winter IPA", "", 6.8, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale));
		pub.addBrew(new Brew("9", "Payette Mutton Buster Brown Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub));
		pub.addBrew(new Brew("10", "Seven Brides Lil's Pils", "", 5.6, 3.00, 5.00, 9.00, true, R.drawable.classic_pilsner));
		
		pub = new Pub("3", "Brewforia - Meridian", "101 Overland Rd.", "Meridian",
				"ID", "83713", "", "Beer Market", "[taplist]", "#FF0000",
				"#FFFFFF", "#000000", "#00FF00", "#FF00FF");
    	pub.downloadLogo(context);
    	PUB_MAP.put(pub.getName(), pub);
    	PUB_LIST.add(pub.getName());

		// Eagle Tap List
		pub.addBrew(new Brew("1","Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false, R.drawable.belgian_ale));
    }
}
