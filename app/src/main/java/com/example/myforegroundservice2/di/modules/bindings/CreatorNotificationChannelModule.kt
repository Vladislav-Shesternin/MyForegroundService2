package com.example.myforegroundservice2.di.modules.bindings

import com.example.myforegroundservice2.repositories.CreatorNotificationChannelImpl
import com.example.myforegroundservice2.useCases.CreatorNotificationChannel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CreatorNotificationChannelModule {

    @Binds
    abstract fun bindCreatorNotificationChannel(impl: CreatorNotificationChannelImpl): CreatorNotificationChannel

}