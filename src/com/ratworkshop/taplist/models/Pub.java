package com.ratworkshop.taplist.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ratworkshop.taplist.service.ImageDownloader;
import com.ratworkshop.taplist.utilities.TaplistTypeface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

public class Pub {
	private static final String DEBUG_TAG = "Pub";
	
	private String id;
	private String name;
	private String logo;
	
	/* Address */
	private String address;
	private String city;
	private String state;
	private String zip;
	
	/* Title */
	private String title;
	private String title_color;
	private float title_size;
	private Typeface title_typeface;
	private String title_font;
	private String title_style;
	private boolean title_font_custom;
	
	/* SubTitle */
	private String subtitle;
	private String subtitle_color;
	private float subtitle_size;
	private Typeface subtitle_typeface;
	private String subtitle_font;
	private String subtitle_style;
	private boolean subtitle_font_custom;
	
	/* SubHeader */
	private String subheader_text_color;
	private float subheader_size;
	private Typeface subheader_typeface;
	private String subheader_font;
	private String subheader_style;
	private boolean subheader_font_custom;	
	
	/* Description */
	private String description_text_color;
	private float description_size;
	private Typeface description_typeface;
	private String description_font;
	private String description_style;
	private boolean description_font_custom;	
	
	/* Background Colors */
	private int header_color;
	private int subheader_color;
	private int taplist_background_color;
	
	/* Tap List Details */
	private String taplist_color;
	private float taplist_size;
	private Typeface taplist_typeface;
	private String taplist_font;
	private String taplist_style;
	private boolean taplist_font_custom;
	
	/* Tap List Featured Details */
	private String featured_brew_color;
	private float featured_brew_size;
	private Typeface featured_brew_typeface;
	private String featured_brew_font;
	private String featured_brew_style;
	private boolean featured_brew_font_custom;
	
	/* Tap List Name */
	private String taplist_name_color;
	private float taplist_name_size;
	private float taplist_name_details_size;
	private Typeface taplist_name_typeface;
	private String taplist_name_font;
	private String taplist_name_style;
	private boolean taplist_name_font_custom;
		
	/* Tap List Featured Name */
	private String featured_brew_name_color;
	private float featured_brew_name_size;
	private Typeface featured_brew_name_typeface;
	private String featured_brew_name_font;
	private String featured_brew_name_style;
	private boolean featured_brew_name_font_custom;
	
	
	/* Brews */
	private List<Brew> tap_list;
	private Map<String, Brew> brew_map = new HashMap<String, Brew>();
	
	public Pub(String name) {
		this("0", name);
	}
	
	/**
	 * Creates the Basic Pub
	 * @param id
	 * @param name
	 */
	public Pub(String id, String name) {
		this.id = id;
		this.name = name;
		tap_list = new ArrayList<Brew>();
		setPubAddress("", "", "", "");
		setPubTitle("", "#ff8db", "MONOSPACE", "BOLD", false, 16.0f);
		setPubSubtitle("", "#ff8db", "MONOSPACE", "NORMAL", false, 36.0f);
		setPubSubheader("#e9dcc8", "DEFAULT", "NORMAL", false, 12.0f);
		setDescriptionStyles("#e9dcc8", "DEFAULT", "BOLD", false, 16.0f);
		setTaplistStyles("#e9dcc8", "DEFAULT", "NORMAL", false, 12.0f);
		setFeaturedBrewStyles("#607d32", "DEFAULT", "BOLD", false, 12.0f);
		setTaplistNameStyles("#e9dcc8", "DEFAULT", "BOLD", false, 12.0f, 18.0f);
		setFeaturedBrewNameStyles("#607d32", "DEFAULT", "BOLD", false, 12.0f);
		setHeader_color("#000000");
		setSubheader_color("#000000");
		setTaplist_background_color("#000000");
	}

	/**
	 * Sets the Address Attributes of the Pub
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 */
	public void setPubAddress(String address, String city, String state, String zip) {
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.state = state;		
	}
	
	/**
	 * Sets the Title Attributes of the Pub
	 * @param title
	 * @param title_color
	 * @param title_font
	 * @param title_style
	 * @param title_font_custom
	 * @param title_size
	 */
	public void setPubTitle(String title, String title_color, String title_font, String title_style, boolean title_font_custom, float title_size) {
		this.title = title;
		this.title_color = title_color;
		this.title_font = title_font;
		this.title_style = title_style;
		this.title_font_custom = title_font_custom;
		this.title_size = title_size;
	}
	
	/**
	 * Sets the Subtitle Attributes of the Pub
	 * @param subtitle
	 * @param subtitle_color
	 * @param subtitle_font
	 * @param subtitle_style
	 * @param subtitle_font_custom
	 * @param subtitle_size
	 */
	public void setPubSubtitle(String subtitle, String subtitle_color, String subtitle_font, String subtitle_style, boolean subtitle_font_custom, float subtitle_size) {
		this.subtitle = subtitle;
		this.subtitle_color = subtitle_color;
		this.subtitle_font = subtitle_font;
		this.subtitle_style = subtitle_style;
		this.subtitle_font_custom = subtitle_font_custom;
		this.subtitle_size = subtitle_size;
	}
	
