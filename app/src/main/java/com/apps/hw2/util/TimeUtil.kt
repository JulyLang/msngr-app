package com.apps.hw2.util

import android.text.format.DateUtils.DAY_IN_MILLIS

object TimeUtil {

    fun isMoreThanDay(messageDate: Long, currentDate: Long): Boolean {
        return messageDate - currentDate > DAY_IN_MILLIS
    }
}