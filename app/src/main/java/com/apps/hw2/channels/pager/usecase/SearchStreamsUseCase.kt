package com.apps.hw2.channels.pager.usecase

import com.apps.hw2.channels.pager.stream.helper.StreamsSearchHelper
import io.reactivex.Observable
import javax.inject.Inject

class SearchStreamsUseCase @Inject constructor(): ISearchStreamsUseCase {
    override fun execute(): Observable<String> {
        return StreamsSearchHelper.createSearchQueryObservable()
    }
}