	/**
	 * Sets the SubHeader Attributes of the Pub
	 * @param subheader_color
	 * @param subheader_text_color
	 * @param subheader_font
	 * @param subheader_style
	 * @param subheader_font_custom
	 * @param subheader_size
	 */
	public void setPubSubheader(String subheader_text_color, String subheader_font, String subheader_style, boolean subheader_font_custom, float subheader_size) {
		this.subheader_text_color = subheader_text_color;
		this.subheader_font = subheader_font;
		this.subheader_style = subheader_style;
		this.subheader_font_custom = subheader_font_custom;
		this.subheader_size = subheader_size;
	}
	
	/**
	 * Sets the Description Styles
	 * @param description_text_color
	 * @param description_font
	 * @param description_style
	 * @param description_font_custom
	 * @param description_size
	 */
	public void setDescriptionStyles(String description_text_color, String description_font, String description_style, boolean description_font_custom, float description_size) {
		this.description_text_color = description_text_color;
		this.description_font = description_font;
		this.description_style = description_style;
		this.description_font_custom = description_font_custom;
		this.description_size = description_size;
	}
	
	/**
	 * Sets the Tap List Text Styles
	 * @param taplist_color
	 * @param taplist_font
	 * @param taplist_style
	 * @param taplist_font_custom
	 * @param taplist_size
	 */
	public void setTaplistStyles(String taplist_color, String taplist_font, String taplist_style, boolean taplist_font_custom, float taplist_size) {
		this.taplist_color = taplist_color;
		this.taplist_size = taplist_size;
		this.taplist_font = taplist_font;
		this.taplist_style = taplist_style;
		this.taplist_font_custom = taplist_font_custom;
	}
	
	/**
	 * Sets the Featured Brew Styles
	 * @param featured_brew_color
	 * @param featured_brew_font
	 * @param featured_brew_style
	 * @param featured_brew_font_custom
	 * @param featured_brew_size
	 */
	public void setFeaturedBrewStyles(String featured_brew_color, String featured_brew_font, String featured_brew_style, boolean featured_brew_font_custom, float featured_brew_size) {
		this.featured_brew_color = featured_brew_color;
		this.featured_brew_size = featured_brew_size;
		this.featured_brew_font = featured_brew_font;
		this.featured_brew_style = featured_brew_style;
		this.featured_brew_font_custom = featured_brew_font_custom;
	}
	
	/**
	 * Sets the Taplist Name Styles
	 * @param taplist_name_color
	 * @param taplist_name_font
	 * @param taplist_name_style
	 * @param taplist_name_font_custom
	 * @param taplist_name_size
	 * @param taplist_name_details_size
	 */
	public void setTaplistNameStyles(String taplist_name_color, String taplist_name_font, String taplist_name_style, boolean taplist_name_font_custom, float taplist_name_size, float taplist_name_details_size) {
		this.taplist_name_color = taplist_name_color;
		this.taplist_name_font = taplist_name_font;
		this.taplist_name_style = taplist_name_style;
		this.taplist_name_font_custom = taplist_name_font_custom;
		this.taplist_name_size = taplist_name_size;
		this.taplist_name_details_size = taplist_name_details_size;
	}
	
	/**
	 * Sets the Featured Brew Name Styles
	 * @param featured_brew_name_color
	 * @param featured_brew_name_font
	 * @param featured_brew_name_style
	 * @param featured_brew_name_font_custom
	 * @param featured_brew_name_size
	 */
	public void setFeaturedBrewNameStyles(String featured_brew_name_color, String featured_brew_name_font, String featured_brew_name_style, boolean featured_brew_name_font_custom, float featured_brew_name_size) {
		this.featured_brew_name_color = featured_brew_name_color;
		this.featured_brew_name_size = featured_brew_name_size;
		this.featured_brew_name_font = featured_brew_name_font;
		this.featured_brew_name_style = featured_brew_name_style;
		this.featured_brew_name_font_custom = featured_brew_name_font_custom;
	}
	
	/**
	 * Adds a brew to the tap list
	 * @param brew
	 */
	public void addBrew(Brew brew) {
		tap_list.add(brew);
        brew_map.put(brew.getId(), brew);
	}
	
	/**
	 * Gets a brew from the tap list
	 * @param brewId
	 * @return
	 */
	public Brew getBrew(String brewId) {
		return brew_map.get(brewId);
	}
	
