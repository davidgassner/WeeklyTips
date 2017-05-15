package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText mNameText, mJobText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button run_button = (Button) findViewById(R.id.run_button);
        run_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCode(v);
            }
        });

        mNameText = (EditText) findViewById(R.id.name_text);
        mJobText = (EditText) findViewById(R.id.job_text);
    }

    /**
     * Run some code.
     */
    public void runCode(View view) {
        String name = mNameText.getText().toString();
        String job = mJobText.getText().toString();
        log(String.format("Name: %s, Job: %s", name, job));
    }

    /**
     * Logs a message - called initially by runCode()
     *
     * @param message The message to display
     */
    private void log(String message) {
        Log.i(TAG, message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
