package com.ratworkshop.taplist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.content.PubContent;
import com.ratworkshop.taplist.models.Brew;

/**
 * A fragment representing a single Brew detail screen.
 * This fragment is either contained in a {@link BrewListActivity}
 * in two-pane mode (on tablets) or a {@link BrewDetailActivity}
 * on handsets.
 */
public class BrewDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_BREW_ID = "item_id";
    public static final String ARG_PUB_ID = "pub_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Brew mBrew;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BrewDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_BREW_ID) && getArguments().containsKey(ARG_PUB_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
        	mBrew = PubContent.PUB_MAP.get(getArguments().getString(ARG_PUB_ID)).getBrew(getArguments().getString(ARG_BREW_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brew_detail, container, false);

        if (mBrew != null) {
            ((TextView) rootView.findViewById(R.id.brew_detail)).setText(mBrew.getName());
            ((TextView) rootView.findViewById(R.id.brew_abv)).setText(Double.toString(mBrew.getAbv()));
        }

        return rootView;
    }
}
