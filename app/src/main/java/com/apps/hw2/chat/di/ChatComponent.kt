package com.apps.hw2.chat.di

import com.apps.hw2.chat.ChatFragment
import com.apps.hw2.di.ApplicationModule
import com.apps.hw2.di.DatabaseModule
import com.apps.hw2.di.NetworkModule
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        ChatModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
@ChatScope
interface ChatComponent {
    fun injectChatFragment(chatFragment: ChatFragment)
}