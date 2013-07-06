package com.ratworkshop.taplist.database;

import android.provider.BaseColumns;

public final class PubContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
	public PubContract() {}
	
	public static abstract class PubEntry implements BaseColumns {
        public static final String TABLE_NAME = "pub";
        
        public static final String COLUMN_NAME_PUB_ID = "pubId";
        public static final String COLUMN_NAME_PUB_NAME = "name";
        public static final String COLUMN_NAME_PUB_LOGO = "logo";
        // Address
        public static final String COLUMN_NAME_PUB_ADDRESS = "address";
        public static final String COLUMN_NAME_PUB_CITY = "city";
        public static final String COLUMN_NAME_PUB_STATE = "state";
        public static final String COLUMN_NAME_PUB_ZIP = "zip";        
    	// Title
    	public static final String COLUMN_NAME_PUB_TITLE = "title";
    	public static final String COLUMN_NAME_PUB_TITLE_COLOR = "title_color";
    	public static final String COLUMN_NAME_PUB_TITLE_SIZE = "title_size";
    	public static final String COLUMN_NAME_PUB_TITLE_FONT = "title_font";
    	public static final String COLUMN_NAME_PUB_TITLE_STYLE = "title_style";
    	public static final String COLUMN_NAME_PUB_TITLE_FONT_CUSTOM = "title_font_custom";
    	// SubTitle
    	public static final String COLUMN_NAME_PUB_SUBTITLE = "subtitle";
    	public static final String COLUMN_NAME_PUB_SUBTITLE_COLOR = "subtitle_color";
    	public static final String COLUMN_NAME_PUB_SUBTITLE_SIZE = "subtitle_size";
    	public static final String COLUMN_NAME_PUB_SUBTITLE_FONT = "subtitle_font";
    	public static final String COLUMN_NAME_PUB_SUBTITLE_STYLE = "subtitle_style";
    	public static final String COLUMN_NAME_PUB_SUBTITLE_FONT_CUSTOM = "subtitle_font_custom";
    	// SubHeader 
    	public static final String COLUMN_NAME_PUB_SUBHEADER_COLOR = "subheader_text_color";
    	public static final String COLUMN_NAME_PUB_SUBHEADER_SIZE = "subheader_size";
    	public static final String COLUMN_NAME_PUB_SUBHEADER_FONT = "subheader_font";
    	public static final String COLUMN_NAME_PUB_SUBHEADER_STYLE = "subheader_style";
    	public static final String COLUMN_NAME_PUB_SUBHEADER_FONT_CUSTOM = "subheader_font_custom";	
    	// Description 
    	public static final String COLUMN_NAME_PUB_DESCRIPTION_COLOR = "description_text_color";
    	public static final String COLUMN_NAME_PUB_DESCRIPTION_SIZE = "description_size";
    	public static final String COLUMN_NAME_PUB_DESCRIPTION_FONT = "description_font";
    	public static final String COLUMN_NAME_PUB_DESCRIPTION_STYLE = "description_style";
    	public static final String COLUMN_NAME_PUB_DESCRIPTION_FONT_CUSTOM = "description_font_custom";	
    	// Pub List 
    	public static final String COLUMN_NAME_PUB_LIST_COLOR = "publist_text_color";
    	public static final String COLUMN_NAME_PUB_LIST_SIZE = "publist_font_size";
    	public static final String COLUMN_NAME_PUB_LIST_FONT = "publist_font";
    	public static final String COLUMN_NAME_PUB_LIST_STYLE = "publist_style";
    	public static final String COLUMN_NAME_PUB_LIST_FONT_CUSTOM = "publist_font_custom";
    	// Background Colors 
    	public static final String COLUMN_NAME_PUB_HEADER_BACKGROUND_COLOR = "header_color";
    	public static final String COLUMN_NAME_PUB_SUBHEADER_BACKGROUND_COLOR = "subheader_color";
    	public static final String COLUMN_NAME_PUB_TAPLIST_BACKGROUND_COLOR = "taplist_background_color";
    	public static final String COLUMN_NAME_PUB_PUBLIST_BACKGROUND_COLOR = "publist_background_color";
    	// Tap List Details 
    	public static final String COLUMN_NAME_PUB_TAPLIST_COLOR = "taplist_color";
    	public static final String COLUMN_NAME_PUB_TAPLIST_SIZE = "taplist_size";
    	public static final String COLUMN_NAME_PUB_TAPLIST_FONT = "taplist_font";
    	public static final String COLUMN_NAME_PUB_TAPLIST_STYLE = "taplist_style";
    	public static final String COLUMN_NAME_PUB_TAPLIST_FONT_CUSTOM = "taplist_font_custom";
    	// Tap List Featured Details 
    	public static final String COLUMN_NAME_PUB_FEATURED_COLOR = "featured_brew_color";
    	public static final String COLUMN_NAME_PUB_FEATURED_SIZE = "featured_brew_size";
    	public static final String COLUMN_NAME_PUB_FEATURED_FONT = "featured_brew_font";
    	public static final String COLUMN_NAME_PUB_FEATURED_STYLE = "featured_brew_style";
    	public static final String COLUMN_NAME_PUB_FEATURED_FONT_CUSTOM = "featured_brew_font_custom";
    	// Tap List Name 
    	public static final String COLUMN_NAME_PUB_TAPLIST_NAME_COLOR = "taplist_name_color";
    	public static final String COLUMN_NAME_PUB_TAPLIST_NAME_SIZE = "taplist_name_size";
    	public static final String COLUMN_NAME_PUB_TAPLIST_NAME_DETAILS_SIZE = "taplist_name_details_size";
    	public static final String COLUMN_NAME_PUB_TAPLIST_NAME_FONT = "taplist_name_font";
    	public static final String COLUMN_NAME_PUB_TAPLIST_NAME_STYLE = "taplist_name_style";
    	public static final String COLUMN_NAME_PUB_TAPLIST_NAME_FONT_CUSTOM = "taplist_name_font_custom";
    	// Tap List Featured Name 
    	public static final String COLUMN_NAME_PUB_FEATURED_BREW_NAME_COLOR = "featured_brew_name_color";
    	public static final String COLUMN_NAME_PUB_FEATURED_BREW_NAME_SIZE = "featured_brew_name_size";
    	public static final String COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT = "featured_brew_name_font";
    	public static final String COLUMN_NAME_PUB_FEATURED_BREW_NAME_STYLE = "featured_brew_name_style";
    	public static final String COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT_CUSTOM = "featured_brew_name_font_custom";
	}
	
}
