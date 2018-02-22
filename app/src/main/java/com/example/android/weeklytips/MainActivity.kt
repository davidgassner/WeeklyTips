package com.example.android.weeklytips

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var mLog: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        mLog = findViewById(R.id.log)
        mLog!!.movementMethod = ScrollingMovementMethod()

        findViewById<View>(R.id.run_button)
                .setOnClickListener { runCode(it) }
        findViewById<View>(R.id.clear_button)
                .setOnClickListener { clearLog() }
    }

    /**
     * Run some code. If the TextView only displays the intro message, clear it first.
     */
    private fun runCode(view: View?) {
        if (mLog!!.text.toString() == getString(R.string.intro_text)) {
            mLog!!.text = ""
        }
        log("Running code")
        log("You click the button with id " +
                resources.getResourceEntryName(view!!.id))
    }

    /**
     * Clear the output TextView
     *
     * @param view The button the user clicked
     */
    fun clearLog() {
        mLog!!.text = ""
    }

    /**
     * Logs a message - called initially by runCode()
     *
     * @param message The message to display
     */
    private fun log(message: String) {
        Log.i(TAG, message)
        mLog!!.append(message + "\n")
        adjustScroll()
    }

    /**
     * Adjusts scroll vertically to ensure last line is displayed
     */
    private fun adjustScroll() {
        val scrollAmount = mLog!!.layout
                .getLineTop(mLog!!.lineCount) - mLog!!.height
        if (scrollAmount > 0)
            mLog!!.scrollTo(0, scrollAmount)
        else
            mLog!!.scrollTo(0, 0)
    }

    /**
     * Display the options menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_choose_red -> handleRed()
            R.id.action_choose_green -> handleGreen()
            R.id.action_choose_blue -> handleBlue()
            else -> false
        }
    }

    private fun handleRed(): Boolean {
        log("You selected Red")
        return true;
    }

    private fun handleGreen(): Boolean {
        log("You selected Green")
        return true
    }

    private fun handleBlue(): Boolean {
        log("You selected Blue")
        return true
    }

    companion object {

        private val TAG = "MainActivity"
    }

}
