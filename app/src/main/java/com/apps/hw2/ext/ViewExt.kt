package com.apps.hw2.ext

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes

fun View.dpToPx(dp: Int): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun View.dpToPxFromRes(@DimenRes dp: Int): Float = resources.getDimension(dp)

val View.measuredWidthAsFloat: Float
    get() = measuredWidth.toFloat()

val View.measuredHeightAsFloat: Float
    get() = measuredHeight.toFloat()

val View.measuredWidthWithMargins: Int
    get() {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        return measuredWidth + params.rightMargin + params.leftMargin
    }

val View.measuredHeightWithMargins: Int
    get() {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        return measuredHeight + params.topMargin + params.bottomMargin
    }

val View.marginTop: Int
    get() = (layoutParams as ViewGroup.MarginLayoutParams).topMargin

val View.marginLeft: Int
    get() = (layoutParams as ViewGroup.MarginLayoutParams).leftMargin