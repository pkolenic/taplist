package com.ratworkshop.taplist.database;

import android.provider.BaseColumns;

public final class BrewContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
	public BrewContract() {}
	
	public static abstract class BrewEntry implements BaseColumns {
        public static final String TABLE_NAME = "brew";
        public static final String COLUMN_NAME_BREW_ID = "brewId";
        public static final String COLUMN_NAME_PUB_ID = "pubId";
        public static final String COLUMN_NAME_BREW_NAME = "name";
        public static final String COLUMN_NAME_BREW_DESCRIPTION = "description";
        public static final String COLUMN_NAME_BREW_ABV = "abv";
        public static final String COLUMN_NAME_BREW_GLASS = "glass";
        public static final String COLUMN_NAME_BREW_QUART = "quart";
        public static final String COLUMN_NAME_BREW_GROWLER = "growler";
        public static final String COLUMN_NAME_BREW_FEATURED = "isFeatured";
        public static final String COLUMN_NAME_BREW_TYPE = "brewType";
        public static final String COLUMN_NAME_BREW_ICON = "icon";
        public static final String COLUMN_NAME_BREW_CUSTOM_ICON = "customIcon";
	}
	
}