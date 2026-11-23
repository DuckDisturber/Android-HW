package com.example.android_hw

interface AppsRepository {
    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
    suspend fun getApps(): List<AppInfo>
}
