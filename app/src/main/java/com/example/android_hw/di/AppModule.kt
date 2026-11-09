package com.example.android_hw.di

import com.example.android_hw.AppsRepository
import com.example.android_hw.AppsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindAppsRepository(impl: AppsRepositoryImpl): AppsRepository
}
