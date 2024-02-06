package com.apps.hw2.profile.usecase

import com.apps.hw2.net.StreamsService
import io.reactivex.Single
import javax.inject.Inject

class FetchMyUserUseCase @Inject constructor(
    private val streamsService: StreamsService
) : IFetchMyUserUseCase {
    override fun execute(): Single<Int> {
        return streamsService.getOwnUser()
            .map { user -> user.user_id }
    }
}