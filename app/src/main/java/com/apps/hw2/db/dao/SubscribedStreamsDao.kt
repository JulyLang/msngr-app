package com.apps.hw2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.hw2.db.entity.streams.SubscribedStreamsEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface SubscribedStreamsDao {

    @Query("SELECT * FROM subscribed_streams")
    fun getSubscribedStreamsFromDB(): Observable<List<SubscribedStreamsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubscribedStreamsIntoDB(subcribedStreams: List<SubscribedStreamsEntity>): Completable
}