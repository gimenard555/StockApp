package com.jimenard.stockapp.ui.util

import android.graphics.Typeface
import android.widget.TextView

fun TextView.changeSelected() {
    this.setTypeface(null, Typeface.BOLD)
}

fun TextView.changeUnselected() {
    this.setTypeface(null, Typeface.NORMAL)
}
