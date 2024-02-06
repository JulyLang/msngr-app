package com.apps.hw2.db.converter

import androidx.room.TypeConverter
import com.apps.hw2.db.entity.messages.Reaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReactionsConverter {
    @TypeConverter
    fun fromReactionList(value: List<Reaction>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Reaction>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toReactionList(value: String): List<Reaction> {
        val gson = Gson()
        val type = object : TypeToken<List<Reaction>>() {}.type
        return gson.fromJson(value, type)
    }
}