package com.ratworkshop.taplist.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ratworkshop.taplist.database.DBHelper;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.models.Pub;
import com.ratworkshop.taplist.service.FontDownloader;

/**
 * Process the Pub JSON Data fetched from the Server
 * <p>
 */
public class PubContent {
	private static final String DEBUG_TAG = "PubContent";
    public static Map<String, Pub> PUB_MAP = new HashMap<String, Pub>();
    public static List<Pub> PUB_LIST = new ArrayList<Pub>();
    
    public static boolean isEmpty() {
    	return PUB_LIST.size() == 0;
    }
            
    public static void clearPubList() {
    	PUB_MAP = new HashMap<String, Pub>();
    	PUB_LIST = new ArrayList<Pub>();
    }
    
    public static void addPub(Pub pub) {
		PUB_MAP.put(pub.getId(), pub);
		PUB_LIST.add(pub);
    }
    
    public static void parsePubLists(String pubListings, Context context) {
    	clearPubList();
    	
    	if (pubListings == null) {
    		Pub pub = new Pub("No Pubs Available");
    		PUB_MAP.put("0", pub);
    		return;
    	}
    	
    	// Parse the JSON Response
    	JSONObject jObject;
    	
    	try {
    		jObject = new JSONObject(pubListings);
    	} catch (JSONException e) {
    		try {
				JSONArray jArray = new JSONArray(pubListings);
				jObject = jArray.getJSONObject(0);
			} catch (JSONException e1) {
				Log.d(DEBUG_TAG, String.format("Unable to parse pubListings: %s", e.getLocalizedMessage()));
				e.printStackTrace();
				
				// Try reading from DB
				DBHelper.loadPubList(context);
				
				if (PUB_LIST.isEmpty()) {
					Pub pub = new Pub("No Pubs Available");
	    			PUB_MAP.put("0", pub);
				}
	    		return;
			}
    	}
    	
    	// Check that the API is valid for this version of the APP
    	try {
			boolean upgrade = jObject.getBoolean("requireUpgrade");
			if (upgrade) {
				// TODO - Need to start new Activity that tells user to upgrade Application
				Log.d(DEBUG_TAG, "APP doesn't support this API");
				
	    		Pub pub = new Pub("No Pubs Available");
	    		PUB_MAP.put("0", pub);
	    		return;
			}
			
			
			JSONArray fonts = jObject.getJSONArray("fonts");
			for (int i=0; i < fonts.length(); i++)
			{
				String font = fonts.getString(i);
				Intent intent = new Intent(context, FontDownloader.class);
				intent.putExtra(FontDownloader.FONT_URL, font);
				context.startService(intent);
			}
			
			JSONArray pubs = jObject.getJSONArray("pubs");
			for (int i=0; i < pubs.length(); i++)
			{
				JSONObject pubData = pubs.getJSONObject(i);
				
				// Create Pub
				String pubId = pubData.getString("id");
				String pubName = pubData.getString("name");
				Pub pub = new Pub(pubId, pubName);
				
				String pubLogo = pubData.getString("logo");
				pub.setLogo(pubLogo, context);
				
				// Address
				JSONObject addressData = pubData.getJSONObject("address");
				String address = addressData.getString("address");
				String city = addressData.getString("city");
				String state = addressData.getString("state");
				String zip = addressData.getString("zip");
				pub.setPubAddress(address, city, state, zip);
				
				// Title
				String title = pubData.getString("title");
				JSONObject fontData = pubData.getJSONObject("title_font");
				String color = fontData.getString("text_color");
				String font = fontData.getString("font");
				String style = fontData.getString("style");
				boolean custom = fontData.getBoolean("custom");
				float list_size = (float) fontData.getDouble("list_size");
				float details_size = (float) fontData.getDouble("details_size");
				pub.setPubTitle(title, color, font, style, custom, list_size);
				
				// SubTitle
				title = pubData.getString("subtitle");
				fontData = pubData.getJSONObject("subtitle_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setPubSubtitle(title, color, font, style, custom, list_size);
				
				// Description
				fontData = pubData.getJSONObject("description_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setDescriptionStyles(color, font, style, custom, list_size);
				
				// SubHeader
				fontData = pubData.getJSONObject("subheader_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setPubSubheader(color, font, style, custom, list_size);
				
				// Tap List Styles
				fontData = pubData.getJSONObject("taplist_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setTaplistStyles(color, font, style, custom, list_size);
				
				// Featured Brew Styles
				fontData = pubData.getJSONObject("featured_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setFeaturedBrewStyles(color, font, style, custom, list_size);
				
				// Tap List Name Styles
				fontData = pubData.getJSONObject("brew_name_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setTaplistNameStyles(color, font, style, custom, list_size, details_size);
				
				// Featured Brew Name Styles
				fontData = pubData.getJSONObject("featured_brew_name_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setFeaturedBrewNameStyles(color, font, style, custom, list_size);
				
				// PubList Name Styles
				fontData = pubData.getJSONObject("name_font");
				color = fontData.getString("text_color");
				font = fontData.getString("font");
				style = fontData.getString("style");
				custom = fontData.getBoolean("custom");
				list_size = (float) fontData.getDouble("list_size");
				details_size = (float) fontData.getDouble("details_size");
				pub.setPublistStyles(color, font, style, custom, list_size);
				
				// Background Colors
				JSONObject colors = pubData.getJSONObject("backgrounds");
				pub.setHeader_color(colors.getString("header"));
				pub.setSubheader_color(colors.getString("subheader"));
				pub.setTaplist_background_color(colors.getString("taplist"));
				pub.setPublist_background_color(colors.getString("publist"));
				
				// Brews
				JSONArray brews = pubData.getJSONArray("brews");
				for (int j=0; j < brews.length(); j++)
				{
					JSONObject brew = brews.getJSONObject(j);
					
					String brewId = brew.getString("id");
					String brewName = brew.getString("name");
					String brewDesc = brew.getString("desc");
					float abv = (float) brew.getDouble("abv");
					float glass = (float) brew.getDouble("glass");
					float quart = (float) brew.getDouble("quart");
					float growler = (float) brew.getDouble("growler");
					boolean featured = brew.getBoolean("featured");
					String image = brew.getString("image");
					String type = brew.getString("type");
					
					pub.addBrew(new Brew(brewId, brewName, brewDesc, abv, glass, quart, growler, featured, image, type));
				}
				
				addPub(pub);
			}
		} catch (JSONException e) {
			e.printStackTrace();
    		Pub pub = new Pub("No Pubs Available");
    		PUB_MAP.put("0", pub);
    		return;
		}
    }
    
    
}
