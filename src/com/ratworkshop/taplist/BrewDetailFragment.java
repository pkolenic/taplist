package com.ratworkshop.taplist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    	
        if (mBrew != null) {
        	((ImageView) rootView.findViewById(R.id.details_brew_image)).setImageResource(mBrew.getIcon());
        	
            ((TextView) rootView.findViewById(R.id.details_brew_name)).setText(mBrew.getName());
            ((TextView) rootView.findViewById(R.id.details_brew_abv)).setText(String.format("%.1f%% ABV", mBrew.getAbv()));
            ((TextView) rootView.findViewById(R.id.details_brew_description)).setText(mBrew.getDescription());
            
            LinearLayout prices = (LinearLayout) rootView.findViewById(R.id.details_prices);
            ((TextView) prices.findViewById(R.id.details_Glass)).setText(String.format("$%.2f", mBrew.getGlass()));
            ((TextView) prices.findViewById(R.id.details_Quart)).setText(String.format("$%.2f", mBrew.getQuart()));
    		((TextView) prices.findViewById(R.id.details_Growler)).setText(String.format("$%.2f", mBrew.getGrowler()));
        }

        return rootView;
    }
}
