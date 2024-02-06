package com.apps.hw2.channels.pager.repository

import com.apps.hw2.net.holder.StreamObjectsHolder
import com.apps.hw2.net.holder.TopicObjectsHolder
import com.apps.hw2.net.model.SubscribedStreamObjectHolder
import io.reactivex.Single

interface IStreamsRemoteDataStore {
    fun getAllStreams(): Single<StreamObjectsHolder>

    fun getStreamTopics(streamId: Int): Single<TopicObjectsHolder>

    fun getSubscribedStreams(): Single<SubscribedStreamObjectHolder>
}