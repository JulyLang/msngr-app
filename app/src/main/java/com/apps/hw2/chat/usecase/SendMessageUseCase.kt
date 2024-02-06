package com.apps.hw2.chat.usecase

import com.apps.hw2.db.AppDatabase
import com.apps.hw2.db.entity.messages.MessagesEntity
import com.apps.hw2.net.StreamsService
import com.apps.hw2.net.mapper.MessagesObjectMapper
import io.reactivex.Single
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val streamsService: StreamsService,
    private val appDatabase: AppDatabase
) : ISendMessageUseCase {
    override fun execute(streamId: Int, topicName: String, messageText: String): Single<Boolean> {
        val messagesDAO = appDatabase.messagesDao()
        return streamsService.sendMessage(
            type = "stream",
            to = listOf(streamId),
            content = messageText,
            topic = topicName
        ).flatMap { result ->
            val id = result.id
            if (id == null) {
                Single.just(false)
            } else {
                streamsService.fetchMessage(
                    messageId = id,
                    applyMarkdown = false,
                    clientGravatar = false,
                )
                    .map { singleMessageObjectHolder -> singleMessageObjectHolder.message }
                    .map { MessagesObjectMapper().mapMessageObjectToMessageEntity(it) }
                    .flatMap { messagesDAO.insertMessageIntoDB(it).andThen(Single.just(true)) }
            }
        }
    }
}