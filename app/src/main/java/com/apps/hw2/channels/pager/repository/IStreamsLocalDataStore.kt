package com.apps.hw2.channels.pager.repository

import com.apps.hw2.channels.pager.stream.IStreamItem
import com.apps.hw2.db.entity.streams.StreamsEntity
import com.apps.hw2.db.entity.streams.SubscribedStreamsEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface IStreamsLocalDataStore {
    fun observeAllStreams(): Observable<List<IStreamItem>>

    fun insertAllStreamsIntoDB(streams: List<StreamsEntity>): Completable

    fun observeSubscribedStreams(): Observable<List<IStreamItem>>

    fun insertSubscribedStreamsIntoDB(streams: List<SubscribedStreamsEntity>): Completable
}