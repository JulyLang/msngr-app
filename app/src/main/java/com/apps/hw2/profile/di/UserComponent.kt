package com.apps.hw2.profile.di

import com.apps.hw2.MainActivity
import com.apps.hw2.chat.di.ChatModule
import com.apps.hw2.di.NetworkModule
import dagger.Component

@Component(
    modules = [
        UserModule::class,
        NetworkModule::class
    ]
)

interface UserComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}