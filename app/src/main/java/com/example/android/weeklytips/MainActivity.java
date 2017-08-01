package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.example.android.weeklytips.data.DataItem;
import com.example.android.weeklytips.data.DataProvider;
import com.example.android.weeklytips.data.MyListAdapter;
import com.mobeta.android.dslv.DragSortListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<DataItem> mDataList = DataProvider.dataList;
    private DragSortListView mListView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (DragSortListView) findViewById(R.id.my_list_view);

        final MyListAdapter myAdapter = new MyListAdapter(this, mDataList);
        mListView.setAdapter(myAdapter);
        log("Displaying data");

        mListView.setDropListener(new DragSortListView.DropListener() {
            @Override
            public void drop(int from, int to) {
                DataItem item = mDataList.get(from);
                myAdapter.remove(item);
                myAdapter.insert(item, to);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Logs a message - called initially by runCode()
     *
     * @param message The message to display
     */
    private void log(String message) {
        Log.i(TAG, message);
    }

}
