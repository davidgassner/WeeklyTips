package com.example.android.weeklytips.data;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<DataItem> dataList;

    static {
        dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            dataList.add(new DataItem(i, "Data item " + i));
        }
    }

}
