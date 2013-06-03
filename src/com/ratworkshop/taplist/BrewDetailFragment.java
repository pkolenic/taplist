package com.ratworkshop.taplist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.contentprovider.PubContent;
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
    public static final String ARG_ITEM_ID = "item_id";

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

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mBrew = PubContent.BREW_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brew_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mBrew != null) {
            ((TextView) rootView.findViewById(R.id.brew_detail)).setText(mBrew.name);
            ((TextView) rootView.findViewById(R.id.brew_abv)).setText(Double.toString(mBrew.abv));
        }

        return rootView;
    }
}
