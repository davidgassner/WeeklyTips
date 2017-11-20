package com.example.android.weeklytips.model;

public class DataItem {
    private int itemId;
    private String description;

    public DataItem(int itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
