package com.example.android.weeklytips.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class DataItemHelper {

    /**
     * An array of sample items.
     */
    public static final List<DataItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample items, by ID.
     */
    public static final Map<String, DataItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 4;

    static {
        // Add some items.
        addItem(new DataItem("1", "apple_pie"));
        addItem(new DataItem("2", "artichokes"));
        addItem(new DataItem("3", "berry_tart"));
        addItem(new DataItem("4", "brownie"));
    }

    private static void addItem(DataItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A data item
     */
    public static class DataItem {
        public final String id;
        public final String content;

        DataItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
