package com.apps.hw2.chat.elm

import com.apps.hw2.list.model.BaseMessage

sealed class Effect {
    object ShowError : Effect()
    data class OnClick(val message: BaseMessage) : Effect()
    data class OnLongClick(val message: BaseMessage) : Effect()
}