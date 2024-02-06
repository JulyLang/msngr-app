package com.apps.hw2.net

import okhttp3.OkHttpClient

internal object MyOkHttpClient {
    private var okHttpClient: OkHttpClient? = null

    fun getClient(): OkHttpClient {
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor)
                .build()
        }
        return okHttpClient!!
    }
}