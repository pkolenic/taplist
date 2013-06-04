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
import android.util.Log;

public class Pub {
	private static final String DEBUG_TAG = "Pub";
	
	private String id;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String logo;
	private String logo_path;
	private String title;
	private String subtitle;
	private String header_color;
	
	private List<Brew> tap_list;
	private Map<String, Brew> brew_map = new HashMap<String, Brew>();
	
	public Pub(String id) {
		this(id, "", "", "", "", "", "", "", "#000");
	}
	
	public Pub(String id, String address, String city, String state, String zip, String logo_path, String title, String subtitle, String color) {
		tap_list = new ArrayList<Brew>();
		this.id = id;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.setLogo(logo_path);
		this.title = title;
		this.subtitle = subtitle;
		this.header_color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
					
					this.logo = filename;	
				} else {
					Log.d(DEBUG_TAG, filename + " is not a valid image file, wrong file type");	
					this.logo = null;
				}
			} else {
		    	Log.d(DEBUG_TAG, filename + " is not a valid image file, too many parts");
		    	this.logo = null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
	    	Log.d(DEBUG_TAG, path + " is malformed");
	    	this.logo = null;
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
}