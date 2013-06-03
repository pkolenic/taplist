package com.ratworkshop.taplist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.adapters.TaplistAdapter;
import com.ratworkshop.taplist.contentprovider.PubContent;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.utilities.FlushedInputStream;

/**
 * A list fragment representing a list of Brews. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link BrewDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class BrewListFragment extends ListFragment {
	
    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    private static final String DEBUG_TAG = "BrewListFragment";
    private TaplistAdapter mAdapter;
    private String pubId;
	private ImageView logo;
	    
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BrewListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pubId = PubContent.PUB_LIST.get(0);
        mAdapter = new TaplistAdapter(getActivity(), R.layout.taplist_row, new ArrayList<Brew>());
        setListAdapter(mAdapter);
    }

    /**
     * Updates the adapter
     * @param pubId
     */
    public void onPubSelected(String pubId) {
    	this.pubId = pubId;
    	mAdapter.clear();
    	List<Brew> taplist = PubContent.TAP_LISTS.get(pubId);
    	if (taplist != null) {
    		mAdapter.addAll(taplist);
    		mAdapter.notifyDataSetChanged();
    	}		
    	
    	final Activity activity = getActivity();    	
    	// Lazily Set the logo
    	if (logo == null) {
        	final View header = activity.getActionBar().getCustomView();
    		logo = (ImageView) header.findViewById(R.id.actionBarLogo);
    	}
    	
		// Try to use existing File, otherwise show default logo and begin download
	    String cacheDir = activity.getCacheDir().getPath();
	    File imageFile = new File(cacheDir, PubContent.PUB_LOGOS.get(pubId));
	    try {
			FileInputStream fis = new FileInputStream(imageFile);
			Bitmap bmp = BitmapFactory.decodeStream(new FlushedInputStream(fis));
			logo.setImageBitmap(bmp);
		} catch (FileNotFoundException e) {
			Log.d(DEBUG_TAG, "Unable to use Image File: " + e.getLocalizedMessage());
			logo.setImageResource(R.drawable.header);
			
			// @TODO Begin File Download
		};  
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
       
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        List<Brew> taplist = PubContent.TAP_LISTS.get(pubId);
        mCallbacks.onItemSelected(taplist.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }  
}