package com.ratworkshop.taplist;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ratworkshop.taplist.adapters.SelectionSpinnerAdapter;
import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.interfaces.PublistDelegate;
import com.ratworkshop.taplist.models.Pub;
import com.ratworkshop.taplist.tasks.FetchPublists;
import com.ratworkshop.taplist.utilities.Constants;


/**
 * An activity representing a list of Brews. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BrewDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link BrewListFragment} and the item details
 * (if present) is a {@link BrewDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link BrewListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class BrewListActivity extends FragmentActivity implements BrewListFragment.Callbacks, PublistDelegate {
	
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
	private boolean initial = true;
    private boolean mTwoPane;
    private String pubId;
    private OnNavigationListener mOnNavigationListener;
    private SelectionSpinnerAdapter mSpinnerAdapter;
	private static final String DEBUG_TAG = "BrewListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_list);

        if (findViewById(R.id.brew_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((BrewListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.brew_list))
                    .setActivateOnItemClick(true);
        }

        final ActionBar actionBar = getActionBar();        
        actionBar.setCustomView(R.layout.activity_header);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        mSpinnerAdapter = new SelectionSpinnerAdapter(this, R.layout.pub_selection_label, PubContent.PUB_LIST);
        mSpinnerAdapter.setSelectionStringId(R.string.pub_selection);
        mSpinnerAdapter.setDropDownViewResource(R.layout.pub_spinner_row);
        mOnNavigationListener = new OnNavigationListener() {

        	  @Override
        	  public boolean onNavigationItemSelected(int position, long itemId) {
        		pubId = getIntent().getStringExtra(getString(R.string.ARG_PUB_ID));
        		if (initial && pubId != null) {
        			initial = false;
        			Pub pub = PubContent.PUB_MAP.get(pubId);
        			int pos = PubContent.PUB_LIST.indexOf(pub);
        			actionBar.setSelectedNavigationItem(pos);
        			return mOnNavigationListener.onNavigationItemSelected(pos, pos);
        		} else {
        			// Set the Pub Id on the BrewListFragment and Redraw the List
        			pubId = PubContent.PUB_LIST.get(position).getId();
                	((BrewListFragment) getSupportFragmentManager().findFragmentById(R.id.brew_list)).onPubSelected(PubContent.PUB_LIST.get(position).getId());
            	    return true;
        		}
        	  }
        };
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);	
        
        // Check if Intent included a pubID
        pubId = getIntent().getStringExtra(getString(R.string.ARG_PUB_ID));
        if (pubId != null) {
        	try {
        	Pub pub = PubContent.PUB_MAP.get(pubId);
        	int pos = PubContent.PUB_LIST.indexOf(pub);
        	actionBar.setSelectedNavigationItem(pos);
			mOnNavigationListener.onNavigationItemSelected(pos, pos);
        	} catch (Exception e) {
        		Log.d(DEBUG_TAG, e.getLocalizedMessage());
			}
        }
    }
    
    /**
     * Callback method from {@link BrewListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(getString(R.string.ARG_BREW_ID), id);
            arguments.putString(getString(R.string.ARG_PUB_ID), pubId);
            BrewDetailFragment fragment = new BrewDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.brew_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, BrewDetailActivity.class);
            detailIntent.putExtra(getString(R.string.ARG_BREW_ID), id);
            detailIntent.putExtra(getString(R.string.ARG_PUB_ID), pubId);
            startActivity(detailIntent);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.options_menu, menu);
    	return true;
    }
    
    public void showSettings(MenuItem menuItem) {
    	Intent settingsIntent = new Intent(this, BrewSettingsActivity.class);
    	settingsIntent.putExtra(getString(R.string.ARG_PUB_ID), pubId);
    	startActivity(settingsIntent);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	
        // Check if its time to reload data
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.taplist_preference), Context.MODE_PRIVATE); 
		long now = System.currentTimeMillis();
		long then = sharedPref.getLong(getString(R.string.LAST_UPDATE), now);
		
		// If its been longer than an hour or now equals then (the app hasn't been used)
		if (now - then > Constants.HOUR) {
			Log.d(DEBUG_TAG, "Begining Request for updated Pub List");
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				// TODO Show Spinner View
				
				new FetchPublists(this).execute();
			}	
		}    	
    }
    
	@Override
	public void onPublistUpdated() {
		Log.d(DEBUG_TAG, "Finished Request for updated Pub List");
		// TODO Hide Spinner View
		
		// Rest List Adapter
		mSpinnerAdapter.clear();
		mSpinnerAdapter.addAll(PubContent.PUB_LIST);
		mSpinnerAdapter.notifyDataSetChanged();
		
        if (pubId != null) {
        	try {
        	Pub pub = PubContent.PUB_MAP.get(pubId);
        	int pos = PubContent.PUB_LIST.indexOf(pub);
        	getActionBar().setSelectedNavigationItem(pos);
			mOnNavigationListener.onNavigationItemSelected(pos, pos);
        	} catch (Exception e) {
        		Log.d(DEBUG_TAG, e.getLocalizedMessage());
			}
        }
	}
}
