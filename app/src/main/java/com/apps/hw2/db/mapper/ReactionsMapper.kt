package com.apps.hw2.db.mapper

import com.apps.hw2.db.entity.messages.Reaction
import com.apps.hw2.model.EmojiCounter

class ReactionsMapper(
    private val myUserId: Int
) {
    fun mapReactionsToEmojiCounters(reactions: List<Reaction>): List<EmojiCounter> {
        val result: MutableList<EmojiCounter> = mutableListOf()
        for (reaction in reactions) {
            val foundIndex = findEmojiPositionByCode(code = reaction.emojiCode, emojis = result)
            if (foundIndex == -1) {
                result.add(EmojiCounter(
                    name = reaction.emojiName,
                    code = reaction.emojiCode,
                    counter = 1,
                    isSelected = reaction.isMyReaction(userId = myUserId)
                ))
            } else {
                result[foundIndex] = updateEmojiCounter(emojiCounter = result[foundIndex])
            }
        }
        return result
    }

    private fun findEmojiPositionByCode(code: String, emojis: List<EmojiCounter>): Int {
        for (i in emojis.indices) {
            if (emojis[i].code == code) return i
        }
        return -1
    }

    private fun updateEmojiCounter(emojiCounter: EmojiCounter): EmojiCounter {
        return emojiCounter.copy(counter = emojiCounter.counter + 1)
    }
}