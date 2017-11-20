package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.weeklytips.database.NotesDatabase;
import com.example.android.weeklytips.model.Note;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLog;
    private NotesDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog = findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());

        db = NotesDatabase.getInstance(this);

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        NotesDatabase.destroyInstance();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * Run some code. If the TextView only displays the intro message, clear it first.
     */
    public void runCode(View view) {

        int deleted = db.noteDao().deleteAll();
        log(deleted + " notes deleted");

        Note note1 = new Note("This is note 1");
        Note note2 = new Note("This is note 2");
        db.noteDao().insertAll(note1, note2);

        int count = db.noteDao().getCount();

        List<Note> notes = db.noteDao().getAll();
        for (Note note :
                notes) {
            log(note.toString());
        }

        log("Number of notes: " + count);

    }

    /**
     * Clear the output TextView
     *
     * @param view The button the user clicked
     */
    public void clearLog(View view) {
        mLog.setText("");
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
