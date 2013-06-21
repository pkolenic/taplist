package com.ratworkshop.taplist.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class TaplistTypeface {
    private static final String DEBUG_TAG = "TypelistTypeface";
    
	public enum Taplist_Typeface {
		DEFAULT, DEFAULT_BOLD, MONOSPACE, SAN_SERIF, SERIF
	}
	
	public enum Taplist_Style {
		BOLD, BOLD_ITALIC, ITALIC, NORMAL
	}
	
	private static Typeface create(String font, String style) {
		Taplist_Typeface ttf;
		try {
			ttf = Taplist_Typeface.valueOf(font);
		} catch (IllegalArgumentException e) {
			ttf = Taplist_Typeface.DEFAULT;
		}
		
		Typeface typeface;
		switch(ttf) {
		default:
		case DEFAULT :
			typeface = Typeface.DEFAULT;
			break;
		case DEFAULT_BOLD:
			typeface = Typeface.DEFAULT_BOLD;
			break;
		case MONOSPACE:
			typeface = Typeface.MONOSPACE;
			break;
		case SAN_SERIF:
			typeface = Typeface.SANS_SERIF;
			break;
		case SERIF:
			typeface = Typeface.SERIF;
			break;
		}
		
    	int s = Typeface.NORMAL;
    	Taplist_Style ts;
    	try{
    		ts = Taplist_Style.valueOf(style); 
    	}  catch (IllegalArgumentException e) {
			ts = Taplist_Style.NORMAL;
		}
    	
    	switch(ts) {
    		default:
    		case NORMAL:
    			s = Typeface.NORMAL;
    			break;
    		case BOLD:
    			s = Typeface.BOLD;
    			break;
    		case BOLD_ITALIC:
    			s = Typeface.BOLD_ITALIC;
    			break;
    		case ITALIC:
    			s = Typeface.ITALIC;
    			break;
    	}
		
    	return Typeface.create(typeface, s);		
	}
	
	public static Typeface create(String font, String style, boolean isCustomFont, Context context) {
    	if (isCustomFont) {
    		Log.d(DEBUG_TAG, String.format("Trying to get font for: %s", font));
    		Typeface typeface;
    		try {
    			typeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", font));	
    			Log.d(DEBUG_TAG, "Font successfully found in Assets");
    		} catch(Exception e) {
    			Log.d(DEBUG_TAG, "Font not found in Assets, trying Cache");
    			try {
    				typeface = Typeface.createFromFile(String.format("%s/fonts/%s", context.getCacheDir(), font));
    				Log.d(DEBUG_TAG, "Font successfully found in Cache");
    			} catch (Exception e2) {
    				Log.d(DEBUG_TAG, "Font not found not found in Cache");
    				typeface = Typeface.DEFAULT;
    			}
    		}
    		return typeface;
    	} else {
        	return create(font, style);
    	}
	}
	

}
