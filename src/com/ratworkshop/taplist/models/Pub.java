package com.ratworkshop.taplist.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import com.ratworkshop.taplist.service.ImageDownloader;
import com.ratworkshop.taplist.utilities.TaplistTypeface;

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
	private int title_color;
	private float title_size;
	private Typeface title_typeface;
	private String title_font;
	private String title_style;
	private boolean title_font_custom;
	
	/* SubTitle */
	private String subtitle;
	private int subtitle_color;
	private float subtitle_size;
	private Typeface subtitle_typeface;
	private String subtitle_font;
	private String subtitle_style;
	private boolean subtitle_font_custom;
	
	/* SubHeader */
	private int subheader_text_color;
	private float subheader_size;
	private Typeface subheader_typeface;
	private String subheader_font;
	private String subheader_style;
	private boolean subheader_font_custom;	
	
	/* Description */
	private int description_text_color;
	private float description_size;
	private Typeface description_typeface;
	private String description_font;
	private String description_style;
	private boolean description_font_custom;	
	
	/* Publist */
	private int publist_text_color;
	private float publist_font_size;
	private Typeface publist_typeface;
	private String publist_font;
	private String publist_style;
	private boolean publist_font_custom;
	
	/* Background Colors */
	private int header_color;
	private int subheader_color;
	private int taplist_background_color;
	private int publist_background_color;
	
	/* Tap List Details */
	private int taplist_color;
	private float taplist_size;
	private Typeface taplist_typeface;
	private String taplist_font;
	private String taplist_style;
	private boolean taplist_font_custom;
	
	/* Tap List Featured Details */
	private int featured_brew_color;
	private float featured_brew_size;
	private Typeface featured_brew_typeface;
	private String featured_brew_font;
	private String featured_brew_style;
	private boolean featured_brew_font_custom;
	
	/* Tap List Name */
	private int taplist_name_color;
	private float taplist_name_size;
	private float taplist_name_details_size;
	private Typeface taplist_name_typeface;
	private String taplist_name_font;
	private String taplist_name_style;
	private boolean taplist_name_font_custom;
		
	/* Tap List Featured Name */
	private int featured_brew_name_color;
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
		setPubTitle("", "#ffe8db", "MONOSPACE", "BOLD", false, 16.0f);
		setPubSubtitle("", "#ffe8db", "MONOSPACE", "NORMAL", false, 36.0f);
		setPubSubheader("#e9dcc8", "DEFAULT", "NORMAL", false, 12.0f);
		setDescriptionStyles("#e9dcc8", "DEFAULT", "BOLD", false, 16.0f);
		setTaplistStyles("#e9dcc8", "DEFAULT", "NORMAL", false, 12.0f);
		setFeaturedBrewStyles("#607d32", "DEFAULT", "BOLD", false, 12.0f);
		setTaplistNameStyles("#e9dcc8", "DEFAULT", "BOLD", false, 12.0f, 18.0f);
		setFeaturedBrewNameStyles("#607d32", "DEFAULT", "BOLD", false, 12.0f);
		setHeader_color("#000000");
		setSubheader_color("#000000");
		setTaplist_background_color("#000000");
		setPublist_background_color("#000000");
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
	 * @param text
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPubTitle(String text, String color, String font, String style, boolean isCustom, float size) {
		setPubTitle(text, Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the Title Attributes of the Pub
	 * @param text
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPubTitle(String text, int color, String font, String style, boolean isCustom, float size) {
		this.title = text;
		this.title_color = color;
		this.title_font = font;
		this.title_style = style;
		this.title_font_custom = isCustom;
		this.title_size = size;
	}
	
	/**
	 * Sets the Subtitle Attributes of the Pub
	 * @param text
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPubSubtitle(String text, String color, String font, String style, boolean isCustom, float size) {
		setPubSubtitle(text, Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the Subtitle Attributes of the Pub
	 * @param text
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPubSubtitle(String text, int color, String font, String style, boolean isCustom, float size) {
		this.subtitle = text;
		this.subtitle_color = color;
		this.subtitle_font = font;
		this.subtitle_style = style;
		this.subtitle_font_custom = isCustom;
		this.subtitle_size = size;
	}
	
	/**
	 * Sets the SubHeader Attributes of the Pub
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPubSubheader(String color, String font, String style, boolean isCustom, float size) {
		setPubSubheader(Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the SubHeader Attributes of the Pub
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPubSubheader(int color, String font, String style, boolean isCustom, float size) {
		this.subheader_text_color = color;
		this.subheader_font = font;
		this.subheader_style = style;
		this.subheader_font_custom = isCustom;
		this.subheader_size = size;
	}
	
	/**
	 * Sets the Description Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setDescriptionStyles(String color, String font, String style, boolean isCustom, float size) {
		setDescriptionStyles(Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the Description Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setDescriptionStyles(int color, String font, String style, boolean isCustom, float size) {
		this.description_text_color = color;
		this.description_font = font;
		this.description_style = style;
		this.description_font_custom = isCustom;
		this.description_size = size;
	}
	
	/**
	 * Sets the Tap List Text Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setTaplistStyles(String color, String font, String style, boolean isCustom, float size) {
		setTaplistStyles(Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the Tap List Text Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setTaplistStyles(int color, String font, String style, boolean isCustom, float size) {
		this.taplist_color = color;
		this.taplist_font = font;
		this.taplist_style = style;
		this.taplist_font_custom = isCustom;
		this.taplist_size = size;
	}
	
	/**
	 * Sets the Featured Brew Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setFeaturedBrewStyles(String color, String font, String style, boolean isCustom, float size) {
		setFeaturedBrewStyles(Color.parseColor(color), font, style, isCustom, size);
	}

	/**
	 * Sets the Featured Brew Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setFeaturedBrewStyles(int color, String font, String style, boolean isCustom, float size) {
		this.featured_brew_color = color;
		this.featured_brew_font = font;
		this.featured_brew_style = style;
		this.featured_brew_font_custom = isCustom;
		this.featured_brew_size = size;
	}
	
	/**
	 * Sets the Tap list Name Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 * @param detail_size
	 */
	public void setTaplistNameStyles(String color, String font, String style, boolean isCustom, float size, float detail_size) {
		setTaplistNameStyles(Color.parseColor(color), font, style, isCustom, size, detail_size);
	}

	/**
	 * Sets the Tap list Name Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 * @param detail_size
	 */
	public void setTaplistNameStyles(int color, String font, String style, boolean isCustom, float size, float detail_size) {
		this.taplist_name_color = color;
		this.taplist_name_font = font;
		this.taplist_name_style = style;
		this.taplist_name_font_custom = isCustom;;
		this.taplist_name_size = size;
		this.taplist_name_details_size = detail_size;
	}
	
	/**
	 * Sets the Featured Brew Name Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setFeaturedBrewNameStyles(String color, String font, String style, boolean isCustom, float size) {
		setFeaturedBrewNameStyles(Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the Featured Brew Name Styles
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setFeaturedBrewNameStyles(int color, String font, String style, boolean isCustom, float size) {
		this.featured_brew_name_color = color;
		this.featured_brew_name_font = font;
		this.featured_brew_name_style = style;
		this.featured_brew_name_font_custom = isCustom;
		this.featured_brew_name_size = size;
	}
	
	/**
	 * Sets the Styles for the Pub Name in the drop down list
	 * @param color
	 * @param font
	 * @param style
	 * @param custom
	 * @param size
	 */
	public void setPublistStyles(String color, String font, String style, boolean isCustom, float size) {
		setPublistStyles(Color.parseColor(color), font, style, isCustom, size);
	}
	
	/**
	 * Sets the Styles for the Pub name in the drop down list
	 * @param color
	 * @param font
	 * @param style
	 * @param isCustom
	 * @param size
	 */
	public void setPublistStyles(int color, String font, String style, boolean isCustom, float size) {
		publist_text_color = color;
		publist_font = font;
		publist_style = style;
		publist_font_custom = isCustom;
		publist_font_size = size;
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
	
	public int getTitle_color() {
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
	
	public String getTitle_font() {
		return title_font;
	}

	public String getTitle_style() {
		return title_style;
	}

	public boolean isTitle_font_custom() {
		return title_font_custom;
	}

	/* SUBTITLE GETTERS */
	public String getSubtitle() {
		return subtitle;
	}
	
	public int getSubtitle_color() {
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
	
	public String getSubtitle_font() {
		return subtitle_font;
	}

	public String getSubtitle_style() {
		return subtitle_style;
	}

	public boolean isSubtitle_font_custom() {
		return subtitle_font_custom;
	}

	/* SUBHEADER GETTERS */
	public int getSubheader_text_color() {
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
	
	public String getSubheader_font() {
		return subheader_font;
	}

	public String getSubheader_style() {
		return subheader_style;
	}

	public boolean isSubheader_font_custom() {
		return subheader_font_custom;
	}

	/* DESCRIPTION GETTERS */
	public int getDescription_color() {
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
	
	public String getDescription_font() {
		return description_font;
	}

	public String getDescription_style() {
		return description_style;
	}

	public boolean isDescription_font_custom() {
		return description_font_custom;
	}

	/* TAPLIST GETTERS */
	public int getTaplist_color() {
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
	
	public String getTaplist_font() {
		return taplist_font;
	}

	public String getTaplist_style() {
		return taplist_style;
	}

	public boolean isTaplist_font_custom() {
		return taplist_font_custom;
	}

	/* FEATURED GETTERS */
	public int getFeatured_brew_color() {
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
	
	public String getFeatured_brew_font() {
		return featured_brew_font;
	}

	public String getFeatured_brew_style() {
		return featured_brew_style;
	}

	public boolean isFeatured_brew_font_custom() {
		return featured_brew_font_custom;
	}

	/* TAPLIST NAME GETTERS */
	public int getTaplist_name_color() {
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
	
	public String getTaplist_name_font() {
		return taplist_name_font;
	}

	public String getTaplist_name_style() {
		return taplist_name_style;
	}

	public boolean isTaplist_name_font_custom() {
		return taplist_name_font_custom;
	}

	/* TAPLIST FEATURED NAME GETTERS */
	public int getFeatured_brew_name_color() {
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
	
	public String getFeatured_brew_name_font() {
		return featured_brew_name_font;
	}

	public String getFeatured_brew_name_style() {
		return featured_brew_name_style;
	}

	public boolean isFeatured_brew_name_font_custom() {
		return featured_brew_name_font_custom;
	}

	/* PUBLIST GETTERS */
	public int getPubList_font_color() {
		return publist_text_color;
	}
	
	public Typeface getPublist_typeface(Context context) {
		if (publist_typeface == null) {
			publist_typeface = TaplistTypeface.create(publist_font, publist_style, publist_font_custom, context);
		}
		return publist_typeface;
	}
	
	public float getPublist_font_size() {
		return publist_font_size;
	}
	
	public String getPublist_font() {
		return publist_font;
	}

	public String getPublist_style() {
		return publist_style;
	}

	public boolean isPublist_font_custom() {
		return publist_font_custom;
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

	public void setLogo(String logo) {
		this.logo = logo;
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

	public void setHeader_color(String color) {
		this.header_color = Color.parseColor(color);
	}	
	
	public void setHeader_color(int color) {
		this.header_color = color;
	}
	
	public int getSubheader_color() {
		return subheader_color;
	}
	
	public void setSubheader_color(String color) {
		this.subheader_color = Color.parseColor(color);
	}
	
	public void setSubheader_color(int color) {
		this.subheader_color = color;
	}
	
	public int getTaplist_background_color() {
		return taplist_background_color;
	}

	public void setTaplist_background_color(String color) {
		this.taplist_background_color = Color.parseColor(color);
	}
	
	public void setTaplist_background_color(int color) {
		this.taplist_background_color = color;
	}
	
	public int getPublist_background_color() {
		return publist_background_color;
	}
	
	public void setPublist_background_color(String color) {
		this.publist_background_color = Color.parseColor(color);
	}
	
	public void setPublist_background_color(int color) {
		this.publist_background_color = color;
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
