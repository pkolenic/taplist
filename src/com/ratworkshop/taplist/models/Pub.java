package com.ratworkshop.taplist.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ratworkshop.taplist.service.ImageDownloader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;

public class Pub {
	private static final String DEBUG_TAG = "Pub";
	
	private String id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String logo;
	private String logo_path;
	
	/* Text Values */
	private String title;
	private String subtitle;
	
	/* Background Colors */
	private String header_color;
	private String subheader_color;
	
	/* Header Styles */
	private String title_color;
	private Typeface title_typeface;
	private float title_size;
	
	private String subtitle_color;
	private String subheader_text_color;
	private float subtitle_size;

	private Typeface subtitle_typeface;
	private Typeface subheader_typeface;
	private float subheader_size;
	
	/* Tap List Styles */
	private String featured_brew_color;
	private Typeface featured_brew_typeface;
	
	private String featured_brew_name_color;
	private Typeface featured_brew_name_typeface;
	
	private String taplist_color;
	private Typeface taplist_typeface;
	
	private String taplist_name_color;
	private Typeface taplist_name_typeface;
	
	private String taplist_background_color;
	
	/* Brews */
	private List<Brew> tap_list;
	private Map<String, Brew> brew_map = new HashMap<String, Brew>();
	
	public Pub(String name) {
		this("", name, "", "", "", "", "", "", "", 
			 "#000000", "#ffe8db", "#ffe8db", "#000000", "e9dcc8",
			 Typeface.MONOSPACE, 16.0f, Typeface.MONOSPACE, 36.0f, Typeface.DEFAULT, 12.0f,
			 "#607d32", Typeface.DEFAULT_BOLD, "#607d32", Typeface.DEFAULT_BOLD,
			 "#e9dcc8", Typeface.DEFAULT, "#e9dcc8", Typeface.DEFAULT_BOLD,
			 "#000000"
			 );
	}
	
