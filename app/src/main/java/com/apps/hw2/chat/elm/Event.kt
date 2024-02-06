package com.apps.hw2.chat.elm

import android.text.Editable
import com.apps.hw2.list.model.BaseMessage

sealed class Event {
    sealed class Ui : Event() {
        object Init : Ui()
        data class OnMessageClicked(val message: BaseMessage) : Ui()
        data class OnMessageLongClicked(val message: BaseMessage) : Ui()
        data class OnMessageTextChanged(val text: Editable?) : Ui()
        object LoadNewMessagesPage : Ui()
        data class SendNewMessage(val messageText: String) : Ui()
    }

    sealed class Internal : Event() {
        data class MessagesLoadingSuccess(val messages: List<BaseMessage>) : Internal()
        object MessagesLoadingError : Internal()

        data class MessagesSyncSuccess(val result: Boolean) : Internal()
        object MessagesSyncError : Internal()

        data class SendMessageSuccess(val result: Boolean) : Internal()
        object SendMessageError : Internal()
    }
}