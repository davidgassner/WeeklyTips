package com.example.android.weeklytips;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_PERMISSION_SEND_SMS = 1001;
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());

    }

    public void addData(View view) {
    }

    public void queryData(View view) {
    }

    /**
     * Clear the output TextView
     * @param view The button the user clicked
     */
    public void clearLog(View view) {
        mLog.setText("");
    }

    /**
     * Logs a message - called initially by addData()
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

    private boolean checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            log("Permission already granted");
            return true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_PERMISSION_SEND_SMS);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_SEND_SMS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    log("Permission granted!");
                } else {
                    log("Permission to send an SMS is needed!");
                }
                break;
        }
    }
}
