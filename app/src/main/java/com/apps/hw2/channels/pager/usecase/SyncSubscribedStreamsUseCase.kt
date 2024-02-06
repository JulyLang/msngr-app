package com.apps.hw2.channels.pager.usecase

import com.apps.hw2.channels.pager.repository.IStreamsRepository
import com.apps.hw2.channels.pager.repository.StreamsRepository
import com.apps.hw2.db.AppDatabase
import com.apps.hw2.db.mapper.SubscribedStreamsObjectsMapper
import com.apps.hw2.net.StreamsService
import io.reactivex.Single
import javax.inject.Inject

class SyncSubscribedStreamsUseCase @Inject constructor(
    private val streamsRepository: IStreamsRepository
) : ISyncStreamsUseCase {
    override fun execute(): Single<Boolean> {
        return streamsRepository.syncSubscribedStreams()
    }
}