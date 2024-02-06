package com.apps.hw2.ext

fun String.toByteArrayExt(): ByteArray {
    return this.toByteArray(Charsets.UTF_8)
}