package com.example.android.weeklytips.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.weeklytips.R;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<DataItem> {

    private static final String TAG = "MyListAdapter";
    private final Context mContext;
    private List<DataItem> mDataList;

    public MyListAdapter(Context context, List<DataItem> dataList) {
        super(context, R.layout.data_list_item, dataList);
        this.mContext = context;
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // inflate layout if necessary
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.data_list_item, parent, false);
        }

        // Assign widget references
        TextView tv = (TextView) convertView.findViewById(R.id.text_name);
        DataItem item = mDataList.get(position);
        tv.setText(String.format("%s. %s", item.getItemId(), item.getItemName()));

        return convertView;

    }

}
