package com.example.android.weeklytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.weeklytips.data.Dog;

import java.util.Collection;

import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLog;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());

        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }

    public void addData(View view) {

        try {
            realm.beginTransaction();
            realm.copyToRealm(new Dog(1, "Scruffy", 8));
            realm.copyToRealm(new Dog(2, "Minnie", 3));
            realm.commitTransaction();
        } catch (RealmPrimaryKeyConstraintException e) {
            realm.cancelTransaction();
        }
        clearLog(null);
        log("Data added to Realm");
    }

    public void queryData(View view) {

        Collection<Dog> dogs = realm.where(Dog.class).findAll().sort("age");
        for (Dog dog :
                dogs) {
            log(dog.toString());
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

}
