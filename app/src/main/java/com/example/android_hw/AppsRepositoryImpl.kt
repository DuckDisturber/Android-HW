package com.example.android_hw

import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor() : AppsRepository {
    override fun getApps(): List<AppInfo> = applications.toList()
}
