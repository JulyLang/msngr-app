package com.apps.hw2.net

import com.apps.hw2.net.holder.StreamObjectsHolder
import com.apps.hw2.net.holder.TopicObjectsHolder
import com.apps.hw2.net.model.*
import io.reactivex.Single
import retrofit2.http.*

interface StreamsService {

    @GET("api/v1/users/me")
    fun getOwnUser(): Single<UserObject>

    @GET("api/v1/streams")
    fun getAllStreams(): Single<StreamObjectsHolder>

    @GET("api/v1/users/me/subscriptions")
    fun getSubscribedStreams(): Single<SubscribedStreamObjectHolder>

    @GET("api/v1/users/me/{stream_id}/topics")
    fun getStreamTopics(@Path("stream_id") streamId: Int): Single<TopicObjectsHolder>

    @GET("api/v1/messages")
    fun getMessagesInStreamInTopic(
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
        @Query("anchor") anchor: String,//or Long
        @Query("narrow") narrow: String?,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("apply_markdown") applyMarkdown: Boolean = true
    ): Single<MessageObjectHolder>

    @POST("api/v1/messages")
    fun sendMessage(
        @Query("type") type: String,
        @Query("to") to: List<Int>,
        @Query("content") content: String,
        @Query("topic") topic: String? = null
    ): Single<SendMessageResult>

    @GET("api/v1/messages/{message_id}")
    fun fetchMessage(
        @Path("message_id") messageId: Int,
        @Query("apply_markdown") applyMarkdown: Boolean = true,
        @Query("client_gravatar") clientGravatar: Boolean = true,
    ): Single<SingleMessageObjectHolder>

    @POST("api/v1/messages/{message_id}/reactions")
    fun addEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
        @Query("emoji_code") emojiCode: String?,
        @Query("reaction_type") reactionType: String?
    ): Single<EmojiResult>

    @DELETE("api/v1/messages/{message_id}/reactions")
    fun deleteEmojiReaction(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
        @Query("emoji_code") emojiCode: String?,
        @Query("reaction_type") reactionType: String?
    ): Single<EmojiResult>
}