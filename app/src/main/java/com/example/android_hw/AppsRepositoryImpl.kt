package com.example.android_hw

import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val api: AppsApi,
) : AppsRepository {
    override suspend fun getApps(): List<AppInfo> = api.getApps().map { it.toAppInfo() }
}
