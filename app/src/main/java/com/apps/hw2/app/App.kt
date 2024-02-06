package com.apps.hw2.app

import android.app.Application
import com.apps.hw2.channels.di.ChannelsComponent
import com.apps.hw2.channels.di.DaggerChannelsComponent
import com.apps.hw2.chat.di.ChatComponent
import com.apps.hw2.chat.di.DaggerChatComponent
import com.apps.hw2.di.ApplicationModule
import com.apps.hw2.profile.di.DaggerUserComponent
import com.apps.hw2.profile.di.UserComponent

class App : Application() {

    lateinit var channelsComponent: ChannelsComponent
    lateinit var chatComponent: ChatComponent
    lateinit var userComponent: UserComponent

    override fun onCreate() {
        super.onCreate()
        val appModule = ApplicationModule(this)
        channelsComponent = DaggerChannelsComponent.builder()
            .applicationModule(appModule)
            .build()
        chatComponent = DaggerChatComponent.builder()
            .applicationModule(appModule)
            .build()
        userComponent = DaggerUserComponent.create()
    }
}