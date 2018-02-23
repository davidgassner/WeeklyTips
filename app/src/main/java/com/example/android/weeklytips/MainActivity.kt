package com.example.android.weeklytips

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.method.ScrollingMovementMethod
import android.util.Log
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
                .setOnClickListener { runCode() }
        findViewById<View>(R.id.clear_button)
                .setOnClickListener { clearLog() }
    }

    /**
     * Run some code. If the TextView only displays the
     * intro message, clear it first.
     */
    private fun runCode() {
        if (mLog!!.text.toString() == getString(R.string.intro_text)) {
            mLog!!.text = ""
        }
    }

    /**
     * Clear the output TextView
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
    companion object {

        private val TAG = "MainActivity"

    }

}