	/* ADDRESS GETTERS */
	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}
	
	/* TITLE GETTERS */
	public String getTitle() {
		return title;
	}
	
	public String getTitle_color() {
		return title_color;
	}

	public Typeface getTitle_typeface(Context context) {
		if (title_typeface == null) {
			title_typeface = TaplistTypeface.create(title_font, title_style, title_font_custom, context);
		}
		return title_typeface;
	}

	public float getTitle_size() {
		return title_size;
	}

	/* SUBTITLE GETTERS */
	public String getSubtitle() {
		return subtitle;
	}
	
	public String getSubtitle_color() {
		return subtitle_color;
	}
	
	public Typeface getSubtitle_typeface(Context context) {
		if (subtitle_typeface == null) {
			subtitle_typeface = TaplistTypeface.create(subtitle_font, subtitle_style, subtitle_font_custom, context);
		}
		return subtitle_typeface;
	}

	public float getSubtitle_size() {
		return subtitle_size;
	}
	
	/* SUBHEADER GETTERS */
	public String getSubheader_text_color() {
		return subheader_text_color;
	}
	
	public Typeface getSubheader_typeface(Context context) {
		if (subheader_typeface == null) {
			subheader_typeface = TaplistTypeface.create(subheader_font, subheader_style, subheader_font_custom, context);
		}
		return subheader_typeface;
	}
	

	public float getSubheader_size() {
		return subheader_size;
	}
	
	/* DESCRIPTION GETTERS */
	public String getDescription_text_color() {
		return description_text_color;
	}
	
	public Typeface getDescription_typeface(Context context) {
		if (description_typeface == null) {
			description_typeface = TaplistTypeface.create(description_font, description_style, description_font_custom, context);
		}
		return description_typeface;
	}
	
	public float getDescription_size() {
		return description_size;
	}
	
	/* TAPLIST GETTERS */
	public String getTaplist_color() {
		return taplist_color;
	}

	public Typeface getTaplist_typeface(Context context) {
		if (taplist_typeface == null) {
			taplist_typeface = TaplistTypeface.create(taplist_font, taplist_style, taplist_font_custom, context);
		}
		return taplist_typeface;
	}
	
	public float getTaplist_size() {
		return taplist_size;
	}
	
	/* FEATURED GETTERS */
	public String getFeatured_brew_color() {
		return featured_brew_color;
	}

	public Typeface getFeatured_brew_typeface(Context context) {
		if (featured_brew_typeface == null) {
			featured_brew_typeface = TaplistTypeface.create(featured_brew_font, featured_brew_style, featured_brew_font_custom, context);
		}
		return featured_brew_typeface;
	}
	
	public float getFeatured_brew_size() {
		return featured_brew_size;
	}
	
	/* TAPLIST NAME GETTERS */
	public String getTaplist_name_color() {
		return taplist_name_color;
	}

	public Typeface getTaplist_name_typeface(Context context) {
		if (taplist_name_typeface == null) {
			taplist_name_typeface = TaplistTypeface.create(taplist_name_font, taplist_name_style, taplist_name_font_custom, context);
		}
		return taplist_name_typeface;
	}
	
	public float getTaplist_name_size() {
		return taplist_name_size;
	}
	
	public float getTaplist_name_details_size() {
		return taplist_name_details_size;
	}
	
	/* TAPLIST FEATURED NAME GETTERS */
	public String getFeatured_brew_name_color() {
		return featured_brew_name_color;
	}

	public Typeface getFeatured_brew_name_typeface(Context context) {
		if (featured_brew_name_typeface == null) {
			featured_brew_name_typeface = TaplistTypeface.create(featured_brew_name_font, featured_brew_name_style, featured_brew_name_font_custom, context);
		}
		return featured_brew_name_typeface;
	}
	
	public float getFeatured_brew_name_size() {
		return featured_brew_name_size;
	}
	
	/* LOGO */
	private void downloadLogo(String name, String path, Context context) {
    	if (logo != null) {
			// Download the file
			Intent intent = new Intent(context, ImageDownloader.class);
			intent.putExtra(ImageDownloader.IMAGE_NAME, name);
			intent.putExtra(ImageDownloader.IMAGE_URL, path);
			context.startService(intent);	
    	}
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String path, Context context) {
		if (path == null || path.equals("")) {
			// no path so no point trying to download
			return;
		}
		
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
					
					logo = filename;	
					downloadLogo(logo, path, context);
				} else {
					Log.d(DEBUG_TAG, filename + " is not a valid image file, wrong file type");	
					logo = null;
				}
			} else {
		    	Log.d(DEBUG_TAG, filename + " is not a valid image file, too many parts");
		    	logo = null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
	    	Log.d(DEBUG_TAG, path + " is malformed");
	    	logo = null;
		}
	}
	
	/* BACKGROUND COLORS */
	public int getHeader_color() {
		return header_color;
	}

	public void setHeader_color(String header_color) {
		this.header_color = Color.parseColor(header_color);
	}	
	
	public int getSubheader_color() {
		return subheader_color;
	}
	
	public void setSubheader_color(String subheader_color) {
		this.subheader_color = Color.parseColor(subheader_color);
	}
	
	public int getTaplist_background_color() {
		return taplist_background_color;
	}

	public void setTaplist_background_color(String taplist_background_color) {
		this.taplist_background_color = Color.parseColor(taplist_background_color);
	}
	
	/* GETTERS */
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<Brew> getTaplist() {
		return tap_list;
	}
}
