package com.ratworkshop.taplist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ratworkshop.taplist.adapters.TaplistAdapter;
import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.models.Pub;
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
    private static final String STATE_ACTIVATED_PUB = "activated_pub";

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
	private ImageView pub_logo;
	private TextView title;
	private TextView subtitle;
	private LinearLayout header;
	private LinearLayout subheader;
	private TextView abvLabel;
	private TextView glassLabel;
	private TextView quartLabel;
	private TextView growlerLabel;
	private ListView list;
	    
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BrewListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new TaplistAdapter(getActivity(), R.layout.taplist_row, new ArrayList<Brew>());
        setListAdapter(mAdapter);
    }

    /**
     * Updates the adapter
     * @param pubId
     */
    public void onPubSelected(String pubId) {
    	this.pubId = pubId;
    	final Pub pub = PubContent.PUB_MAP.get(pubId);
    	mAdapter.clear();
    	List<Brew> taplist = pub.getTaplist();
    	
    	final Activity activity = getActivity();  
    	if (taplist != null) {
    		// Set the Styling for the Current TapList
    		mAdapter.setBackgroundColor(pub.getTaplist_background_color());
    		
    		// Details
    		mAdapter.setListColor(pub.getTaplist_color());    		
    		mAdapter.setListFontFace(pub.getTaplist_typeface(activity));
    		mAdapter.setListSize(pub.getTaplist_size());
    		
    		// Featured
    		mAdapter.setFeaturedFontFace(pub.getFeatured_brew_typeface(activity));
    		mAdapter.setFeaturedColor(pub.getFeatured_brew_color());
    		mAdapter.setFeaturedSize(pub.getFeatured_brew_size());
    		
    		// Name
    		mAdapter.setListNameFontFace(pub.getTaplist_name_typeface(activity));
    		mAdapter.setListNameColor(pub.getTaplist_name_color());
    		mAdapter.setListNameSize(pub.getTaplist_name_size());
    		
    		// Featured Name
    		mAdapter.setFeaturedNameFontFace(pub.getFeatured_brew_name_typeface(activity));
    		mAdapter.setFeaturedNameColor(pub.getFeatured_brew_name_color());
    		mAdapter.setFeaturedNameSize(pub.getFeatured_brew_name_size());
    		// TODO set Size
    		
    		// Add tap list
    		mAdapter.addAll(taplist);
    		mAdapter.notifyDataSetChanged();
    	}		
    	  	
    	// Lazily Instantiate UI Components
    	if (header == null) {
    		list = (ListView) activity.findViewById(android.R.id.list);
    		header = (LinearLayout) activity.findViewById(R.id.taplistHeader);
    		pub_logo = (ImageView) header.findViewById(R.id.pubLogo);
    		title = (TextView) header.findViewById(R.id.taplistTitle);
    		subtitle = (TextView) header.findViewById(R.id.taplistSubTitle);
    		subheader = (LinearLayout) header.findViewById(R.id.taplistSubheader);
    		abvLabel = (TextView) subheader.findViewById(R.id.taplistHeaderABV);
    		glassLabel = (TextView) subheader.findViewById(R.id.taplistHeaderGlass);
    		quartLabel = (TextView) subheader.findViewById(R.id.taplistHeaderQuart);
    		growlerLabel = (TextView) subheader.findViewById(R.id.taplistHeaderGrowler);
    	}
    	
		// If Custom Logo set show it, otherwise show the Title and SubTitle		
		if (pub.getLogo() != null) {
		    String cacheDir = activity.getCacheDir().getPath();
		    File imageFile = new File(cacheDir, pub.getLogo());
		    try {
				FileInputStream fis = new FileInputStream(imageFile);
				Bitmap bmp = BitmapFactory.decodeStream(new FlushedInputStream(fis));
				pub_logo.setImageBitmap(bmp);
			} catch (FileNotFoundException e) {
				Log.d(DEBUG_TAG, "Unable to use Image File: " + e.getLocalizedMessage());
				pub_logo.setVisibility(View.GONE);

				title.setText(pub.getTitle());
				title.setVisibility(View.VISIBLE);

				subtitle.setText(pub.getSubtitle());
				subtitle.setVisibility(View.VISIBLE);
			};  
			
			pub_logo.setVisibility(View.VISIBLE);
			title.setVisibility(View.GONE);
			subtitle.setVisibility(View.GONE);
		} else {
			pub_logo.setVisibility(View.GONE);

			title.setText(pub.getTitle());
			title.setVisibility(View.VISIBLE);

			subtitle.setText(pub.getSubtitle());
			subtitle.setVisibility(View.VISIBLE);
		}
		
		// Set Background Colors
		list.setBackgroundColor(pub.getTaplist_background_color());
		header.setBackgroundColor(pub.getHeader_color());
		subheader.setBackgroundColor(pub.getSubheader_color());
		
		// Set Text Colors
		title.setTextColor(Color.parseColor(pub.getTitle_color()));
		title.setTypeface(pub.getTitle_typeface(activity));
		title.setTextSize(pub.getTitle_size());
		
		subtitle.setTextColor(Color.parseColor(pub.getSubtitle_color()));
		subtitle.setTypeface(pub.getSubtitle_typeface(activity));
		subtitle.setTextSize(pub.getSubtitle_size());
		
		abvLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		abvLabel.setTypeface(pub.getSubheader_typeface(activity));
		abvLabel.setTextSize(pub.getSubheader_size());
		
		glassLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		glassLabel.setTypeface(pub.getSubheader_typeface(activity));
		glassLabel.setTextSize(pub.getSubheader_size());
		
		quartLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		quartLabel.setTypeface(pub.getSubheader_typeface(activity));
		quartLabel.setTextSize(pub.getSubheader_size());
		
		growlerLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		growlerLabel.setTypeface(pub.getSubheader_typeface(activity));
		growlerLabel.setTextSize(pub.getSubheader_size());
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
        	
        	if (savedInstanceState.containsKey(STATE_ACTIVATED_PUB)) {
        		onPubSelected(savedInstanceState.getString(STATE_ACTIVATED_PUB));
        	}
        	
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
        List<Brew> taplist = PubContent.PUB_MAP.get(pubId).getTaplist();
        mCallbacks.onItemSelected(taplist.get(position).getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
            outState.putString(STATE_ACTIVATED_PUB, pubId);
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