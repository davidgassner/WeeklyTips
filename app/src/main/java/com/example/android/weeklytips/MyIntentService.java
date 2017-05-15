package com.example.android.weeklytips;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.android.weeklytips.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class MyIntentService extends IntentService {

    public static final String TAG = "MyIntentService";

    private static final String ACTION_FOO = "com.example.android.weeklytips.action.FOO";
    private static final String EXTRA_NAME = "com.example.android.weeklytips.extra.NAME";
    private static final String EXTRA_JOB = "com.example.android.weeklytips.extra.JOB";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context, String name, String job) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_JOB, job);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String name = intent.getStringExtra(EXTRA_NAME);
                final String job = intent.getStringExtra(EXTRA_JOB);
                handleActionFoo(name, job);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String name, String job) {
        Log.i(TAG, "handleActionFoo: starting service");
        MessageEvent event = new MessageEvent("handleActionFoo: starting service");
        EventBus.getDefault().post(event);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "handleActionFoo: name=" + name + ", job=" + job);
        event = new MessageEvent("handleActionFoo: name=" + name + ", job=" + job);
        EventBus.getDefault().post(event);
    }
}
