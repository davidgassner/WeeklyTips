package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.weeklytips.utilities.FileHelper;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLog;

    private static final String AUDIO_FILE_NAME1 = "cartoon_fall.wav";
    private static final String AUDIO_FILE_NAME2 = "cartoon_whoop.wav";

    private FileHelper fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());

        fileHelper = new FileHelper(this);

    }

    public void playAudio(String fileName) {
        if (!fileHelper.copyAssetToStorage(fileName)) return;
        File file = fileHelper.getFile(fileName);
        if (file.exists()) {
            log("file exists");
        } else {
            log("file doesn't exist");
        }
    }

    /**
     * Clear the output TextView
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
        final int scrollAmount = mLog.getLayout()
                .getLineTop(mLog.getLineCount()) - mLog.getHeight();
        if (scrollAmount > 0)
            mLog.scrollTo(0, scrollAmount);
        else
            mLog.scrollTo(0, 0);
    }

    public void playAudioFile1(View view) {
        playAudio(AUDIO_FILE_NAME1);
    }

    public void playAudioFile2(View view) {
        playAudio(AUDIO_FILE_NAME2);
    }
}
