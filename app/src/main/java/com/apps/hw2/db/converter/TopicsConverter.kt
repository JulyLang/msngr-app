package com.apps.hw2.db.converter

import androidx.room.TypeConverter
import com.apps.hw2.channels.pager.stream.TopicItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TopicsConverter {
    @TypeConverter
    fun fromTopicItemList(value: List<TopicItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<TopicItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTopicItemList(value: String): List<TopicItem> {
        val gson = Gson()
        val type = object : TypeToken<List<TopicItem>>() {}.type
        return gson.fromJson(value, type)
    }
}