package com.apps.hw2.channels.pager.usecase

import com.apps.hw2.channels.pager.repository.IStreamsRepository
import com.apps.hw2.channels.pager.stream.IStreamItem
import io.reactivex.Observable
import javax.inject.Inject

class ObserveAllStreamsUseCase @Inject constructor(
    private val streamsRepository: IStreamsRepository
) : IObserveStreamsUseCase {
    override fun execute(): Observable<List<IStreamItem>> {
        return streamsRepository.observeAllStreams()
    }
}