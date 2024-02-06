package com.apps.hw2.channels.pager.repository

import com.apps.hw2.net.StreamsService
import com.apps.hw2.net.holder.StreamObjectsHolder
import com.apps.hw2.net.holder.TopicObjectsHolder
import com.apps.hw2.net.model.SubscribedStreamObjectHolder
import io.reactivex.Single
import javax.inject.Inject

class StreamsRemoteDataStore @Inject constructor(
    private val streamsService: StreamsService
) : IStreamsRemoteDataStore {
    override fun getAllStreams(): Single<StreamObjectsHolder> {
        return streamsService.getAllStreams()
    }

    override fun getStreamTopics(streamId: Int): Single<TopicObjectsHolder> {
        return streamsService.getStreamTopics(streamId)
    }

    override fun getSubscribedStreams(): Single<SubscribedStreamObjectHolder> {
        return streamsService.getSubscribedStreams()
    }
}