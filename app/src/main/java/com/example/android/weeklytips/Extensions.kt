package com.example.android.weeklytips

import android.view.View

fun View.flipVisibility() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.INVISIBLE
    } else {
        this.visibility = View.VISIBLE
    }
}