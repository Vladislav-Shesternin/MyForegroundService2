package com.example.myforegroundservice2.di.modules.bindings

import com.example.myforegroundservice2.repositories.SharedRepositoryImpl
import com.example.myforegroundservice2.useCases.SharedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SharedRepositoryModule {

    @Binds
    abstract fun bindSharedRepository(impl: SharedRepositoryImpl): SharedRepository

}