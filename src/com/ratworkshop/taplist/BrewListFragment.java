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
import android.graphics.Typeface;
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
import com.ratworkshop.taplist.utilities.TaplistStyle;
import com.ratworkshop.taplist.utilities.TaplistTypeface;

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
	private ImageView pub_logo;
	private TextView title;
	private TextView subtitle;
	private LinearLayout header;
	private LinearLayout subheader;
	private TextView abvLabel;
	private TextView glassLabel;
	private TextView quartLabel;
	private TextView growlerLabel;
	    
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
    	if (taplist != null) {
    		mAdapter.addAll(taplist);
    		mAdapter.notifyDataSetChanged();
    		
    		// Set the Styling for the Current TapList
    	}		
    	
    	final Activity activity = getActivity();    	
    	// Lazily Instantiate UI Components
    	if (header == null) {
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
		header.setBackgroundColor(Color.parseColor(pub.getHeader_color()));
		subheader.setBackgroundColor(Color.parseColor(pub.getSubheader_color()));
		
		// Set Text Colors
		title.setTextColor(Color.parseColor(pub.getTitle_color()));
		setTextStyle(title, pub.getTitle_typeface(), pub.getTitle_style(), pub.is_title_custom_font());
		
		subtitle.setTextColor(Color.parseColor(pub.getSubtitle_color()));
		setTextStyle(subtitle, pub.getSubtitle_typeface(), pub.getSubtitle_style(), pub.is_subtitle_custom_font());
		
		abvLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		setTextStyle(abvLabel, pub.getSubheader_typeface(), pub.getSubheader_style(), pub.is_subheader_custom_font());
		
		glassLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		setTextStyle(glassLabel, pub.getSubheader_typeface(), pub.getSubheader_style(), pub.is_subheader_custom_font());
		
		quartLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		setTextStyle(quartLabel, pub.getSubheader_typeface(), pub.getSubheader_style(), pub.is_subheader_custom_font());
		
		growlerLabel.setTextColor(Color.parseColor(pub.getSubheader_text_color()));
		setTextStyle(growlerLabel, pub.getSubheader_typeface(), pub.getSubheader_style(), pub.is_subheader_custom_font());
    }
    
    /**
     * Sets the Font Styling for a TextView
     * @param view - TextView to set font for
     * @param typeface - Name of default TypeFace, or path to Custom TypeFace
     * @param style - Name of Style to apply to TypeFace
     * @param size - Size in SP to set the Font
     * @param isCustomFont - flag to mark if using a custom font
     */
    private void setTextStyle(TextView view, String typeface, String style, boolean isCustomFont) {
    	// @TODO - Move this into a custom subclass of TextView
    	Typeface tf;
    	if (isCustomFont) {
    		final Activity activity = getActivity();  
    		tf = Typeface.createFromFile(String.format("%s/fonts/%s", activity.getCacheDir(), typeface));
    	} else {
    		TaplistTypeface ttf = TaplistTypeface.valueOf(typeface);
    		switch(ttf) {
    		default:
    		case DEFAULT :
    			tf = Typeface.DEFAULT;
    			break;
    		case DEFAULT_BOLD:
    			tf = Typeface.DEFAULT_BOLD;
    			break;
    		case MONOSPACE:
    			tf = Typeface.MONOSPACE;
    			break;
    		case SAN_SERIF:
    			tf = Typeface.SANS_SERIF;
    			break;
    		case SERIF:
    			tf = Typeface.SERIF;
    			break;
    		}
    	}
    	int s = Typeface.NORMAL;
    	TaplistStyle ts = TaplistStyle.valueOf(style);
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
    	
    	view.setTypeface(tf, s);
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
        List<Brew> taplist = PubContent.PUB_MAP.get(pubId).getTaplist();
        mCallbacks.onItemSelected(taplist.get(position).getId());
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