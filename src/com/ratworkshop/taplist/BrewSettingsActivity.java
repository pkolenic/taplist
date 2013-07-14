package com.ratworkshop.taplist;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.urbanairship.UAirship;
import com.urbanairship.preference.UAPreferenceAdapter;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class BrewSettingsActivity extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PushPreferenceFragment()).commit();
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
            	Intent intent = new Intent(this, BrewListActivity.class);
            	intent.putExtra(getString(R.string.ARG_PUB_ID), getIntent().getStringExtra(getString(R.string.ARG_PUB_ID)));
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    @Override
    protected void onStart() {
        super.onStart();

        // Activity instrumentation for analytic tracking
        UAirship.shared().getAnalytics().activityStarted(this);
    }

    @Override
	public void onStop() {
        super.onStop();
        // Activity instrumentation for analytic tracking
        UAirship.shared().getAnalytics().activityStopped(this);
    }
    
    /**
     * This fragment shows the preferences for the first header.
     */
    public static class PushPreferenceFragment extends PreferenceFragment {
        private UAPreferenceAdapter preferenceAdapter;
    	
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.push_preferences);
            
            // Creates the UAPreferenceAdapter with the entire preference screen
            preferenceAdapter = new UAPreferenceAdapter(getPreferenceScreen());
        }
        
        @Override
		public void onStop() {
            super.onStop();
            // Apply any changed UA preferences from the preference screen
            preferenceAdapter.applyUrbanAirshipPreferences();
            // - Left in to show where Tags will need to be set (refer to UrbanAirship Sample App on how to create tags)
//            if (PushManager.shared().getPreferences().isPushEnabled()) {
//            	PushManager.shared().setAlias("FRED");
//            }
        }
    }
}
