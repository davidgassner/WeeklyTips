package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.weeklytips.events.MessageEvent;
import com.example.android.weeklytips.model.DataItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLog;
    private List<DataItem> mItems;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog = findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * Run some code. If the TextView only displays the intro message, clear it first.
     */
    public void runCode(View view) {
        if (mLog.getText().toString().equals(getString(R.string.intro_text))) {
            mLog.setText("");
        }

        if (mItems == null) {
            mItems = new ArrayList<>();
        }

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                int itemId = 0;

                while (true) {
                    try {
                        for (int i = 0; i < 100; i++) {
                            itemId++;
                            mItems.add(new DataItem(itemId,
                                    "Item number " + itemId));
                        }
                    } catch (NullPointerException e) {
                        break;
                    }
                    MessageEvent event = new MessageEvent("Number of items: " + itemId);
                    EventBus.getDefault().post(event);

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }

                }

                MessageEvent stopEvent = new MessageEvent("Thread finishing");
                EventBus.getDefault().post(stopEvent);
            }
        };

        mThread = new Thread(r);
        mThread.start();
    }

    /**
     * Clear the output TextView
     *
     * @param view The button the user clicked
     */
    public void clearLog(View view) {
        mLog.setText("");
        if (mThread != null) {
            mThread.interrupt();
        }
        if (mItems != null) {
            mItems.clear();
        }
        mItems = null;
        log("Cleared items from List");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        log(event.getMessage());
    }

    /**
     * Logs a message - called initially by runCode()
     *
     * @param message The message to display
     */
    private void log(String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        adjustScroll();
    }

    /**
     * Adjusts scroll vertically to ensure last line is displayed
     */
    private void adjustScroll() {
        final int scrollAmount = mLog.getLayout()
                .getLineTop(mLog.getLineCount()) - mLog.getHeight();
        if (scrollAmount > 0)
            mLog.scrollTo(0, scrollAmount);
        else
            mLog.scrollTo(0, 0);
    }

}
