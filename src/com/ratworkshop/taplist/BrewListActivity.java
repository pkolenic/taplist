package com.ratworkshop.taplist;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.ratworkshop.taplist.contentprovider.PubContent;


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
public class BrewListActivity extends FragmentActivity implements BrewListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private String pubId;
    private OnNavigationListener mOnNavigationListener;
    private SpinnerAdapter mSpinnerAdapter;

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

        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, PubContent.PUB_LIST);
        mOnNavigationListener = new OnNavigationListener() {

        	  @Override
        	  public boolean onNavigationItemSelected(int position, long itemId) {
        		// Set the Pub Id on the BrewListFragment and Redraw the List  
        		pubId = PubContent.PUB_LIST.get(position);
                ((BrewListFragment) getSupportFragmentManager().findFragmentById(R.id.brew_list)).onPubSelected(PubContent.PUB_LIST.get(position));
        	    return true;
        	  }
        };
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);	
        	
        // TODO: If exposing deep links into your app, handle intents here.
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
            arguments.putString(BrewDetailFragment.ARG_BREW_ID, id);
            arguments.putString(BrewDetailFragment.ARG_PUB_ID, pubId);
            BrewDetailFragment fragment = new BrewDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.brew_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, BrewDetailActivity.class);
            detailIntent.putExtra(BrewDetailFragment.ARG_BREW_ID, id);
            detailIntent.putExtra(BrewDetailFragment.ARG_PUB_ID, pubId);
            startActivity(detailIntent);
        }
    }
}
