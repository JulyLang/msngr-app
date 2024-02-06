package com.apps.hw2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.hw2.db.entity.streams.StreamsEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface StreamsDao {
    @Query("SELECT * FROM streams")
    fun getAllStreamsFromDB(): Observable<List<StreamsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStreamsIntoDB(streams: List<StreamsEntity>): Completable
}