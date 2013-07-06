package com.ratworkshop.taplist.database;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.database.BrewContract.BrewEntry;
import com.ratworkshop.taplist.database.PubContract.PubEntry;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.models.Pub;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DEBUG_TAG = "DBHELPER";
	
	protected static final String TEXT_TYPE = " TEXT";
	protected static final String INT_TYPE = " INT";
	protected static final String FLOAT_TYPE = " FLOAT";
	protected static final String COMMA_SEP = ",";
	
	private static final String SQL_DELETE_PUB_ENTRIES = "DROP TABLE IF EXISTS " + PubEntry.TABLE_NAME;
	private static final String SQL_DELETE_BREW_ENTRIES = "DROP TABLE IF EXISTS " + BrewEntry.TABLE_NAME;
	
	private static final String SQL_CREATE_PUB_ENTRIES =
		    "CREATE TABLE " + PubEntry.TABLE_NAME + " (" +
		    		PubEntry._ID + " INTEGER PRIMARY KEY," +
		    		PubEntry.COLUMN_NAME_PUB_ID + INT_TYPE + COMMA_SEP +
		    		PubEntry.COLUMN_NAME_PUB_NAME + TEXT_TYPE + COMMA_SEP +
		    		PubEntry.COLUMN_NAME_PUB_LOGO + TEXT_TYPE + COMMA_SEP +
		    		// Address
		    		PubEntry.COLUMN_NAME_PUB_ADDRESS + TEXT_TYPE + COMMA_SEP +
		    		PubEntry.COLUMN_NAME_PUB_CITY  + TEXT_TYPE + COMMA_SEP +
        			PubEntry.COLUMN_NAME_PUB_STATE  + TEXT_TYPE + COMMA_SEP +
        			PubEntry.COLUMN_NAME_PUB_ZIP  + TEXT_TYPE + COMMA_SEP +
        			// Title 
        			PubEntry.COLUMN_NAME_PUB_TITLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TITLE_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TITLE_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TITLE_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TITLE_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TITLE_FONT_CUSTOM + INT_TYPE + COMMA_SEP +
		    		// SubTitle
    				PubEntry.COLUMN_NAME_PUB_SUBTITLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBTITLE_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBTITLE_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBTITLE_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT_CUSTOM + INT_TYPE + COMMA_SEP +
    				// SubHeader 
    				PubEntry.COLUMN_NAME_PUB_SUBHEADER_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBHEADER_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBHEADER_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT_CUSTOM + INT_TYPE + COMMA_SEP +
    				// Description
    				PubEntry.COLUMN_NAME_PUB_DESCRIPTION_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_DESCRIPTION_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_DESCRIPTION_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT_CUSTOM + INT_TYPE + COMMA_SEP +    				
    				// Pub list
    				PubEntry.COLUMN_NAME_PUB_LIST_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_LIST_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_LIST_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_LIST_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_LIST_FONT_CUSTOM + INT_TYPE + COMMA_SEP +    						
    				// Background Colors
    				PubEntry.COLUMN_NAME_PUB_HEADER_BACKGROUND_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_SUBHEADER_BACKGROUND_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_BACKGROUND_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_PUBLIST_BACKGROUND_COLOR + INT_TYPE + COMMA_SEP +
    				// Tap List Details
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT_CUSTOM + INT_TYPE + COMMA_SEP +    				
    				// Tap List Featured Details
    				PubEntry.COLUMN_NAME_PUB_FEATURED_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_FONT_CUSTOM + INT_TYPE + COMMA_SEP +    				
    				// Tap List Name 
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_DETAILS_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT_CUSTOM + INT_TYPE + COMMA_SEP +  
    				// Tap List Featured Name
    				PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_COLOR + INT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_SIZE + FLOAT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_STYLE + TEXT_TYPE + COMMA_SEP +
    				PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT_CUSTOM + INT_TYPE +    				
		    " )";
	
	private static final String SQL_CREATE_BREW_ENTRIES =
		    "CREATE TABLE " + BrewEntry.TABLE_NAME + " (" +
		    		BrewEntry._ID + " INTEGER PRIMARY KEY," +
		    		BrewEntry.COLUMN_NAME_BREW_ID + INT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_PUB_ID + INT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_NAME + TEXT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_ABV + FLOAT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_GLASS + FLOAT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_QUART + FLOAT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_GROWLER + FLOAT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_FEATURED + INT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_TYPE + TEXT_TYPE + COMMA_SEP +
		    		BrewEntry.COLUMN_NAME_BREW_CUSTOM_ICON + TEXT_TYPE +
		    " )";
	
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Publist.db";
	
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PUB_ENTRIES);
        db.execSQL(SQL_CREATE_BREW_ENTRIES);
        
        Log.d(DEBUG_TAG, "DATABASE TABLES CREATED!");
    }
	
	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_PUB_ENTRIES);
        db.execSQL(SQL_DELETE_BREW_ENTRIES);
        onCreate(db);
    }
	
	@Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
	/**
	 * Empties the Database
	 * @param context
	 */
	public static void deletePubList(Context context) {
    	DBHelper mDBHelper = new DBHelper(context);
    	
    	// Gets the data repositories in write mode
    	SQLiteDatabase DB = mDBHelper.getWritableDatabase();
    	
    	DB.delete(PubEntry.TABLE_NAME, null, null);
    	DB.delete(BrewEntry.TABLE_NAME, null, null);
    	
    	Log.d(DEBUG_TAG, "DATABASE TABLES EMPTIED!");
	}
	
	/**
	 * Empties the Database
	 * @param DB
	 */
	public static void deletePubList(SQLiteDatabase DB) {
    	DB.delete(PubEntry.TABLE_NAME, null, null);
    	DB.delete(BrewEntry.TABLE_NAME, null, null);
    	
    	Log.d(DEBUG_TAG, "DATABASE TABLES EMPTIED!");
	}
	
	/**
	 * Saves the Pub listings to the DB
	 * @param context
	 */
    public static void savePubList(Context context) { 
    	DBHelper mDBHelper = new DBHelper(context);
    	
    	// Gets the data repositories in write mode
    	SQLiteDatabase DB = mDBHelper.getWritableDatabase();
    	deletePubList(DB);
    	
    	for(Pub pub : PubContent.PUB_LIST) {
    		String pubId = pub.getId();
    		
    		ContentValues pubValues = new ContentValues();
    		// Pub Data 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_ID, Integer.parseInt(pubId));
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_NAME, pub.getName());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_LOGO, pub.getLogo());
    		// Address 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_ADDRESS, pub.getAddress());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_CITY, pub.getCity());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_STATE, pub.getState());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_ZIP, pub.getZip());
			// Title 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TITLE, pub.getTitle());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TITLE_COLOR, pub.getTitle_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TITLE_SIZE, pub.getTitle_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TITLE_FONT, pub.getTitle_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TITLE_STYLE, pub.getTitle_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TITLE_FONT_CUSTOM, pub.isTitle_font_custom());
			// SubTitle
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBTITLE, pub.getSubtitle());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBTITLE_COLOR, pub.getSubtitle_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBTITLE_SIZE, pub.getSubtitle_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT, pub.getSubtitle_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBTITLE_STYLE, pub.getSubtitle_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT_CUSTOM, pub.isSubtitle_font_custom());
			// SubHeader 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBHEADER_COLOR, pub.getSubheader_text_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBHEADER_SIZE, pub.getSubheader_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT, pub.getSubheader_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBHEADER_STYLE, pub.getSubheader_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT_CUSTOM, pub.isSubheader_font_custom());
			// Description 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_COLOR, pub.getDescription_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_SIZE, pub.getDescription_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT, pub.getDescription_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_STYLE, pub.getDescription_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT_CUSTOM, pub.isDescription_font_custom());
			// Publist 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_LIST_COLOR, pub.getPubList_font_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_LIST_SIZE, pub.getPublist_font_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_LIST_FONT, pub.getPublist_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_LIST_STYLE, pub.getPublist_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_LIST_FONT_CUSTOM, pub.isPublist_font_custom());
			// Background Colors 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_HEADER_BACKGROUND_COLOR, pub.getHeader_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_SUBHEADER_BACKGROUND_COLOR, pub.getSubheader_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_BACKGROUND_COLOR, pub.getTaplist_background_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_PUBLIST_BACKGROUND_COLOR, pub.getPublist_background_color());			
			// Tap List Details 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_COLOR, pub.getTaplist_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_SIZE, pub.getTaplist_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT, pub.getTaplist_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_STYLE, pub.getTaplist_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT_CUSTOM, pub.isTaplist_font_custom());
			// Tap List Featured Details 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_COLOR, pub.getFeatured_brew_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_SIZE, pub.getFeatured_brew_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_FONT, pub.getFeatured_brew_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_STYLE, pub.getFeatured_brew_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_FONT_CUSTOM, pub.isFeatured_brew_font_custom());
			// Tap List Name 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_COLOR, pub.getTaplist_name_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_SIZE, pub.getTaplist_name_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_DETAILS_SIZE, pub.getTaplist_name_details_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT, pub.getTaplist_name_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_STYLE, pub.getTaplist_name_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT_CUSTOM, pub.isTaplist_name_font_custom());
			// Tap List Featured Name 
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_COLOR, pub.getFeatured_brew_name_color());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_SIZE, pub.getFeatured_brew_name_size());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT, pub.getFeatured_brew_name_font());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_STYLE, pub.getFeatured_brew_name_style());
    		pubValues.put(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT_CUSTOM, pub.isFeatured_brew_name_font_custom());
    		
    		DB.insert(PubEntry.TABLE_NAME, null, pubValues);
    		
    		List<Brew> taplist = pub.getTaplist();
    		for(Brew brew : taplist) {
    			// Create a new map of values, where column names are the keys
    			ContentValues brewValues = new ContentValues();
    			brewValues.put(BrewEntry.COLUMN_NAME_PUB_ID, Integer.parseInt(pubId));
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_ID, Integer.parseInt(brew.getId()));
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_NAME, brew.getName());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_DESCRIPTION, brew.getDescription());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_ABV, brew.getAbv());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_GLASS, brew.getGlass());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_QUART, brew.getQuart());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_GROWLER, brew.getGrowler());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_FEATURED, brew.isFeatured()); 
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_CUSTOM_ICON, brew.getCustomIcon());
    			brewValues.put(BrewEntry.COLUMN_NAME_BREW_TYPE, brew.getBrewtype().name());
    			
    			DB.insert(BrewEntry.TABLE_NAME, null, brewValues);
    		}
    	}
    	DB.close();
    	Log.d(DEBUG_TAG, "DATA SAVE COMPLETE");
    }
    
    /**
     * Reads the Pub listings from the DB
     * @param context
     */
    public static void loadPubList(Context context) {
    	PubContent.clearPubList();
    	
    	DBHelper mDBHelper = new DBHelper(context);
    	
    	// Gets the data repositories in read mode
    	SQLiteDatabase DB = mDBHelper.getReadableDatabase();
    	
    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] pubProjection = {
    	    PubEntry.COLUMN_NAME_PUB_ID,
    	    PubEntry.COLUMN_NAME_PUB_NAME,
    	    PubEntry.COLUMN_NAME_PUB_LOGO,
            // Address
    	    PubEntry.COLUMN_NAME_PUB_ADDRESS,
    	    PubEntry.COLUMN_NAME_PUB_CITY,
    	    PubEntry.COLUMN_NAME_PUB_STATE,
    	    PubEntry.COLUMN_NAME_PUB_ZIP,        
        	// Title
    	    PubEntry.COLUMN_NAME_PUB_TITLE,
    	    PubEntry.COLUMN_NAME_PUB_TITLE_COLOR,
    	    PubEntry.COLUMN_NAME_PUB_TITLE_SIZE,
    	    PubEntry.COLUMN_NAME_PUB_TITLE_FONT,
    	    PubEntry.COLUMN_NAME_PUB_TITLE_STYLE,
    	    PubEntry.COLUMN_NAME_PUB_TITLE_FONT_CUSTOM,
        	// SubTitle
    	    PubEntry.COLUMN_NAME_PUB_SUBTITLE,
    	    PubEntry.COLUMN_NAME_PUB_SUBTITLE_COLOR,
    	    PubEntry.COLUMN_NAME_PUB_SUBTITLE_SIZE,
    	    PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT,
    	    PubEntry.COLUMN_NAME_PUB_SUBTITLE_STYLE,
    	    PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT_CUSTOM,
        	// SubHeader 
    	    PubEntry.COLUMN_NAME_PUB_SUBHEADER_COLOR,
    	    PubEntry.COLUMN_NAME_PUB_SUBHEADER_SIZE,
        	PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT,
        	PubEntry.COLUMN_NAME_PUB_SUBHEADER_STYLE,
        	PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT_CUSTOM,	
        	// Description 
        	PubEntry.COLUMN_NAME_PUB_DESCRIPTION_COLOR,
        	PubEntry.COLUMN_NAME_PUB_DESCRIPTION_SIZE,
        	PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT,
        	PubEntry.COLUMN_NAME_PUB_DESCRIPTION_STYLE,
        	PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT_CUSTOM,	
        	// Pub List 
        	PubEntry.COLUMN_NAME_PUB_LIST_COLOR,
        	PubEntry.COLUMN_NAME_PUB_LIST_SIZE,
        	PubEntry.COLUMN_NAME_PUB_LIST_FONT,
        	PubEntry.COLUMN_NAME_PUB_LIST_STYLE,
        	PubEntry.COLUMN_NAME_PUB_LIST_FONT_CUSTOM,
        	// Background Colors 
        	PubEntry.COLUMN_NAME_PUB_HEADER_BACKGROUND_COLOR,
        	PubEntry.COLUMN_NAME_PUB_SUBHEADER_BACKGROUND_COLOR,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_BACKGROUND_COLOR,
        	PubEntry.COLUMN_NAME_PUB_PUBLIST_BACKGROUND_COLOR,
        	// Tap List Details 
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_COLOR,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_SIZE,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_STYLE,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT_CUSTOM,
        	// Tap List Featured Details 
        	PubEntry.COLUMN_NAME_PUB_FEATURED_COLOR,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_SIZE,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_FONT,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_STYLE,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_FONT_CUSTOM,
        	// Tap List Name 
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_COLOR,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_SIZE,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_DETAILS_SIZE,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_STYLE,
        	PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT_CUSTOM,
        	// Tap List Featured Name 
        	PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_COLOR,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_SIZE,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_STYLE,
        	PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT_CUSTOM
    	};
    	
    	// How you want the results sorted in the resulting Cursor
    	String pubSortOrder = PubEntry.COLUMN_NAME_PUB_ID + " ASC";
    	
    	Cursor pubCursor = DB.query(
    		    PubEntry.TABLE_NAME,  					  // The table to query
    		    pubProjection,                            // The columns to return
    		    null,                                // The columns for the WHERE clause
    		    null,                            // The values for the WHERE clause
    		    null,                                     // don't group the rows
    		    null,                                     // don't filter by row groups
    		    pubSortOrder                              // The sort order
    	);
    	
    	pubCursor.moveToFirst();
    	while (pubCursor.isAfterLast() == false) {
    		int pubId = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_ID));
    		String pubName = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_NAME));
    		Pub pub = new Pub(String.valueOf(pubId), pubName);
    		  
    		pub.setLogo(pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_LOGO)));
    		
    		// Address
    		String address = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_ADDRESS));
    		String city = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_CITY));
    		String state = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_STATE));
    		String zip = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_ZIP));
    		pub.setPubAddress(address, city, state, zip);
    		
    		// Title
			String text = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TITLE));
			int color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TITLE_COLOR));
			String font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TITLE_FONT));
			String style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TITLE_STYLE));
			boolean isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TITLE_FONT_CUSTOM)) > 0 ? true : false;
			float size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TITLE_SIZE));
			pub.setPubTitle(text, color, font, style, isCustom, size);
			
			// SubTitle
			text = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBTITLE));
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBTITLE_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBTITLE_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBTITLE_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBTITLE_SIZE));
			pub.setPubSubtitle(text, color, font, style, isCustom, size);
			
			// SubHeader 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBHEADER_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBHEADER_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBHEADER_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBHEADER_SIZE));
			pub.setPubSubheader(color, font, style, isCustom, size);
			
			// Description 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_DESCRIPTION_SIZE));
			pub.setDescriptionStyles(color, font, style, isCustom, size);
			
			// Pub List 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_LIST_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_LIST_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_LIST_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_LIST_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_LIST_SIZE));
			pub.setPublistStyles(color, font, style, isCustom, size);
			
			// Background Colors 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_HEADER_BACKGROUND_COLOR));
			pub.setHeader_color(color);
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_SUBHEADER_BACKGROUND_COLOR));
			pub.setSubheader_color(color);
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_BACKGROUND_COLOR));
			pub.setTaplist_background_color(color);
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_PUBLIST_BACKGROUND_COLOR));
			pub.setPublist_background_color(color);
			
			// Tap List Details 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_SIZE));
			pub.setTaplistStyles(color, font, style, isCustom, size);
			
			// Tap List Featured Details 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_SIZE));
			pub.setFeaturedBrewStyles(color, font, style, isCustom, size);
			
			// Tap List Name 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_SIZE));
			float detail_size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_TAPLIST_NAME_DETAILS_SIZE));
			pub.setTaplistNameStyles(color, font, style, isCustom, size, detail_size);
				
			// Tap List Featured Name 
			color = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_COLOR));
			font = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT));
			style = pubCursor.getString(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_STYLE));
			isCustom = pubCursor.getInt(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_FONT_CUSTOM)) > 0 ? true : false;
			size = pubCursor.getFloat(pubCursor.getColumnIndexOrThrow(PubEntry.COLUMN_NAME_PUB_FEATURED_BREW_NAME_SIZE));
			pub.setFeaturedBrewNameStyles(color, font, style, isCustom, size);
    		
    		// Get all the Brews for the pub
    		String[] brewProjection = {
    			BrewEntry.COLUMN_NAME_BREW_ID,
    			BrewEntry.COLUMN_NAME_BREW_NAME,
    			BrewEntry.COLUMN_NAME_BREW_DESCRIPTION,
    			BrewEntry.COLUMN_NAME_BREW_ABV,
    			BrewEntry.COLUMN_NAME_BREW_GLASS,
    			BrewEntry.COLUMN_NAME_BREW_QUART,
    			BrewEntry.COLUMN_NAME_BREW_GROWLER,
    			BrewEntry.COLUMN_NAME_BREW_FEATURED, 
    			BrewEntry.COLUMN_NAME_BREW_CUSTOM_ICON,
    			BrewEntry.COLUMN_NAME_BREW_TYPE
    		};
    		
    		String brewSortOrder = BrewEntry.COLUMN_NAME_BREW_ID + " ASC";
    		String selection = BrewEntry.COLUMN_NAME_PUB_ID + " LIKE ?";
    		String[] selectionArgs = { String.valueOf(pubId) };
    		
    		Cursor brewCursor = DB.query(
    				BrewEntry.TABLE_NAME,
    				brewProjection,
    				selection,
    				selectionArgs,
    				null,
    				null,
    				brewSortOrder
    		);
    		
    		brewCursor.moveToFirst();
    		while (brewCursor.isAfterLast() == false) {
    			String brewId = String.valueOf(brewCursor.getInt(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_ID)));
    			String brewName = brewCursor.getString(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_NAME));
    			String brewDesc = brewCursor.getString(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_DESCRIPTION));
    			float brewABV = brewCursor.getFloat(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_ABV));
    			float brewGlass = brewCursor.getFloat(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_GLASS));
    			float brewQuart = brewCursor.getFloat(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_QUART));
    			float brewGrowler = brewCursor.getFloat(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_GROWLER));
    			boolean isFeatured = brewCursor.getInt(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_FEATURED)) > 0 ? true: false; 
    			String brewIcon = brewCursor.getString(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_CUSTOM_ICON));
    			String brewType = brewCursor.getString(brewCursor.getColumnIndexOrThrow(BrewEntry.COLUMN_NAME_BREW_TYPE));
    			
    			Brew brew = new Brew(brewId, brewName, brewDesc, brewABV, brewGlass, brewQuart, brewGrowler, isFeatured, brewIcon, brewType);
    			pub.addBrew(brew);
    			brewCursor.moveToNext();
    		}
    		
    		PubContent.addPub(pub);
    		pubCursor.moveToNext();
    	}
    	DB.close();
    	Log.d(DEBUG_TAG, "DATA LOAD COMPLETE");
    	
		// Set the Last Update so it doesn't keep reading from the DB
		String preferenceFile = context.getString(R.string.taplist_preference);
		SharedPreferences sharedPref = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong(context.getString(R.string.LAST_UPDATE), System.currentTimeMillis());
		editor.commit();
    }
}