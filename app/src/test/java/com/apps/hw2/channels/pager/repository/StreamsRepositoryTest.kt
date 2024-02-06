package com.apps.hw2.channels.pager.repository

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Test

class StreamsRepositoryTest {
    @Test
    fun `Given mocked repository, When observeAllStreams(), Then observeAllStreams() is called only once`() {
        //Given
        val localDataStore = mockk<StreamsLocalDataStore>()
        every { localDataStore.observeAllStreams() } returns Observable.just(emptyList())
        val remoteDataStore = mockk<StreamsRemoteDataStore>()
        val repository: IStreamsRepository = StreamsRepository(localDataStore, remoteDataStore)

        //When
        repository.observeAllStreams()

        //Then
        verify(exactly = 1) { localDataStore.observeAllStreams() }
    }
}