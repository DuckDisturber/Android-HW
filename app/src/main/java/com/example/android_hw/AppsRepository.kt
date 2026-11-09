package com.example.android_hw

interface AppsRepository {
    suspend fun getApps(): List<AppInfo>
}
