package com.apps.hw2.chat.usecase

import com.apps.hw2.db.AppDatabase
import com.apps.hw2.net.StreamsService
import com.apps.hw2.net.mapper.MessagesObjectMapper
import io.reactivex.Single
import javax.inject.Inject

class DeleteEmojiReactionUseCase @Inject constructor(
    private val streamsService: StreamsService,
    private val appDatabase: AppDatabase
) : IDeleteEmojiReactionUseCase {

    override fun execute(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Single<Boolean> {
        val messagesDao = appDatabase.messagesDao()
        return streamsService.deleteEmojiReaction(messageId, emojiName, emojiCode, reactionType)
            .flatMap {
                streamsService.fetchMessage(
                    messageId = messageId,
                    applyMarkdown = false,
                    clientGravatar = false,
                )
            }
            .map { singleMessageObjectHolder -> singleMessageObjectHolder.message }
            .map { MessagesObjectMapper().mapMessageObjectToMessageEntity(it) }
            .flatMap { messagesDao.insertMessageIntoDB(it).andThen(Single.just(true)) }
    }
}