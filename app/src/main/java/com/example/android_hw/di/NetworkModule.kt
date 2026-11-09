package com.example.android_hw.di

import com.example.android_hw.AppsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAppsApi(): AppsApi = Retrofit.Builder()
        .baseUrl("http://185.103.109.134/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AppsApi::class.java)
}
