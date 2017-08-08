package com.example.android.weeklytips;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.weeklytips.model.DataItem;

import java.io.IOException;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
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

    public void runCode(View view) {
        clearLog(null);
        WebServiceTask task = new WebServiceTask();
        task.execute();
    }

    public void clearLog(View view) {
        mLog.setText("");
    }

    private void log(String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        adjustScroll();
    }

    private void adjustScroll() {
        final int scrollAmount = mLog.getLayout()
                .getLineTop(mLog.getLineCount()) - mLog.getHeight();
        if (scrollAmount > 0)
            mLog.scrollTo(0, scrollAmount);
        else
            mLog.scrollTo(0, 0);
    }

    private class WebServiceTask extends AsyncTask<Void, Void, DataItem[]> {
        @Override
        protected DataItem[] doInBackground(Void... params) {
            MyWebService webService = MyWebService.retrofit.create(MyWebService.class);
            Call<DataItem[]> call = webService.dataItems();

            try {
                return call.execute().body();
            } catch (IOException e) {
                log(e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(DataItem[] dataItems) {
            for (DataItem item :
                    dataItems) {
                log(item.toString());
            }
        }
    }


}
