package com.example.android_hw

import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path

interface AppsApi {
    @GET("catalog")
    suspend fun getApps(): List<AppDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}

suspend fun AppsApi.getRating(id: String): Float {
    delay(1500)
    return (30..50).random() / 10f
}
