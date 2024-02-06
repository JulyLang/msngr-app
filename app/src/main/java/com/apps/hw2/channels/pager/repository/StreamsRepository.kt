package com.apps.hw2.channels.pager.repository

import com.apps.hw2.channels.pager.stream.IStreamItem
import com.apps.hw2.db.mapper.SubscribedStreamsObjectsMapper
import com.apps.hw2.net.mapper.StreamsObjectsMapper
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StreamsRepository @Inject constructor(
    private val streamsLocalDataStore: IStreamsLocalDataStore,
    private val streamsRemoteDataStore: IStreamsRemoteDataStore
) : IStreamsRepository {
    override fun observeAllStreams(): Observable<List<IStreamItem>> {
        return streamsLocalDataStore.observeAllStreams()
    }

    override fun syncAllStreams(): Single<Boolean> {
        return streamsRemoteDataStore.getAllStreams()
            .flattenAsObservable { streamsHolder -> streamsHolder.streams }
            .flatMap { stream ->
                streamsRemoteDataStore
                    .getStreamTopics(streamId = stream.stream_id)
                    .toObservable()
                    .map { topicsHolder ->
                        topicsHolder.apply {
                            topics.map { it.streamId = stream.stream_id }
                        }
                    }
                    .map { topicsHolder -> stream.also { it.topics = topicsHolder.topics } }
            }
            .toList()
            .map { StreamsObjectsMapper().mapStreamsObjectsToStreamsEntities(it) }
            .flatMap { streamsLocalDataStore.insertAllStreamsIntoDB(it).andThen(Single.just(true)) }
    }

    override fun observeSubscribedStreams(): Observable<List<IStreamItem>> {
        return streamsLocalDataStore.observeSubscribedStreams()
    }

    override fun syncSubscribedStreams(): Single<Boolean> {
        return streamsRemoteDataStore
            .getSubscribedStreams()
            .flattenAsObservable { subscribedStreamsHolder -> subscribedStreamsHolder.subscriptions }
            .flatMap { stream ->
                streamsRemoteDataStore
                    .getStreamTopics(streamId = stream.stream_id)
                    .toObservable()
                    .map { topicsHolder ->
                        topicsHolder.apply {
                            topics.map { it.streamId = stream.stream_id }
                        }
                    }
                    .map { topicsHolder -> stream.also { it.topics = topicsHolder.topics } }
            }
            .toList()
            .map {
                SubscribedStreamsObjectsMapper()
                    .mapSubscribedStreamsObjectsToSubscribedStreamsEntities(it)
            }
            .flatMap {
                streamsLocalDataStore.insertSubscribedStreamsIntoDB(it).andThen(Single.just(true))
            }
    }
}