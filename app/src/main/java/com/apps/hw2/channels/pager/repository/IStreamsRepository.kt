package com.apps.hw2.channels.pager.repository

import com.apps.hw2.channels.pager.stream.IStreamItem
import com.apps.hw2.db.mapper.SubscribedStreamsObjectsMapper
import io.reactivex.Observable
import io.reactivex.Single

interface IStreamsRepository {
    fun observeAllStreams(): Observable<List<IStreamItem>>

    fun syncAllStreams(): Single<Boolean>

    fun observeSubscribedStreams(): Observable<List<IStreamItem>>

    fun syncSubscribedStreams(): Single<Boolean>
}