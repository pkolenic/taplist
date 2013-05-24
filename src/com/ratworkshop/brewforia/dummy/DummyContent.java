package com.ratworkshop.brewforia.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ratworkshop.brewforia.R;
import com.ratworkshop.brewforia.models.Brew;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Brew> ITEMS = new ArrayList<Brew>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Brew> ITEM_MAP = new HashMap<String, Brew>();

    static {
        // Add 3 sample items.
        addItem(new Brew("1", "Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false, R.drawable.belgian_ale));
        addItem(new Brew("2", "Mendocino Black Hawk Stout", "", 5.6, 3.49, 6.99, 13.99, false, R.drawable.porter_stout));
        addItem(new Brew("3", "Selkirk Abbey Saint Stephen Saison", "", 5.6, 3.69, 6.99, 13.99, false, R.drawable.wheat_beer));
        addItem(new Brew("4", "Crooked Fence Devil's Pick IPA", "", 7.0, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale));
        addItem(new Brew("5", "Green Flash Imperial Red Rye IPA", "", 8.5, 4.59, 10.59, 21.09, false, R.drawable.belgian_ale));
        addItem(new Brew("6", "Snake River Packed Porter", "", 6.6, 3.69, 7.39, 14.79, false, R.drawable.porter_stout));
        addItem(new Brew("7", "Goodlife Mt Rescue Pale Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub));
        addItem(new Brew("8", "Sockeye Winterfest Winter IPA", "", 6.8, 3.49, 6.99, 13.99, false, R.drawable.belgian_ale));
        addItem(new Brew("9", "Payette Mutton Buster Brown Ale", "", 5.5, 3.49, 6.99, 13.99, false, R.drawable.craft_pub));
        addItem(new Brew("10", "Seven Brides Lil's Pils", "", 5.6, 3.00, 5.00, 9.00, true, R.drawable.classic_pilsner));
    }

    private static void addItem(Brew item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
