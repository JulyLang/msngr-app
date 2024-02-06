package com.apps.hw2

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ChannelsFragmentTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun showTopicsList() = run {

        step("Список скрыт")
        {
            activityTestRule.launchActivity(null)
            SubscribedStreamsPageScreen.topicsList.hasSize(1)
        }
        step("Клик по элементу списка")
        {
            SubscribedStreamsPageScreen.topicsList.childAt<SubscribedStreamsPageScreen.StreamItem>(0) {
                click()
            }
        }
        step("Отображается список топиков")
        {
            val listSize = SubscribedStreamsPageScreen.topicsList.getSize()
            assert(listSize > 1)
        }
    }
}