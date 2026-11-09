package com.example.android_hw

import retrofit2.http.GET
import retrofit2.http.Path

interface AppsApi {
    @GET("catalog")
    suspend fun getApps(): List<AppDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}
