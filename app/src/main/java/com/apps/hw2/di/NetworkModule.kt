package com.apps.hw2.di

import com.apps.hw2.BuildConfig
import com.apps.hw2.net.MyOkHttpClient
import com.apps.hw2.net.StreamsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideStreamsService(): StreamsService {
        return provideRetrofitService(provideBaseUrl()).create(StreamsService::class.java)
    }

    private fun provideRetrofitService(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(MyOkHttpClient.getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}