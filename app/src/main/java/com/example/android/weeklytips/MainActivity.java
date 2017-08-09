package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.weeklytips.model.DataItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        MyWebService webService = MyWebService.retrofit.create(MyWebService.class);
        Call<DataItem[]> call = webService.dataItems();

        call.enqueue(new Callback<DataItem[]>() {
            @Override
            public void onResponse(@NonNull Call<DataItem[]> call,
                                   @NonNull Response<DataItem[]> response) {
                if (response.isSuccessful()) {
                    for (DataItem item :
                            response.body()) {
                        log(item.toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataItem[]> call, @NonNull Throwable t) {
                log("There was a failure");
            }
        });
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

}
