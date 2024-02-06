package com.apps.hw2.chat.usecase

import com.apps.hw2.db.AppDatabase
import com.apps.hw2.db.MyUserIdStorage
import com.apps.hw2.db.mapper.MessagesEntitiesMapper
import com.apps.hw2.list.model.BaseMessage
import io.reactivex.Observable
import javax.inject.Inject

class ObserveMessagesUseCase @Inject constructor(
    private val appDatabase: AppDatabase
): IObserveMessagesUseCase {
    override fun execute(streamId: Int, topicName: String): Observable<List<BaseMessage>> {
        val messagesDAO = appDatabase.messagesDao()
        return messagesDAO.getMessagesFromDB(streamId, topicName)
            .map { MessagesEntitiesMapper(myUserId = MyUserIdStorage.myUserId)
                .mapMessagesEntitiesToMessagesObjects(it)
            }
    }
}