package com.apps.hw2.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apps.hw2.db.converter.ReactionsConverter
import com.apps.hw2.db.converter.TopicsConverter
import com.apps.hw2.db.dao.MessagesDao
import com.apps.hw2.db.dao.StreamsDao
import com.apps.hw2.db.dao.SubscribedStreamsDao
import com.apps.hw2.db.entity.messages.MessagesEntity
import com.apps.hw2.db.entity.streams.StreamsEntity
import com.apps.hw2.db.entity.streams.SubscribedStreamsEntity

@Database(
    entities = [
        StreamsEntity::class,
        SubscribedStreamsEntity::class,
        MessagesEntity::class
    ], version = 1
)
@TypeConverters(
    TopicsConverter::class,
    ReactionsConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun streamsDao(): StreamsDao
    abstract fun subscribedStreamsDao(): SubscribedStreamsDao
    abstract fun messagesDao(): MessagesDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            return AppDatabaseSingleton.getInstance(context).appDatabase
        }
    }
}