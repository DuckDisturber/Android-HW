package com.example.android_hw

import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class AppsRepositoryImpl @Inject constructor(
    private val api: AppsApi,
) : AppsRepository {
    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> =
        ids.associateWith { api.getRating(it) }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        ids.map { id -> async { id to api.getRating(id) } }.awaitAll().toMap()
    }

    override suspend fun getApps(): List<AppInfo> = api.getApps().map { it.toAppInfo() }
}
