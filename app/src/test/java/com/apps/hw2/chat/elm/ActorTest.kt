package com.apps.hw2.chat.elm

import com.apps.hw2.channels.pager.stream.IMessagesMapper
import com.apps.hw2.net.StreamsService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

class ActorTest {
    @Test
    fun `Given mocked chat Actor, When execute() with LoadMessages command, Then getMessagesInStreamInTopic() is called only once`() {
        //Given
        val command = Command.LoadMessages
        val streamsService = mockk<StreamsService>()
        every { streamsService.getMessagesInStreamInTopic(any(), any(), any()) } returns Single.never()
        val messagesMapper = mockk<IMessagesMapper>()
        val actor = Actor(streamsService, messagesMapper)

        //When
        actor.execute(command)

        //Then
        verify(exactly = 1) { streamsService.getMessagesInStreamInTopic(any(), any(), any()) }
    }
}