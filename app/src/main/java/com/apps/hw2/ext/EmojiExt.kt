package com.apps.hw2.ext

fun String.getAndroidReadableEmoji(): String {
    return getAndroidReadableEmoji(codePoint = this.toInt(radix = 16))
}

private fun getAndroidReadableEmoji(codePoint: Int): String {
    return if (Character.charCount(codePoint) == 1) {
        codePoint.toString()
    } else {
        String(Character.toChars(codePoint))
    }
}