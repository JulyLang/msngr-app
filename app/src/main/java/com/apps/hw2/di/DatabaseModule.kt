package com.apps.hw2.di

import android.content.Context
import com.apps.hw2.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }
}