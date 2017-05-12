package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.android.weeklytips.dialogs.EditTextDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements EditTextDialog.EditTextDialogListener {

    private static final String TAG = "MainActivity";
    public static final String DIALOG_TAG = "dialog_tag";

    String name = "";

    @BindView(R.id.log)
    TextView mLog;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinator;

    @OnClick(R.id.run_button)
    public void runCode() {
        if (mLog.getText().toString().equals(getString(R.string.intro_text))) {
            mLog.setText("");
        }
        log("Running code");

        EditTextDialog dialog = EditTextDialog.Companion.newInstance("Enter your name", name);
        dialog.show(getSupportFragmentManager(), DIALOG_TAG);
    }

    @OnClick(R.id.clear_button)
    public void clearLog() {
        mLog.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog.setMovementMethod(new ScrollingMovementMethod());
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

    @Override
    public void onEditTextDialogOK(String newValue, String tag) {
        log("You entered a value of " + newValue);
        name = newValue;
    }
}
