package com.apps.hw2.di

import android.content.Context
import com.apps.hw2.app.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val app: App) {

    @Provides
    fun provideApplicationContext(): Context {
        return app
    }
}