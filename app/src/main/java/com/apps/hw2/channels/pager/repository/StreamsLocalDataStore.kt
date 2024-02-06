package com.apps.hw2.channels.pager.repository

import com.apps.hw2.channels.pager.stream.IStreamItem
import com.apps.hw2.channels.pager.stream.NetStreamsMapper
import com.apps.hw2.channels.pager.stream.StreamShimmers
import com.apps.hw2.channels.pager.stream.SubscribedStreamsMapper
import com.apps.hw2.db.AppDatabase
import com.apps.hw2.db.entity.streams.StreamsEntity
import com.apps.hw2.db.entity.streams.SubscribedStreamsEntity
import com.apps.hw2.db.mapper.StreamsEntitiesMapper
import com.apps.hw2.db.mapper.SubscribedStreamsEntitiesMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class StreamsLocalDataStore @Inject constructor(
    private val appDatabase: AppDatabase
) : IStreamsLocalDataStore {

    private val streamsDao = appDatabase.streamsDao()

    private val subscribedStreamsDao = appDatabase.subscribedStreamsDao()

    override fun observeAllStreams(): Observable<List<IStreamItem>> {


        return streamsDao.getAllStreamsFromDB()
            .map { StreamsEntitiesMapper().mapStreamsEntitiesToStreamObjects(it) }
            .map { NetStreamsMapper().mapNetStreamsToIStreams(it) }
            .flatMap { dbStreams ->
                if (dbStreams.isEmpty()) {
                    Observable.just(StreamShimmers.shimmers)
                } else {
                    Observable.just(dbStreams)
                }
            }
    }

    override fun insertAllStreamsIntoDB(streams: List<StreamsEntity>): Completable {
        return streamsDao.insertAllStreamsIntoDB(streams)
    }

    override fun observeSubscribedStreams(): Observable<List<IStreamItem>> {
        return subscribedStreamsDao.getSubscribedStreamsFromDB()
            .map { SubscribedStreamsEntitiesMapper().mapSubscribedStreamsEntitiesToSubscribedStreamObjects(it) }
            .map { SubscribedStreamsMapper().mapSubscribedStreamsIStreams(it) }
            .flatMap { dbStreams ->
                if (dbStreams.isEmpty()) {
                    Observable.just(StreamShimmers.shimmers)
                } else {
                    Observable.just(dbStreams)
                }
            }
    }

    override fun insertSubscribedStreamsIntoDB(streams: List<SubscribedStreamsEntity>): Completable {
        return subscribedStreamsDao.insertSubscribedStreamsIntoDB(streams)
    }
}