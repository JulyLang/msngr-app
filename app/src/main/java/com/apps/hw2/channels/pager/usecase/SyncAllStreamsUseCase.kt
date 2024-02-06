package com.apps.hw2.channels.pager.usecase

import com.apps.hw2.channels.pager.repository.IStreamsRepository
import io.reactivex.Single
import javax.inject.Inject

class SyncAllStreamsUseCase @Inject constructor(
    private val streamsRepository: IStreamsRepository
): ISyncStreamsUseCase {
    override fun execute(): Single<Boolean> {
      return streamsRepository.syncAllStreams()
    }
}