package com.apps.hw2.db

import android.content.Context
import androidx.room.Room
import com.apps.hw2.util.SingletonHolder

class AppDatabaseSingleton private constructor(context: Context) {

    var appDatabase: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "database-name"
    ).build()

    companion object : SingletonHolder<AppDatabaseSingleton, Context>(::AppDatabaseSingleton)
}