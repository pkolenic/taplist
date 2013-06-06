package com.ratworkshop.taplist.utilities;

import android.content.Context;
import android.graphics.Typeface;

public class TaplistTypeface {

	public enum Taplist_Typeface {
		DEFAULT, DEFAULT_BOLD, MONOSPACE, SAN_SERIF, SERIF
	}
	
	public enum Taplist_Style {
		BOLD, BOLD_ITALIC, ITALIC, NORMAL
	}
	
	public static Typeface create(String font, String style, boolean isCustomFont, Context context) {
		Typeface typeface;
    	if (isCustomFont) {
    		typeface = Typeface.createFromFile(String.format("%s/fonts/%s", context.getCacheDir(), font));
    	} else {
    		Taplist_Typeface ttf;
    		try {
    			ttf = Taplist_Typeface.valueOf(font);
    		} catch (IllegalArgumentException e) {
    			ttf = Taplist_Typeface.DEFAULT;
    		}
    		
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
	

}