	public Pub(String id, String name, String address, String city,
			String state, String zip, String logo_path, String title,
			String subtitle, 
			String header_color, String title_color, String subtitle_color,
			String subheader_color, String subheader_text_color,
			Typeface title_typeface, float title_size, Typeface subtitle_typeface, float subtitle_size, Typeface subheader_typeface, float subheader_size,
			String featured_brew_color, Typeface featured_brew_typeface, String featured_brew_name_color, Typeface featured_brew_name_typeface,
			String taplist_color, Typeface taplist_typeface, String taplist_name_color, Typeface taplist_name_typeface,
			String taplist_background_color) {
		
		tap_list = new ArrayList<Brew>();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.setLogo(logo_path);
		this.title = title;
		this.subtitle = subtitle;
		this.header_color = header_color;
		this.title_color = title_color;
		this.subtitle_color = subtitle_color;
		this.subheader_color = subheader_color;
		this.subheader_text_color = subheader_text_color;
		this.title_typeface = title_typeface;
		this.title_size = title_size;
		this.subtitle_typeface = subtitle_typeface;
		this.subtitle_size = subtitle_size;
		this.subheader_typeface = subheader_typeface;
		this.subheader_size = subheader_size;
		this.featured_brew_color = featured_brew_color;
		this.featured_brew_typeface = featured_brew_typeface;
		this.featured_brew_name_color = featured_brew_name_color;
		this.featured_brew_name_typeface = featured_brew_name_typeface;
		this.taplist_color = taplist_color;
		this.taplist_typeface = taplist_typeface;
		this.taplist_name_color = taplist_name_color;
		this.taplist_name_typeface = taplist_name_typeface;
		this.taplist_background_color = taplist_background_color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String path) {
		this.logo_path = path;
		
		if (path.equals("")) {
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

	public String getLogoPath() {
		return logo_path;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getHeader_color() {
		return header_color;
	}

	public void setHeader_color(String header_color) {
		this.header_color = header_color;
	}
	
	public String getTitle_color() {
		return title_color;
	}

	public void setTitle_color(String title_color) {
		this.title_color = title_color;
	}

	public String getSubtitle_color() {
		return subtitle_color;
	}

	public void setSubtitle_color(String subtitle_color) {
		this.subtitle_color = subtitle_color;
	}
	
	public String getSubheader_color() {
		return subheader_color;
	}

	public void setSubheader_color(String subheader_color) {
		this.subheader_color = subheader_color;
	}

	public String getSubheader_text_color() {
		return subheader_text_color;
	}

	public void setSubheader_text_color(String subheader_text_color) {
		this.subheader_text_color = subheader_text_color;
	}

	public List<Brew> getTaplist() {
		return tap_list;
	}
	
	public void addBrew(Brew brew) {
		tap_list.add(brew);
        brew_map.put(brew.getId(), brew);
	}
	
	public Brew getBrew(String brewId) {
		return brew_map.get(brewId);
	}
	
	public void downloadLogo(Context context) {
    	if (logo != null) {
			// Download the file
			Intent intent = new Intent(context, ImageDownloader.class);
			intent.putExtra(ImageDownloader.IMAGE_NAME, logo);
			intent.putExtra(ImageDownloader.IMAGE_URL, logo_path);
			context.startService(intent);	
    	}
	}

	public Typeface getTitle_typeface() {
		return title_typeface;
	}

	public void setTitle_typeface(Typeface title_typeface) {
		this.title_typeface = title_typeface;
	}

	public Typeface getSubtitle_typeface() {
		return subtitle_typeface;
	}

	public void setSubtitle_typeface(Typeface subtitle_typeface) {
		this.subtitle_typeface = subtitle_typeface;
	}

	public Typeface getSubheader_typeface() {
		return subheader_typeface;
	}

	public void setSubheader_typeface(Typeface subheader_typeface) {
		this.subheader_typeface = subheader_typeface;
	}

	public String getFeatured_brew_color() {
		return featured_brew_color;
	}

	public void setFeatured_brew_color(String featured_brew_color) {
		this.featured_brew_color = featured_brew_color;
	}

	public Typeface getFeatured_brew_typeface() {
		return featured_brew_typeface;
	}

	public void setFeatured_brew_typeface(Typeface featured_brew_typeface) {
		this.featured_brew_typeface = featured_brew_typeface;
	}

	public String getFeatured_brew_name_color() {
		return featured_brew_name_color;
	}

	public void setFeatured_brew_name_color(String featured_brew_name_color) {
		this.featured_brew_name_color = featured_brew_name_color;
	}

	public Typeface getFeatured_brew_name_typeface() {
		return featured_brew_name_typeface;
	}

	public void setFeatured_brew_name_typeface(Typeface featured_brew_name_typeface) {
		this.featured_brew_name_typeface = featured_brew_name_typeface;
	}

	public String getTaplist_color() {
		return taplist_color;
	}

	public void setTaplist_color(String taplist_color) {
		this.taplist_color = taplist_color;
	}

	public Typeface getTaplist_typeface() {
		return taplist_typeface;
	}

	public void setTaplist_typeface(Typeface taplist_typeface) {
		this.taplist_typeface = taplist_typeface;
	}

	public String getTaplist_name_color() {
		return taplist_name_color;
	}

	public void setTaplist_name_color(String taplist_name_color) {
		this.taplist_name_color = taplist_name_color;
	}

	public Typeface getTaplist_name_typeface() {
		return taplist_name_typeface;
	}

	public void setTaplist_name_typeface(Typeface taplist_name_typeface) {
		this.taplist_name_typeface = taplist_name_typeface;
	}

	public String getTaplist_background_color() {
		return taplist_background_color;
	}

	public void setTaplist_background_color(String taplist_background_color) {
		this.taplist_background_color = taplist_background_color;
	}

	public float getTitle_size() {
		return title_size;
	}

	public void setTitle_size(float title_size) {
		this.title_size = title_size;
	}

	public float getSubtitle_size() {
		return subtitle_size;
	}

	public void setSubtitle_size(float subtitle_size) {
		this.subtitle_size = subtitle_size;
	}

	public float getSubheader_size() {
		return subheader_size;
	}

	public void setSubheader_size(float subheader_size) {
		this.subheader_size = subheader_size;
	}
}
