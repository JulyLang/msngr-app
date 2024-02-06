package com.apps.hw2.chat.usecase

import com.apps.hw2.db.AppDatabase
import com.apps.hw2.net.StreamsService
import com.apps.hw2.net.mapper.MessagesObjectMapper
import com.apps.hw2.net.model.NarrowMessageBuilder
import io.reactivex.Single
import javax.inject.Inject

class SyncMessagesUseCase @Inject constructor(
    private val streamsService: StreamsService,
    private val appDatabase: AppDatabase
) : ISyncMessagesUseCase {
    override fun execute(streamId: Int, topicName: String): Single<Boolean> {
        val messagesDao = appDatabase.messagesDao()
        return streamsService
            .getMessagesInStreamInTopic(
                numBefore = 20,
                numAfter = 0,
                anchor = "newest",//or "first_unread", 10000000000000000L
                narrow = NarrowMessageBuilder.build(streamId, topicName),
                clientGravatar = false,
                applyMarkdown = false
            )
            .map { MessagesObjectMapper().mapMessageObjectsToMessagesEntities(it.messages) }
            .flatMap { messagesDao.insertMessagesIntoDB(it).andThen(Single.just(true)) }
    }
}