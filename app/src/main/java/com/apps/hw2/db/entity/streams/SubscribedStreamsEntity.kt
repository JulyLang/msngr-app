package com.apps.hw2.db.entity.streams

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.hw2.channels.pager.stream.TopicItem

@Entity(tableName = "subscribed_streams")
class SubscribedStreamsEntity (

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "stream_name")
    val streamName: String,

    @ColumnInfo(name = "topics")
    val topics: List<TopicItem>
)