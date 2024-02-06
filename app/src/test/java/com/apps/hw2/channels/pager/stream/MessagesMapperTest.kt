package com.apps.hw2.channels.pager.stream

import com.apps.hw2.channels.pager.stream.MessageObjectStub.MY_NET_MESSAGE_WITHOUT_EMOJIS
import com.apps.hw2.channels.pager.stream.MessageObjectStub.MY_NET_MESSAGE_WITH_EMOJIS
import com.apps.hw2.channels.pager.stream.MessageObjectStub.USER_NET_MESSAGE_WITH_EMOJIS
import com.apps.hw2.list.model.BaseMessage
import com.apps.hw2.list.model.MyUserMessage
import com.apps.hw2.list.model.UserMessage
import org.junit.Test
import kotlin.test.assertIs

class MessagesMapperTest {

    private val messagesMapper: IMessagesMapper = MessagesMapper()

    @Test
    fun `Given net message without emojis, When map to UI model, Then UI message without emojis`() {
        //Given
        val netMessage = MY_NET_MESSAGE_WITHOUT_EMOJIS

        //When
        val uiMessage = messagesMapper.mapToMessage(netMessage) as MyUserMessage

        //Then
        assert(uiMessage.emojis.isEmpty())
    }

    @Test
    fun `Given net message with emojis, When map to UI model, Then UI message with emojis`() {
        //Given
        val netMessage = MY_NET_MESSAGE_WITH_EMOJIS

        //When
        val uiMessage = messagesMapper.mapToMessage(netMessage) as MyUserMessage

        //Then
        assert(uiMessage.emojis.isNotEmpty())
    }

    @Test
    fun `Given net message with isMeMessage == true, When map to UI model, Then UI message is MyUserMessage`() {
        //Given
        val netMessage = MY_NET_MESSAGE_WITH_EMOJIS

        //When
        val uiMessage: BaseMessage = messagesMapper.mapToMessage(netMessage)

        //Then
        assertIs<MyUserMessage>(uiMessage)
    }

    @Test
    fun `Given net message with isMeMessage == false, When map to UI model, Then UI message is UserMessage`() {
        //Given
        val netMessage = USER_NET_MESSAGE_WITH_EMOJIS

        //When
        val uiMessage: BaseMessage = messagesMapper.mapToMessage(netMessage)

        //Then
        assertIs<UserMessage>(uiMessage)
    }
}