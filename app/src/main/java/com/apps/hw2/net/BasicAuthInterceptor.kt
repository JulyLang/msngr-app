package com.apps.hw2.net

import com.apps.hw2.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal object BasicAuthInterceptor : Interceptor {
    private const val email = BuildConfig.EMAIL
    private const val accessToken = BuildConfig.ACCESS_TOKEN
    private val authCredential = okhttp3.Credentials.basic(email, accessToken)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder().header("Authorization", authCredential)
        return chain.proceed(newRequest.build())
    }
}