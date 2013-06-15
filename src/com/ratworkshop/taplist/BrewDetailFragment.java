package com.ratworkshop.taplist;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.models.Brew;
import com.ratworkshop.taplist.models.Pub;

/**
 * A fragment representing a single Brew detail screen.
 * This fragment is either contained in a {@link BrewListActivity}
 * in two-pane mode (on tablets) or a {@link BrewDetailActivity}
 * on handsets.
 */
public class BrewDetailFragment extends Fragment {

//	private static final String DEBUG_TAG = "BrewDetailFragment";
    private Brew mBrew;
    private Pub mPub;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BrewDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(getString(R.string.ARG_BREW_ID)) && getArguments().containsKey(getString(R.string.ARG_PUB_ID))) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
        	mPub = PubContent.PUB_MAP.get(getArguments().getString(getString(R.string.ARG_PUB_ID)));
        	mBrew = mPub.getBrew(getArguments().getString(getString(R.string.ARG_BREW_ID)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brew_detail, container, false);
    	final Activity activity = getActivity();  
    	activity.getActionBar().setTitle(mPub.getName());
    	
    	((ScrollView) rootView.findViewById(R.id.brew_detail_scroll)).setBackgroundColor(mPub.getTaplist_background_color());
    	LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.brew_detail_view);
    	
        if (mBrew != null) {
        	((ImageView) layout.findViewById(R.id.details_brew_image)).setImageResource(mBrew.getIcon());
        	if (mBrew.isFeatured()) {
        		((TextView) layout.findViewById(R.id.details_featured_brew)).setVisibility(View.VISIBLE);
        	} else {
        		((TextView) layout.findViewById(R.id.details_featured_brew)).setVisibility(View.GONE);
        	}
        	
        	// Brew Name
        	TextView brew_name = (TextView) layout.findViewById(R.id.details_brew_name);
        	brew_name.setText(mBrew.getName());
        	brew_name.setTextColor(Color.parseColor(mPub.getTaplist_name_color()));
        	brew_name.setTypeface(mPub.getTaplist_name_typeface());
            
            // ABV
        	TextView abv = (TextView) layout.findViewById(R.id.details_brew_abv);
        	abv.setText(String.format("%.1f%% ABV", mBrew.getAbv()));
        	abv.setTextColor(Color.parseColor(mPub.getTaplist_color()));
        	abv.setTypeface(mPub.getTaplist_typeface());
            
            // Description
        	TextView desc = (TextView) layout.findViewById(R.id.details_brew_description);
        	desc.setText(mBrew.getDescription());
        	desc.setTextColor(Color.parseColor(mPub.getDescription_text_color()));
        	desc.setTypeface(mPub.getDescription_typeface(activity));
        	desc.setTextSize(mPub.getDescription_size());
            
            LinearLayout subheader = (LinearLayout) layout.findViewById(R.id.details_prices_header);
            LinearLayout prices = (LinearLayout) layout.findViewById(R.id.details_prices);
            
            // Glass
            TextView glass_header = (TextView) subheader.findViewById(R.id.details_Glass_header);
            glass_header.setTextColor(Color.parseColor(mPub.getSubheader_text_color()));
            glass_header.setTypeface(mPub.getSubheader_typeface(activity));
            
            TextView details_glass = (TextView) prices.findViewById(R.id.details_Glass);
            details_glass.setText(String.format("$%.2f", mBrew.getGlass()));
            details_glass.setTextColor(Color.parseColor(mPub.getTaplist_color()));
            details_glass.setTypeface(mPub.getTaplist_typeface());
            
            // Quart
            TextView quart_header = (TextView) subheader.findViewById(R.id.details_Quart_header);
            quart_header.setTextColor(Color.parseColor(mPub.getSubheader_text_color()));
            quart_header.setTypeface(mPub.getSubheader_typeface(activity));
            
            TextView details_quart = (TextView) prices.findViewById(R.id.details_Quart);
            details_quart.setText(String.format("$%.2f", mBrew.getQuart()));
            details_quart.setTextColor(Color.parseColor(mPub.getTaplist_color()));
            details_quart.setTypeface(mPub.getTaplist_typeface());
            
            // Growler
            TextView growler_header = (TextView) subheader.findViewById(R.id.details_Growler_header);
            growler_header.setTextColor(Color.parseColor(mPub.getSubheader_text_color()));
            growler_header.setTypeface(mPub.getSubheader_typeface(activity));
            
    		TextView details_growler = (TextView) prices.findViewById(R.id.details_Growler);
    		details_growler.setText(String.format("$%.2f", mBrew.getGrowler()));
            details_growler.setTextColor(Color.parseColor(mPub.getTaplist_color()));
            details_growler.setTypeface(mPub.getTaplist_typeface());
        }

        return rootView;
    }
}
