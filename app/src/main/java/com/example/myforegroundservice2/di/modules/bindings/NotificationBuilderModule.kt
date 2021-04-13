package com.example.myforegroundservice2.di.modules.bindings

import com.example.myforegroundservice2.repositories.NotificationBuilderImpl
import com.example.myforegroundservice2.useCases.NotificationBuilder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NotificationBuilderModule {

    @Binds
    abstract fun bindNotificationBuilder(impl: NotificationBuilderImpl): NotificationBuilder

}