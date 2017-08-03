package com.example.android.weeklytips;

import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLog;

    private static final String AUDIO_FILE_NAME1 = "cartoon_fall.wav";
    private static final String AUDIO_FILE_NAME2 = "cartoon_whoop.wav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());
    }

    public void playAudio(int fileNumber) {
    }

    /**
     * Clear the output TextView
     *
     * @param view The button the user clicked
     */
    public void clearLog(View view) {
        mLog.setText("");
    }

    /**
     * Logs a message - called initially by playAudio()
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
        try {
            final int scrollAmount = mLog.getLayout()
                    .getLineTop(mLog.getLineCount()) - mLog.getHeight();
            if (scrollAmount > 0)
                mLog.scrollTo(0, scrollAmount);
            else
                mLog.scrollTo(0, 0);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void playAudioFile1(View view) {
    }

    public void playAudioFile2(View view) {
    }
}
