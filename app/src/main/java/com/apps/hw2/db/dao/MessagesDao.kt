package com.apps.hw2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.hw2.db.entity.messages.MessagesEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MessagesDao {
    @Query("SELECT * FROM messages WHERE stream_id=:streamId AND subject=:topicName ORDER BY timestamp ASC")
    fun getMessagesFromDB(streamId: Int, topicName: String): Observable<List<MessagesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessageIntoDB(message: MessagesEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessagesIntoDB(messages: List<MessagesEntity>): Completable
}