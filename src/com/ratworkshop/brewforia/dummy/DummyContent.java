package com.ratworkshop.brewforia.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Firestone Walker Wookey Jack Rye Black IPA", "", 8.3, 4.49, 8.99, 17.99, false));
        addItem(new DummyItem("2", "Mendocino Black Hawk Stout", "", 5.6, 3.49, 6.99, 13.99, false));
        addItem(new DummyItem("3", "Selkirk Abbey Saint Stephen Saison", "", 5.6, 3.69, 6.99, 13.99, false));
        addItem(new DummyItem("4", "Crooked Fence Devil's Pick IPA", "", 7.0, 3.49, 6.99, 13.99, false));
        addItem(new DummyItem("5", "Green Flash Imperial Red Rye IPA", "", 8.5, 4.59, 10.59, 21.09, false));
        addItem(new DummyItem("6", "Snake River Packed Porter", "", 6.6, 3.69, 7.39, 14.79, false));
        addItem(new DummyItem("7", "Goodlife Mt Rescue Pale Ale", "", 5.5, 3.49, 6.99, 13.99, false));
        addItem(new DummyItem("8", "Sockeye Winterfest Winter IPA", "", 6.8, 3.49, 6.99, 13.99, false));
        addItem(new DummyItem("9", "Payette Mutton Buster Brown Ale", "", 5.5, 3.49, 6.99, 13.99, false));
        addItem(new DummyItem("10", "Seven Brides Lil's Pils", "", 5.6, 3.00, 5.00, 9.00, true));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String name;
        public String description;
        public double abv;
        public double glass;
        public double quart;
        public double growler;
        public boolean isFeatured;

        public DummyItem(String id, String name, String description, double d, double e, double f, double g, boolean isFeatured) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.abv = d;
            this.glass = e;
            this.quart = f;
            this.growler = g;
            this.isFeatured = isFeatured;
        }

        @Override
        public String toString() {
            return name.concat(" " + abv + "%");
        }
    }
}
