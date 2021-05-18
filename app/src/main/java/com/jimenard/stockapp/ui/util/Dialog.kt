package com.jimenard.stockapp.ui.util

import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.jimenard.stockapp.R

fun Dialog.initCurrentSettings(context: Context) {
    val metrics: DisplayMetrics = context.resources.displayMetrics
    val width = metrics.widthPixels
    this.window?.setBackgroundDrawable(
        ContextCompat.getDrawable(
            context,
            R.drawable.background_light_all_rounded_corners
        )
    )
    this.window?.attributes?.windowAnimations = R.style.DialogAnimation
    this.show()
    this.setCanceledOnTouchOutside(false)
    this.window?.setLayout(
        (6 * width) / 7,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}